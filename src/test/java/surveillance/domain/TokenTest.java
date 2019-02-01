package surveillance.domain;

import org.junit.Test;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class TokenTest {

    private static final Logger logger = getLogger(TokenTest.class);

    @Test
    public void 루트경로Test() throws IOException {
        String root = new File(".").getCanonicalPath();
        logger.info("Root : {}", root);
    }

    @Test
    public void 파일읽기Test() throws IOException {
        String root = new File(".").getCanonicalPath();
        String path = String.format("%s/src/main/resources/configuration.properties", root);
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = null;
        while((line = br.readLine()) != null) {

        }
    }
}
