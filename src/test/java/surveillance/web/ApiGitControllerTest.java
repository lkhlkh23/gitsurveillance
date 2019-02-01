package surveillance.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.AcceptanceTest;
import support.UserFixture;
import surveillance.domain.user.User;
import surveillance.domain.user.UserRepository;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class ApiGitControllerTest extends AcceptanceTest {

    private static final Logger logger = getLogger(ApiGitControllerTest.class);

    @Test
    public void gitCommitsTest() {
        ResponseEntity<Void> responseEntity = template().postForEntity("/api/git", null, Void.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}