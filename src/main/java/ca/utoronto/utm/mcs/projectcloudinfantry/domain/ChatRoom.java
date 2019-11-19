package ca.utoronto.utm.mcs.projectcloudinfantry.domain;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.List;

@NodeEntity
public class ChatRoom{
    @Id
    @GeneratedValue
    private Long oidChatRoom;

    @Relationship(type = "FROM_CHAT", direction = Relationship.INCOMING)
    private  List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
