package surveillance.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import support.Converter;
import support.RandomNumber;
import surveillance.SlackApplicationConfigurationProp;
import surveillance.domain.Member;
import surveillance.domain.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class SlackService {

    private RestTemplate restTemplate = new RestTemplate();

    private static final Logger logger = getLogger(SlackService.class);

    @Value("${slack.token}")
    private String slackToken;

    @Value("${slack.channel}")
    private String slackChannel;

    @Autowired
    private SlackApplicationConfigurationProp slackApplicationConfigurationProp;

    public Map<String, Object> obtainMemberId() {
        String url = "https://slack.com/api/conversations.members";
        ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                String.format("%s?token=%s&channel=%s", url, slackToken, slackChannel),
                HttpMethod.GET,
                createResource(),
                new ParameterizedTypeReference<Map<String, Object>>(){}
        );
        return responseEntity.getBody();
    }

    public HttpEntity createResource() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        return new HttpEntity(httpHeaders);
    }

    public List<Member> obtainMemberProfile(List<String> members) {
        String apiUrl = "https://slack.com/api/users.profile.get";
        List<Member> profiles = new ArrayList<>();
        for(String id : members) {
            ResponseEntity<Map<String, Object>> responseEntity = restTemplate.exchange(
                    String.format("%s?token=%s&user=%s", apiUrl, slackToken, id),
                    HttpMethod.GET,
                    createResource(),
                    new ParameterizedTypeReference<Map<String, Object>>(){}
            );
            Map<String, String> profile = Converter.objectToMap(responseEntity.getBody(), "profile");
            if(profile.get("display_name").contains("(") && profile.get("display_name").contains(")")) {
                profiles.add(new Member(id, profile.get("display_name"), profile.get("email"), profile.get("image_original")));
            }
        }
        return profiles;
    }

    public List<Member> sendAllMessage(List<Member> members) {
        List<Member> sendFailMembers = new ArrayList<>();
        for (Member member : members) {
            boolean flag = sendMessage(member.getSlackId());
            if(!flag) {
                sendFailMembers.add(member);
            }
        }
        return sendFailMembers;
    }

    public boolean sendMessage(String slackId) {
        String url = "https://slack.com/api/chat.postMessage";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<Map<String, String>> request = new HttpEntity(new Message().createMessage(slackId, slackToken), httpHeaders);
        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);
        if(responseEntity.getBody().get("ok").equals("false")) {
            return false;
        }
        return true;
    }

    /* 슬랙에 가입된 아이디인지 확인하는 메소드 */
    public boolean isRegisteredSlackId(String slackId) {
        String url = slackApplicationConfigurationProp.getCmd().getProfile();

        logger.debug("slackId : {}, token : {}, url : {}", slackId, slackApplicationConfigurationProp.getToken(), url);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", slackApplicationConfigurationProp.getToken());
        params.add("name", slackId);

        HttpEntity<Map> request = new HttpEntity<>(params, httpHeaders);

        ResponseEntity<Map> responseEntity = restTemplate.postForEntity(url, request, Map.class);

        if(responseEntity.getBody().get("ok").equals("true")) {
            return true;
        }

        return false;
    }
}