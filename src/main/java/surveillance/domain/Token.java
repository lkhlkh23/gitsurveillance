package surveillance.domain;

public class Token {

    private String tokenOfGit;
    private String tokenOfSlack;

    public Token() {

    }

    public Token(String tokenOfGit, String tokenOfSlack) {
        this.tokenOfGit = tokenOfGit;
        this.tokenOfSlack = tokenOfSlack;
    }

    public String getTokenOfGit() {
        return tokenOfGit;
    }

    public void setTokenOfGit(String tokenOfGit) {
        this.tokenOfGit = tokenOfGit;
    }

    public String getTokenOfSlack() {
        return tokenOfSlack;
    }

    public void setTokenOfSlack(String tokenOfSlack) {
        this.tokenOfSlack = tokenOfSlack;
    }
}
