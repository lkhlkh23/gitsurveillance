package surveillance.domain;

import org.junit.Test;
import support.AcceptanceTest;

public class MemberTest extends AcceptanceTest {

    @Test
    public void obtainGitIdTest() {
        softly.assertThat(new Member().obtainGitId("doby(lkhlkh23)")).isEqualTo("lkhlkh23");
    }


}
