package surveillance.dto;

import support.domain.AbstractEntity;
import surveillance.domain.user.Avatar;
import surveillance.domain.user.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto extends AbstractEntity {

    @Size(min = 3, max = 10)
    @NotBlank
    private String userId;

    @Size(min = 6, max = 12)
    @NotBlank
/*
    @Pattern(regexp = "/^[A-Za-z0-9]{6,12}$/")
*/
    private String password;

    @NotBlank
    private String name;

    @NotBlank
    private String gitId;

    @NotBlank
    private String slackId;

    private Avatar avatar;

    public UserDto() {

    }

    public UserDto(String userId, String password, String name, String gitId, String slackId, Avatar avatar) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.gitId = gitId;
        this.slackId = slackId;
        this.avatar = avatar;
    }

    public UserDto(String userId, String name, String gitId, String slackId, Avatar avatar) {
        this(userId, "", name, gitId, slackId, avatar);
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

    public User _toUser() {
        return new User(this.userId, this.password, this.name, this.gitId, this.slackId, this.avatar);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", gitId='" + gitId + '\'' +
                ", slackId='" + slackId + '\'' +
                ", avatar=" + avatar +
                '}';
    }
}
