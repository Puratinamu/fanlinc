package ca.utoronto.utm.mcs.projectcloudinfantry.response;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Message;

import java.util.ArrayList;
import java.util.List;

public class GetMessagesResponse {
    private List<MessageResponse> messages = new ArrayList<MessageResponse>();

    public GetMessagesResponse(List<Message> inputMessages){
        System.out.println(inputMessages.size());
        for (Message inputMessage : inputMessages){
            MessageResponse msgResponse = new MessageResponse();
            msgResponse.setContent(inputMessage.getContent());
            msgResponse.setCreatedTimeStamp(inputMessage.getCreationTimestamp().toString());
            msgResponse.setFromId(inputMessage.getFromId());
            messages.add(msgResponse);
        }
    }

    public void setMessages(List<MessageResponse> messages) {
        this.messages = messages;
    }
    public List<MessageResponse> getMessages(){
        return this.messages;
    }
}

class MessageResponse{
    private String content;
    private Long fromId;
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
}