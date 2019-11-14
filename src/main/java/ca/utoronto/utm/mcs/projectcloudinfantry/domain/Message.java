package ca.utoronto.utm.mcs.projectcloudinfantry.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@NodeEntity
public class Message {
    @Id
    @GeneratedValue
    private Long oidMessage;
    private Long fromId;
    private String fromUsername;

    @Relationship(type = "FROM_CHAT")
    private ChatRoom chatRoom;

    @CreatedDate
    private Date creationTimestamp;

    private String content;

    public Date getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Date creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

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

    public Long getOidMessage() {
        return oidMessage;
    }

    public void setOidMessage(Long oidMessage) {
        this.oidMessage = oidMessage;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
