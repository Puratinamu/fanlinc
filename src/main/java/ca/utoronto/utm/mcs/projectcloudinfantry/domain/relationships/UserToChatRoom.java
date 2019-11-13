package ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.ChatRoom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "IN_CHAT")
public class UserToChatRoom {
    //Relationship for a user in a chatroom
    // Could be in a DM chatroom or Fandom Chat (By Interest Level)
    @Id
    @GeneratedValue
    private Long oidUserToChatRoom;

    @Property
    private String relationship;

    @Property
    @StartNode
    private User user;

    @Property
    @EndNode
    private ChatRoom chatRoom;
    //type -> dm, fandom interests level
    public UserToChatRoom(User user, ChatRoom chatRoom, String type){
        this.setRelationship(type);
        this.setChatRoom(chatRoom);
        this.setUser(user);
    }

    public Long getOidUserToChatRoom() {
        return oidUserToChatRoom;
    }

    public void setOidUserToChatRoom(Long oidUserToChatRoom) {
        this.oidUserToChatRoom = oidUserToChatRoom;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
