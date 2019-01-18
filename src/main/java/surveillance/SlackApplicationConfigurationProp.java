package surveillance;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "slack")
@PropertySource("classpath:configuration.properties")
public class SlackApplicationConfigurationProp {

    private String token;
    private String channel;

    private Cmd cmd = new Cmd();

    public static class Cmd {

        private String profile;

        public Cmd() {

        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Cmd getCmd() {
        return cmd;
    }

    public void setCmd(Cmd get) {
        this.cmd = cmd;
    }
}
