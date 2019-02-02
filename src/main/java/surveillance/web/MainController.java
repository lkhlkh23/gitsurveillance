package surveillance.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import support.Converter;
import surveillance.service.GitService;
import surveillance.service.SlackService;
import surveillance.service.UserService;

import javax.persistence.Convert;
import java.util.List;

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

        slackService.registerNewMember();

        model.addAttribute("users", userService.obtainUsersWithRank());
        model.addAttribute("date", Converter.obtainYesterday());

        //gitService.applyCommitResult();

        //slackService.sendAllMessage();

        return "index";
    }
}
