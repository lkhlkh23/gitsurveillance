package surveillance.domain.user;

import java.util.ArrayList;
import java.util.List;

public class Users {

    private List<User> members;
    private int rank;

    public Users(int rank) {
        this.members = new ArrayList<>();
        this.rank = rank;
    }

    public void addUser(User user) {
        this.members.add(user);
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}


