package surveillance;

import org.slf4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import surveillance.domain.Token;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private static final Logger logger = getLogger(WebMvcConfig.class);

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setCacheSeconds(30);
        return messageSource;
    }

    @Bean
    public MessageSourceAccessor messageSourceAccessor(MessageSource messageSource) {
        return new MessageSourceAccessor(messageSource);
    }

    @Bean
    public Token token() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File(obtainTokenPath())));
        String line = null;
        String gitToken = null;
        String slackToken = null;
        while((line = br.readLine()) != null) {
            logger.info("LINE : {}", line);
            if(line.startsWith("slack.token")) {
                slackToken = line.split("=")[1];
            }

            if(line.startsWith("git.token")) {
                gitToken = line.split("=")[1];
            }
        }

        return new Token(gitToken, slackToken);
    }

    public String obtainTokenPath() throws IOException {
        String root = new File(".").getCanonicalPath();
        String path = String.format("%s/src/main/resources/configuration.properties", root);
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        String line = null;
        while((line = br.readLine()) != null) {
            if(line.startsWith("token.path")) {
                logger.info("TOKEN PATH : {}", line);
                return line.split("=")[1];
            }
        }
        return line;
    }

}
