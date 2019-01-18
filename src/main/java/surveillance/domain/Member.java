package surveillance.domain;

public class Member {

    private static final String REPOSITORY_URL = "https://github.com/";

    private String slackId;

    private String name;

    private String gitId;

    private String email;

    private boolean isCommit;

    private String repository;

    private String image;

    public Member() {

    }

    public Member(String slackId, String name, String email, String image) {
        this.slackId = slackId;
        this.name = name;
        this.gitId = obtainGitId(name);
        this.email = email;
        this.image = image;
        this.repository = REPOSITORY_URL + gitId;
    }

    public String getSlackId() {
        return slackId;
    }

    public void setSlackId(String slackId) {
        this.slackId = slackId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isCommit() {
        return isCommit;
    }

    public void setCommit(boolean commit) {
        isCommit = commit;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Member applyCommit(int commitCount) {
        this.isCommit = true;
        if(commitCount < 1) {
            this.isCommit = false;
        }
        return this;
    }

    public String obtainGitId(String name) {
        return name.split("\\(")[1].split("\\)")[0];
    }

    @Override
    public String toString() {
        return "Member{" +
                "slackId='" + slackId + '\'' +
                ", name='" + name + '\'' +
                ", gitId='" + gitId + '\'' +
                ", email='" + email + '\'' +
                ", isCommit=" + isCommit +
                ", repository='" + repository + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
