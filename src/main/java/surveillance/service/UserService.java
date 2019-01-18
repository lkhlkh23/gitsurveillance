package surveillance.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import surveillance.domain.user.User;
import surveillance.domain.user.UserRepository;
import surveillance.dto.UserDto;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = getLogger(UserService.class);

    public User saveUser(UserDto user) {
        return userRepository.save(user._toUser());
    }

    public boolean isDuplicationSlackId(UserDto user) {
        if(userRepository.findBySlackId(user.getSlackId()).isPresent()) {
            return true;
        }

        return false;
    }

    public boolean isDuplicationUserId(UserDto user) {
        if(userRepository.findByUserId(user.getUserId()).isPresent()) {
            return true;
        }

        return false;
    }
}
