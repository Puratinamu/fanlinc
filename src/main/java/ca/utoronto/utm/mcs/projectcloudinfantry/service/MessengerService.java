package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Message;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessengerService {
    List<Message> getChatsInFandom(Long fandomId, String fandomInterestLevel);
    List<Message> getChatsInDm(Long fromUserId, Long toUserId);
    Message postChatToFandom(Long fandomId, String fandomInterestLevel, Long fromUserId, String messageContent);
    void postChatToDm(Long fromUserId, Long toUserId, String meesageContent);
}
