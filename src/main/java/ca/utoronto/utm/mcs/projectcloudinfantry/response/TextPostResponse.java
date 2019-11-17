package ca.utoronto.utm.mcs.projectcloudinfantry.response;


public class TextPostResponse {

    private Long oidPost;
    private Long oidCreator;
    private String username;
    private Long oidFandom;
    private String fandomName;
    private String text;


    public Long getOidPost() {
        return oidPost;
    }

    public void setOidPost(Long oidPost) {
        this.oidPost = oidPost;
    }

    public Long getOidCreator() {
        return oidCreator;
    }

    public void setOidCreator(Long oidCreator) {
        this.oidCreator = oidCreator;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getOidFandom() {
        return oidFandom;
    }

    public void setOidFandom(Long oidFandom) {
        this.oidFandom = oidFandom;
    }

    public String getFandomName() {
        return fandomName;
    }

    public void setFandomName(String fandomName) {
        this.fandomName = fandomName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
