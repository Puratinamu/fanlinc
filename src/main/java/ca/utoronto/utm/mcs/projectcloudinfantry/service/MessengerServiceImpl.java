package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Message;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessengerServiceImpl implements MessengerService{

    public List<Message> getChatsInDm(String fromUserId, String toUserId){
        List<Message> dmChats = new ArrayList<>();
        return dmChats;
    };

    public List<Message> getChatsInFandom(String fandomId, String fandomInterestLevel){
        List<Message> fandomMessages = new ArrayList<>();
        return fandomMessages;
    };

    public void postChatToFandom(String fandomId, String fandomInterestLevel, String fromUserId, String messageContent){
        // See if this ChatRoom exists
        // if it doesn't exist, create the ChatRoom
        // get the ChatRoom
        // Create the message, post it to that ChatRoom
    };

    public void postChatToDm(String fromUserId, String toUserId, String meesageContent){

    };
}
