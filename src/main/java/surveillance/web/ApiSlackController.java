package surveillance.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import support.Converter;
import surveillance.service.SlackService;
import java.util.List;

@RequestMapping("/api/slack")
@RestController
public class ApiSlackController {

    @Autowired
    private SlackService slackService;

    /* 신규 채널 가입자 등록 */
    @PostMapping
    public void registerNewMember() {
         slackService.registerNewMember();
    }

    /* Commit 미완료자 메세지 전송 */

    /*@GetMapping
    public ResponseEntity<List<Member>> obtainProfile() throws Exception {
        List<Member> members =
                slackService.obtainMemberProfile(Converter.objectToArrayList(slackService.obtainMemberId(), "members"));
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity sendAllMessage(@RequestBody List<Member> members) throws Exception {
        slackService.sendAllMessage(members);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public Boolean sendMessage(@PathVariable String id) throws Exception {
        return slackService.sendMessage(id);
    }*/
}

