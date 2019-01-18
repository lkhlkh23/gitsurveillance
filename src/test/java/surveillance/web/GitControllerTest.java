package surveillance.web;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import support.AcceptanceTest;

public class GitControllerTest extends AcceptanceTest {

    @Test
    public void getCommitsTest() {
        ResponseEntity<String> responseEntity = template().getForEntity("/api/git", String.class, new HttpHeaders());
    }
}