package surveillance.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import support.Converter;
import surveillance.domain.Member;
import surveillance.service.GitService;
import surveillance.service.SlackService;

import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class MainController {

    private static final Logger logger = getLogger(MainController.class);

    @Autowired
    private GitService gitService;

    @Autowired
    private SlackService slackService;

    @GetMapping("/")
    public String start(Model model) throws Exception {
        logger.debug(" ========= start =========");
        /*List<Member> members =
                slackService.obtainMemberProfile(Converter.objectToArrayList(slackService.obtainMemberId(), "members"));
        List<Member> commiters = gitService.obtainCommitOfMembers(members);
        model.addAttribute("nonCommiters", commiters.stream().filter(c -> !c.isCommit()).collect(Collectors.toList()));
        model.addAttribute("commiters", commiters.stream().filter(c -> c.isCommit()).collect(Collectors.toList()));*/
        return "index";
    }
}
