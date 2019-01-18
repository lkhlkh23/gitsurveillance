package surveillance.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import support.Converter;
import support.MediaTypeCreator;
import surveillance.domain.Member;
import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GitService {

    private RestTemplate restTemplate = new RestTemplate();

    private static final Logger logger = getLogger(GitService.class);

    @Value("${git.token}")
    private String gitToken;

    public List<Member> obtainCommitOfMembers(List<Member> members) {
        List<Member> commiters = new ArrayList<>();
        String date = Converter.obtainCurrentDate();

        for(Member member : members) {
            commiters.add(obtainCommitOfMember(member, date));
        }
        logger.info("Informatioin : {}", members.toString());
        return commiters;
    }

    public Member obtainCommitOfMember(Member member, String date) {
        String url = "https://api.github.com/search/commits";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaTypeCreator.GIT_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                String.format("%s?q=committer-date:%s..%s+committer:%s&token:%s", url, date, date, member.getGitId(), gitToken),
                HttpMethod.GET,
                new HttpEntity(httpHeaders),
                new ParameterizedTypeReference<Map<String, Object>>(){}
        );
        logger.info(responseEntity.getBody().toString());
        return member.applyCommit((int)responseEntity.getBody().get("total_count"));
    }
}