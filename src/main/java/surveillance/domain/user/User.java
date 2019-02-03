package surveillance.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import static java.util.concurrent.TimeUnit.DAYS;

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
    private int performance;

    @Column
    private int totalCount;

    @Column
    private int rank = 1;

    @Column
    private LocalDate createDate;

    public User() {

    }

    public User(String gitId, String slackId) {
        this.gitId = gitId;
        this.slackId = slackId;
    }

    public User(String gitId, String slackId, LocalDate createDate) {
        this(gitId, slackId);
        this.createDate = createDate;
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

    public int getPerformance() {
        return performance;
    }

    public void setPerformance(int performance) {
        this.performance = performance;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @JsonIgnore
    public String getFormattedCreateDate() {
        return getFormattedDate(createDate, "yyyy.MM.dd");
    }

    private String getFormattedDate(LocalDate date, String format) {
        if (date == null) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern(format));
    }

    public void plusTotalCount() {
        this.totalCount += 1;
    }

    public void applyPerformance() {
        int days = Period.between(createDate, LocalDate.now()).getDays();
        this.performance = (int) Math.round((double)totalCount / days);
    }

    public void applyRank(User user) {
        this.rank = user.rank + 1;
        if(this.performance == user.performance) {
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
