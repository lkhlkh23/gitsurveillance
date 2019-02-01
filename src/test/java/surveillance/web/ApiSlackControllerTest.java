package surveillance.web;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.AcceptanceTest;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ApiSlackControllerTest extends AcceptanceTest {

    private static final Logger logger = getLogger(ApiSlackControllerTest.class);

    private static final String CHANNEL = "CEUMURDL4";

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

    @Test
    public void obtainProfileTest() {
        /*String id = "UCR3RN38X";

        String apiUrl = "https://slack.com/api/users.profile.get";
        //String url = String.format("%s?token=%s&user=%s", apiUrl, TOKEN, id);
        ResponseEntity<String> responseEntity = template().getForEntity(url, String.class, httpHeaders);

        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        logger.info("Body : {} ",responseEntity.getBody());*/
    }

    /*@Test
    public void sendMessageTest() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("UCR3RN38X","Doby(lkhlkh23)", "lkhlkh09@gmial.com", ""));

        ResponseEntity<Void> responseEntity = template().postForEntity("/api/slack/UCR3RN38X", new HttpEntity<>(members, new HttpHeaders()), Void.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }*/

    @Test
    public void sendMessageTest_실패() {
        /*List<Member> members = new ArrayList<>();
        members.add(new Member("UCPRRTARJ2", "RED(SeEunKim)", "kimse9450@gmail.com"));
        members.add(new Member("UCR3RN38X","Doby(lkhlkh23)", "lkhlkh09@gmial.com"));

        ResponseEntity<Void> responseEntity = template().postForEntity("/api/slack", new HttpEntity<>(members, new HttpHeaders()), Void.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);*/
    }
}
