package surveillance.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import surveillance.domain.user.User;
import surveillance.domain.user.UserRepository;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = getLogger(UserService.class);

    @Transactional
    public List<User> obtainUsersWithRank() {
        List<User> users = userRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "totalCount")));
        for (int i = 1; i < users.size(); i++) {
            users.get(i).applyRank(users.get(i - 1));
        }

        return users;
    }
}
