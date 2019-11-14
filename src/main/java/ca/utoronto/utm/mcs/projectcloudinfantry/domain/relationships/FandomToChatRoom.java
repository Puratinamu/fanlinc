package ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.ChatRoom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import org.neo4j.ogm.annotation.*;

@RelationshipEntity(type = "HAS_CHAT")
public class FandomToChatRoom {
    // A Fandom Has a Chatroom For every interest level.
    @Id
    @GeneratedValue
    private Long oidHasChatRoom;

    @Property
    private String relationship;

    @Property
    @StartNode
    private Fandom fandom;

    @Property
    @EndNode
    private ChatRoom chatRoom;


    public Long getOidHasChatRoom() {
        return oidHasChatRoom;
    }

    public void setOidHasChatRoom(Long oidHasChatRoom) {
        this.oidHasChatRoom = oidHasChatRoom;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public Fandom getFandom() {
        return fandom;
    }

    public void setFandom(Fandom fandom) {
        this.fandom = fandom;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }
}
