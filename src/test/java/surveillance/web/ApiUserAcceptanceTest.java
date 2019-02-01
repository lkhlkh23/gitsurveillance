package surveillance.web;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.AcceptanceTest;
import support.ErrorMessage;
import support.UserFixture;
import surveillance.dto.ResultDto;

import static org.slf4j.LoggerFactory.getLogger;

public class ApiUserAcceptanceTest extends AcceptanceTest {

    private static final Logger logger = getLogger(ApiUserAcceptanceTest.class);

    @Before
    public void setUp() {
        ResponseEntity<Void> responseEntity = template().postForEntity("/api/users", UserFixture.BRAD, Void.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void 등록된슬랙아이디확인() {
        ResponseEntity<ResultDto> responseEntity = template()
                .getForEntity("/api/users/lkhlkh09", ResultDto.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        logger.debug("msg : {}",responseEntity.getBody().getObject().toString());
    }

    @Test
    public void 회원가입_성공() {
        ResponseEntity<ResultDto> responseEntity = template()
                .postForEntity("/api/users", UserFixture.DOBY, ResultDto.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void 회원가입_중복아이디_실패() {
        ResponseEntity<ErrorMessage> responseEntity = template().postForEntity("/api/users", UserFixture.BRAD, ErrorMessage.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    /*@Test
    public void 회원가입_중복슬랙아이디_실패() {
        User user = UserFixture.DOBY;
        user.setSlackId("brad903");
        ResponseEntity<ErrorMessage> responseEntity = template().postForEntity("/api/users", user, ErrorMessage.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }

    @Test
    public void 회원가입_비밀번호_특수문자미포함_성공() {
        UserDto user = UserFixture.DOBY;
        user.setPassword("lkhlkh23");
        ResponseEntity<Void> responseEntity = template().postForEntity("/api/users", user, Void.class);
        softly.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }*/
}
