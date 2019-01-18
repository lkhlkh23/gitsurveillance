package surveillance.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import support.domain.AbstractEntity;
import surveillance.domain.user.Avatar;
import surveillance.dto.UserDto;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class User extends AbstractEntity {

    @Column(unique = true, length = 10, nullable = false)
    @Size(min = 3, max = 10)
    @NotBlank
    private String userId;

    @Column(length = 12, nullable = false)
    @Size(min = 6, max = 12)
    @NotBlank
/*
    @Pattern(regexp = "/^[A-Za-z0-9]{6,12}$/")
*/
    @JsonIgnore
    private String password;

    @Column(nullable = false)
    @NotBlank
    private String name;

    @Column(nullable = false)
    @NotBlank
    private String gitId;

    @Column
    @NotBlank
    private String slackId;

    //@Embedded
    private Avatar avatar;

    public User() {

    }

    public User(String userId, String password, String name, String gitId, String slackId, Avatar avatar) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gitId = gitId;
        this.slackId = slackId;
        this.avatar = avatar;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGitId() {
        return gitId;
    }

    public void setGitId(String gitId) {
        this.gitId = gitId;
    }

    public String getSlackId() {
        return slackId;
    }

    public void setSlackId(String slackId) {
        this.slackId = slackId;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }

    public UserDto _toUserDto() {
        return new UserDto(this.userId, this.name, this.gitId, this.slackId, this.avatar);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gitId='" + gitId + '\'' +
                ", slackId='" + slackId + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
