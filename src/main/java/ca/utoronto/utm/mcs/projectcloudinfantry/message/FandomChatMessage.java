package ca.utoronto.utm.mcs.projectcloudinfantry.message;

public class FandomChatMessage {
    private long fromUserId;
    private String fandomId;
    private String fandomInterestLevel;
    private String messageContent;
    public FandomChatMessage(final String fandomId, final String fandomInterestLevel, final String messageContent)
    {
        this.fandomId = fandomId;
        this.fandomInterestLevel = fandomInterestLevel;
        this.messageContent = messageContent;
    }

    public long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFandomId() {
        return fandomId;
    }

    public void setFandomId(String fandomId) {
        this.fandomId = fandomId;
    }

    public String getFandomInterestLevel() {
        return fandomInterestLevel;
    }

    public void setFandomInterestLevel(String fandomInterestLevel) {
        this.fandomInterestLevel = fandomInterestLevel;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }
}
