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
            msgResponse.setFromUsername(inputMessage.getFromUsername());
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

