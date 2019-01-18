package surveillance.web;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import surveillance.DuplicationInfoException;
import surveillance.UnRegisteredException;
import surveillance.domain.user.User;
import surveillance.dto.TransferDto;
import surveillance.dto.UserDto;
import surveillance.service.SlackService;
import surveillance.service.UserService;
import javax.validation.Valid;
import java.net.URI;
import java.util.Locale;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SlackService slackService;

    @Autowired
    private MessageSource messageSource;

    private static final Logger logger = getLogger(ApiUserController.class);

    /* 회원가입 */
    @PostMapping()
    public ResponseEntity<TransferDto<String>> createUser(@RequestBody @Valid UserDto user) {
        logger.debug("Call createUser Method -> UserDto : {}", user.toString());

        if(userService.isDuplicationUserId(user)) {
            throw new DuplicationInfoException(messageSource.getMessage("error.duplication.id",
                    null, Locale.getDefault()));
        }

        if(userService.isDuplicationSlackId(user)) {
            throw new DuplicationInfoException(messageSource.getMessage("error.duplication.id",
                    null, Locale.getDefault()));
        }

        User savedUser = userService.saveUser(user);
        return new ResponseEntity(new TransferDto<String>("", true)
                , createHeader("/api/users/" + savedUser.getId()),HttpStatus.CREATED);
    }

    @GetMapping("/{slackId}")
    public TransferDto unRegisteredSlackId(@PathVariable String slackId) {
        logger.debug("Call unRegisteredSlackId Method -> slackId : {}");
        if(!slackService.isRegisteredSlackId(slackId)) {
            throw new UnRegisteredException(messageSource.getMessage("error.unregister.id",
                    null, Locale.getDefault()));
        }
        return new TransferDto<String>("", true);
    }

    public HttpHeaders createHeader(String url) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create(url));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
