package surveillance.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import surveillance.domain.user.User;
import surveillance.service.GitService;

import java.util.*;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/api/git")
public class ApiGitController {

    @Autowired
    private GitService gitService;

    private static final Logger logger = getLogger(ApiGitController.class);

    @PostMapping
    public void applyCommit() {
        gitService.applyCommitResult();
    }
}
