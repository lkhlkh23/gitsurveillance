package surveillance.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import support.Converter;
import surveillance.domain.Member;
import surveillance.service.GitService;
import surveillance.service.SlackService;

import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@RequestMapping("/exception")
public class ExceptionController {

    private static final Logger logger = getLogger(ExceptionController.class);

    @GetMapping("")
    public String start(Model model) throws Exception {
        return "exception";
    }
}
