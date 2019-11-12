package ca.utoronto.utm.mcs.projectcloudinfantry.domain;

import org.neo4j.ogm.annotation.NodeEntity;

import java.util.List;

@NodeEntity
public class ChatRoom {
    private  List<Message> messages;

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
}
