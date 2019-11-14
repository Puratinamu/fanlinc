package ca.utoronto.utm.mcs.projectcloudinfantry.request;

public class TextPostRequest {

    private Long oidCreator;
    private Long oidFandom;
    private String text;
    private String title;

    public Long getOidCreator() {
        return oidCreator;
    }

    public void setOidCreator(Long oidCreator) {
        this.oidCreator = oidCreator;
    }

    public Long getOidFandom() {
        return oidFandom;
    }

    public void setOidFandom(Long oidFandom) {
        this.oidFandom = oidFandom;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
