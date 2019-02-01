package surveillance.domain;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.RandomNumber;
import surveillance.SlackApplicationConfigurationProp;
import surveillance.domain.user.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Message {

    private static final String USER_NAME = "마구니를 쫓는자";
    private static final String iconUrl = "https://t1.daumcdn.net/cfile/tistory/220CE83D584F741A22";

    public Message() {

    }

    public static MultiValueMap<String, String> createMessage(User user, Token token
            , SlackApplicationConfigurationProp slackApplicationConfigurationProp) {
        List<String> messages = slackApplicationConfigurationProp.getMessages();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("channel", user.getSlackId());
        params.add("text", messages.get(RandomNumber.getRandomNumber(messages.size())) + "  " + iconUrl);
        params.add("username", USER_NAME);
        params.add("token", token.getTokenOfSlack());
        params.add("icon_url", iconUrl);
        return params;
    }
}
