package ca.utoronto.utm.mcs.projectcloudinfantry.response;

public class MessageResponse{
    private String content;
    private Long fromId;
    private String fromUsername;
    private Long msgId;
    private String CreatedTimeStamp;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public String getCreatedTimeStamp() {
        return CreatedTimeStamp;
    }

    public void setCreatedTimeStamp(String createdTimeStamp) {
        CreatedTimeStamp = createdTimeStamp;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
}
