package surveillance.domain;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {

    private static final String PROPERTIES_NAME = "/token.properties";

    private static Properties properties = new Properties();

    private String path;

    public ConfigurationReader() {
        path = getClass().getResource(PROPERTIES_NAME).getPath();
    }

    public String obtainSlackToken() throws IOException {
        properties.load(new FileInputStream(path));
        return properties.getProperty("slack.token");
    }

    public String obtainGitToken() throws IOException {
        properties.load(new FileInputStream(path));
        return properties.getProperty("git.token");
    }

    public String obtainSlackChannel() throws IOException {
        properties.load(new FileInputStream(path));
        return properties.getProperty("slack.channel");
    }
}
