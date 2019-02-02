package surveillance.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

    @Column
    @NotBlank
    @Id
    private String slackId;

    private String gitId;

    @Column
    private boolean committed = false;

    @Column
    private int totalCount;

    @Column
    private int rank = 1;

    public User() {

    }

    public User(String gitId, String slackId) {
        this.gitId = gitId;
        this.slackId = slackId;
    }


    public boolean isCommitted() {
        return committed;
    }

    public void setCommitted(boolean committed) {
        this.committed = committed;
    }

    public String getSlackId() {
        return slackId;
    }

    public void setSlackId(String slackId) {
        this.slackId = slackId;
    }

    public String getGitId() {
        return gitId;
    }

    public void setGitId(String gitId) {
        this.gitId = gitId;
    }

    public void completeCommit() {
        this.committed = true;
    }

    public boolean isSelf(String slackId) {
        return this.slackId.equals(slackId);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void plusTotalCount() {
        this.totalCount += 1;
    }

    public void applyRank(User user) {
        this.rank = user.rank + 1;
        if(this.totalCount == user.totalCount) {
            this.rank = user.rank;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "slackId='" + slackId + '\'' +
                ", gitId='" + gitId + '\'' +
                ", committed=" + committed +
                '}';
    }
}
