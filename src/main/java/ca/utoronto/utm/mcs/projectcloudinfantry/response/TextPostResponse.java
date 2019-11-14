package ca.utoronto.utm.mcs.projectcloudinfantry.response;


public class TextPostResponse {

    private Long oidPost;
    private Long oidCreator;
    private Long oidFandom;

    public Long getOidFandom() {
        return oidFandom;
    }

    public void setOidFandom(Long oidFandom) {
        this.oidFandom = oidFandom;
    }

    public Long getOidCreator() {
        return oidCreator;
    }

    public void setOidCreator(Long oidCreator) {
        this.oidCreator = oidCreator;
    }

    public Long getOidPost() {
        return oidPost;
    }

    public void setOidPost(Long oidPost) {
        this.oidPost = oidPost;
    }
}
