package surveillance.service;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import support.Rank;
import surveillance.domain.user.User;
import surveillance.domain.user.UserRepository;
import surveillance.domain.user.Users;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = getLogger(UserService.class);

    @Transactional
    public List<Users> obtainUsersWithRank() {
        List<User> users = userRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "performance")));
        for (int i = 1; i < users.size(); i++) {
            users.get(i).applyRank(users.get(i - 1));
        }

        return Rank.processRank(users);
    }
}
