package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Message;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessengerService {
    List<Message> getChatsInFandom(String fandomId, String fandomInterestLevel);
    List<Message> getChatsInDm(String fromUserId, String toUserId);
    void postChatToFandom(String fandomId, String fandomInterestLevel, String fromUserId, String messageContent);
    void postChatToDm(String fromUserId, String toUserId, String meesageContent);
}
