package surveillance;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ConfigurationProperties(prefix = "git")
@PropertySource("classpath:configuration.properties")
public class GitApplicationConfigurationProp {

    private Cmd cmd = new Cmd();

    public static class Cmd {

        private String search;

        public Cmd() {

        }

        public String getSearch() {
            return search;
        }

        public void setSearch(String search) {
            this.search = search;
        }
    }

    public Cmd getCmd() {
        return cmd;
    }

    public void setCmd(Cmd get) {
        this.cmd = cmd;
    }
}
