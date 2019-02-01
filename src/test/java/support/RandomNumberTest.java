package support;

import org.junit.Test;
import org.slf4j.Logger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.slf4j.LoggerFactory.getLogger;

public class RandomNumberTest {

    private static final Logger logger = getLogger(RandomNumberTest.class);

    @Test
    public void randomNumberTest() {
        for (int i = 0; i < 10; i++) {
            assertThat(RandomNumber.getRandomNumber(1)).isEqualTo(0);
        }
    }
}
