package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.ChatRoom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Message;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.FandomToChatRoom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToChatRoom;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessengerServiceImpl implements MessengerService{

    private ChatRoomRepository chatRoomRepository;
    private MessageRepository messageRepository;
    private UserToChatRoomRepository userToChatRoomRepository;
    private FandomToChatRoomRepository fandomToChatRoomRepository;
    private UserRepository userRepository;
    private FandomRepository fandomRepository;

    public MessengerServiceImpl(FandomRepository fandomRepository, ChatRoomRepository chatRoomRepository, MessageRepository messageRepository, UserToChatRoomRepository userToChatRoomRepository, FandomToChatRoomRepository fandomToChatRoomRepository, UserRepository userRepository) {

        this.chatRoomRepository = chatRoomRepository;
        this.messageRepository = messageRepository;
        this.userToChatRoomRepository = userToChatRoomRepository;
        this.fandomToChatRoomRepository = fandomToChatRoomRepository;
        this.userRepository = userRepository;
        this.fandomRepository = fandomRepository;
    }

    public List<Message> getChatsInDm(Long fromUserId, Long toUserId){
        List<Message> dmChats = new ArrayList<>();
        return dmChats;
    };

    public List<Message> getChatsInFandom(Long fandomId, String fandomInterestLevel){
        List<Message> fandomMessages = new ArrayList<>();
        return fandomMessages;
    };

    private void createUserToFandomRelationship(long fromUserId, ChatRoom room, String fandomInterestLevel){
        Optional<User> user = this.userRepository.findById(fromUserId);
        UserToChatRoom userToChatRoomRelationship = new UserToChatRoom();
        userToChatRoomRelationship.setChatRoom(room);
        userToChatRoomRelationship.setRelationship(fandomInterestLevel);
        userToChatRoomRelationship.setUser(user.get());
        this.userToChatRoomRepository.save(userToChatRoomRelationship);
    }

    private void createFandomToChatRoomRelationship(ChatRoom room, long fandomId, String fandomInterestLevel){
        Optional<Fandom> fandom = this.fandomRepository.findById(fandomId);
        FandomToChatRoom fandomToChatRoom = new FandomToChatRoom();
        fandomToChatRoom.setChatRoom(room);
        fandomToChatRoom.setFandom(fandom.get());
        fandomToChatRoom.setRelationship(fandomInterestLevel);
        this.fandomToChatRoomRepository.save(fandomToChatRoom);
        this.chatRoomRepository.save(room);
    }

    public void postChatToFandom(Long fandomId, String fandomInterestLevel, Long fromUserId, String messageContent){
        // See if a chatroom for this fandom exists at this level, if it doesn't create it.
        ChatRoom room = this.chatRoomRepository.getChatRoomForFandomByInterestLevel(fandomId, fandomInterestLevel);
        if(room == null){
            room = new ChatRoom();
            createFandomToChatRoomRelationship(room, fandomId, fandomInterestLevel);
            createUserToFandomRelationship(fromUserId, room, fandomInterestLevel);

        } else if(userRepository.findUserByFandomChatRoom(fromUserId, fandomInterestLevel, fandomId) == null){
            // If the user who posted the message doesn't have a "IN_CHAT" relationship to the chatroom, create the relationship
            createUserToFandomRelationship(fromUserId, room, fandomInterestLevel);
        }

        // Create the msg to be save to the repository.
        Message msg = new Message();
        msg.setChatRoom(room);
        msg.setContent(messageContent);
        msg.setFromId(fromUserId);
        Date date = new Date();
        msg.setCreationTimestamp(date);

        this.messageRepository.save(msg);

    };

    public void postChatToDm(Long fromUserId, Long toUserId, String meesageContent){

    };
}
