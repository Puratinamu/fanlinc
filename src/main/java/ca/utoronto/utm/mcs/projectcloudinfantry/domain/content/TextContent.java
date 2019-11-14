package ca.utoronto.utm.mcs.projectcloudinfantry.domain.content;

public class TextContent extends Content {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getOidUser() { return super.getOidUser(); }
    public Long getOidFandom() { return super.getOidFandom(); }
}
