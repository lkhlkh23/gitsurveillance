package surveillance.domain;

import org.junit.Test;
import support.AcceptanceTest;

import java.io.IOException;

public class configurationReaderTest extends AcceptanceTest {

    @Test
    public void getTokenTest() throws IOException {
        softly.assertThat(new ConfigurationReader().obtainSlackChannel()).isEqualTo("CET4JLG1W");
    }
}
