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

    private String channel;

    private Cmd cmd = new Cmd();

    private List<String> messages = new ArrayList<>();

    public static class Cmd {

        private String profile;
        private String member;
        private String message;

        public Cmd() {

        }

        public String getProfile() {
            return profile;
        }

        public void setProfile(String profile) {
            this.profile = profile;
        }

        public String getMember() {
            return member;
        }

        public void setMember(String member) {
            this.member = member;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
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

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }
}
