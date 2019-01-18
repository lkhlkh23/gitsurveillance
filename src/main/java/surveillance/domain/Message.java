package surveillance.domain;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.RandomNumber;
import java.util.HashMap;
import java.util.Map;

public class Message {

    private static final String USER_NAME = "마구니를 쫓는자";
    private static final Map<Integer, String> contents = new HashMap<>();
    private static final String iconUrl = "https://t1.daumcdn.net/cfile/tistory/220CE83D584F741A22";

    static {
        contents.put(1, "관심법이란 짐의 시피유 인스럭션으로 그대의 커널메모리를 읽는것이니라!");
        contents.put(2, "마구니가 가득하구나!");
        contents.put(3, "어리석은 중생들이여, 짐이 왔노라!");
    }

    public Message() {

    }

    public static MultiValueMap<String, String> createMessage(String slackId, String token) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("channel", slackId);
        params.add("text", contents.get(RandomNumber.getRandomNumber(contents.size())) + "   " +iconUrl);
        params.add("username", USER_NAME);
        params.add("token", token);
        params.add("icon_url", iconUrl);
        return params;
    }
}
