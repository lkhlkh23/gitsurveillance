package surveillance.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import support.ErrorMessage;
import surveillance.DuplicationInfoException;
import surveillance.UnRegisteredException;
import surveillance.dto.TransferDto;

@RestControllerAdvice(annotations = RestController.class)
public class RestSecurityControllerAdvice {
    private static final Logger logger = LoggerFactory.getLogger(RestSecurityControllerAdvice.class);

    @ExceptionHandler(DuplicationInfoException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<TransferDto<ErrorMessage>> DuplicationInfoException(DuplicationInfoException e) {
        logger.debug("DuplicationInfoException : {} ",e.getMessage());
        return new ResponseEntity<>(new TransferDto<>(new ErrorMessage(e.getMessage()), false)
                , HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnRegisteredException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ResponseEntity<TransferDto<ErrorMessage>> UnRegisteredException(UnRegisteredException e) {
        return new ResponseEntity<>(new TransferDto<>(new ErrorMessage(e.getMessage()), false)
                , HttpStatus.FORBIDDEN);
    }
}
