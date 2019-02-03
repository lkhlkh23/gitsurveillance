package support;

import surveillance.domain.user.User;
import surveillance.domain.user.Users;
import java.util.ArrayList;
import java.util.List;

public class Rank {

    public static List<Users> processRank (List<User> users) {
        List<Users> rank = initRank(users);
        for (User user : users) {
            rank.get(user.getRank() - 1).addUser(user);
        }

        return rank;
    }

    public static List<Users> initRank(List<User> users) {
        List<Users> rank = new ArrayList<>();
        for (int i = 1; i <= users.get(users.size() - 1).getRank(); i++) {
            rank.add(new Users(i));
        }

        return rank;
    }
}
