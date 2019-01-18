package surveillance.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import support.AcceptanceTest;
import support.MediaTypeCreator;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.slf4j.LoggerFactory.getLogger;

public class GitServiceTest extends AcceptanceTest {
    @Autowired
    private GitService gitService;

    private static final Logger logger = getLogger(GitServiceTest.class);

    @Test
    public void obtainCommitOfMemberTest() {
        String url = "https://api.github.com/search/commits";

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaTypeCreator.GIT_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String date = "2019-01-03";
        ResponseEntity<Map<String, Object>> responseEntity = new RestTemplate().exchange(
                String.format("%s?q=committer-date:%s..%s+author:%s&token:%s"
                        , url, date, date, "gbeea1004", ""),
                HttpMethod.GET,
                new HttpEntity(httpHeaders),
                new ParameterizedTypeReference<Map<String, Object>>(){}
        );

        logger.info("Body : {}", responseEntity.getBody().toString());
    }

}
