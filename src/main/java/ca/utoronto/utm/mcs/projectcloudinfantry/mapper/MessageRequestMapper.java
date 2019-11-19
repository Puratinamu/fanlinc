package ca.utoronto.utm.mcs.projectcloudinfantry.mapper;

import ca.utoronto.utm.mcs.projectcloudinfantry.request.MessageRequest;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MessageRequestMapper {
    public MessageRequest toMessageRequest(Map<String, Object> requestBody) throws IllegalArgumentException{
        MessageRequest request = new MessageRequest();
        if(requestBody.containsKey("message")){
            request.setMessage(requestBody.get("message").toString());
        } else {
            throw new IllegalArgumentException();
        }
        return request;
    }
}
