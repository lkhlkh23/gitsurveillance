package surveillance.web;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.AcceptanceTest;

import static org.slf4j.LoggerFactory.getLogger;

public class ApiSlackControllerTest extends AcceptanceTest {

    private static final Logger logger = getLogger(ApiSlackControllerTest.class);

    private HttpHeaders httpHeaders;

    private MultiValueMap<String, String> params;

    @Before
    public void setUp() {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        params = new LinkedMultiValueMap<>();
    }

    @Test
    public void authTest() {
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(params, httpHeaders);

        String url = "https://slack.com/api/auth.test";
        ResponseEntity<String> responseEntity = template().postForEntity(url, request, String.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void registerMemberTest() {
        ResponseEntity<Void> responseEntity = template().postForEntity("/api/slack", null, Void.class, httpHeaders);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
