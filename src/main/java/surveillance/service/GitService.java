package surveillance.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import support.Converter;
import support.MediaTypeCreator;
import surveillance.GitApplicationConfigurationProp;
import surveillance.domain.Token;
import surveillance.domain.user.User;
import surveillance.domain.user.UserRepository;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GitService {

    @Autowired
    private GitApplicationConfigurationProp gitApplicationConfigurationProp;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Token token;

    private RestTemplate restTemplate = new RestTemplate();

    private static final Logger logger = getLogger(GitService.class);

    /* 데이터베이스에서 Commit 미완료자(Commit 검사 대상자) 조회
        --> Commit 완료자는 확인할 필요가 없기 때문에 데이버테이스에서 상태값 관리
    */
    public List<User> obtainNonCommiters() {
        return userRepository.findByCommitted(false);
    }

    /* Commit 완료자는 데이터베이스 반영 */
    @Transactional
    public void applyCommitResult() {
        List<User> users = obtainNonCommiters();
        String date = Converter.obtainCurrentDate();

        for(User user : users) {
            if(isCommit(user, date)) {
                user.completeCommit();
                user.plusTotalCount();
            }
        }
    }

    /* Commit 유무 확인하는 메소드 */
    public boolean isCommit(User user, String date) {
        String url = gitApplicationConfigurationProp.getCmd().getSearch();
        String gitToken = token.getTokenOfGit();

        logger.info("URL : {}, Git Token : {}", url, gitToken);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaTypeCreator.GIT_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                String.format("%s?q=committer-date:%s..%s+committer:%s&token:%s", url, date, date, user.getGitId(), gitToken),
                HttpMethod.GET,
                new HttpEntity(httpHeaders),
                new ParameterizedTypeReference<Map<String, Object>>(){}
        );

        logger.info("Commit Info : {} ",responseEntity.getBody().toString());
        return (int)responseEntity.getBody().get("total_count") > 0;
    }
}