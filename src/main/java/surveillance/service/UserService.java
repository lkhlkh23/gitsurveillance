package surveillance.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import surveillance.domain.user.User;
import surveillance.domain.user.UserRepository;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = getLogger(UserService.class);
}
