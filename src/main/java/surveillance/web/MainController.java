package surveillance.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import support.Converter;
import surveillance.domain.user.User;
import surveillance.domain.user.Users;
import surveillance.service.GitService;
import surveillance.service.SlackService;
import surveillance.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MainController {

    private static final Logger logger = getLogger(MainController.class);

    @Autowired
    private GitService gitService;

    @Autowired
    private SlackService slackService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String start(Model model) throws Exception {
        logger.debug(" ========= start =========");

        /* 신규멤버 등록 */
        slackService.registerNewMember();

        /* 멤버들 랭킹 정보 조회 */
        model.addAttribute("users", userService.obtainUsersWithRank());
        model.addAttribute("date", Converter.obtainCurrentDate());

        /* Commit Result 조회 */
        //gitService.applyCommitResult();

        /* 메세지 전송 */
        //slackService.sendAllMessage();

        return "index";
    }
}
