package ca.utoronto.utm.mcs.projectcloudinfantry.service;

import ca.utoronto.utm.mcs.projectcloudinfantry.domain.ChatRoom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Fandom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Message;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.FandomToChatRoom;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.relationships.UserToChatRoom;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MessengerServiceImpl implements MessengerService {

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

    public List<Message> getChatsInDm(Long fromUserId, Long toUserId) {
        List<Message> dmChats = this.messageRepository.getMessagesForDM(fromUserId, toUserId);
        return dmChats;
    }

    ;

    public List<Message> getChatsInFandom(Long fandomId, String fandomInterestLevel) {
        List<Message> fandomMessages = this.messageRepository.getMessagesFromAFandomChatRoom(fandomId, fandomInterestLevel);
        return fandomMessages;
    }

    ;

    private void createUserToChatRoomRelationship(User user, ChatRoom room, String interestLevel) {
        UserToChatRoom userToChatRoomRelationship = new UserToChatRoom();
        userToChatRoomRelationship.setChatRoom(room);
        userToChatRoomRelationship.setRelationship(interestLevel);
        userToChatRoomRelationship.setUser(user);
        this.userToChatRoomRepository.save(userToChatRoomRelationship);
    }

    private void createFandomToChatRoomRelationship(ChatRoom room, Fandom fandom, String fandomInterestLevel) {

        FandomToChatRoom fandomToChatRoom = new FandomToChatRoom();
        fandomToChatRoom.setChatRoom(room);
        fandomToChatRoom.setFandom(fandom);
        fandomToChatRoom.setRelationship(fandomInterestLevel);
        this.fandomToChatRoomRepository.save(fandomToChatRoom);
        this.chatRoomRepository.save(room);
    }

    public Message postChatToFandom(Long fandomId, String fandomInterestLevel, Long fromUserId, String messageContent) {
        // Upfront Validation check if fandom exists, fromUser exists.
        Optional<Fandom> fandom = this.fandomRepository.findById(fandomId);
        if (!fandom.isPresent()) {
            throw new FandomNotFoundException();
        }
        Optional<User> user = this.userRepository.findById(fromUserId);
        if (!user.isPresent()) {
            throw new UserNotFoundException();
        }

        // See if a chatroom for this fandom exists at this level, if it doesn't create it.
        ChatRoom room = this.chatRoomRepository.getChatRoomForFandomByInterestLevel(fandomId, fandomInterestLevel);
        if (room == null) {
            room = new ChatRoom();
            createFandomToChatRoomRelationship(room, fandom.get(), fandomInterestLevel);
            createUserToChatRoomRelationship(user.get(), room, fandomInterestLevel);

        } else if (userRepository.findUserByFandomChatRoom(fromUserId, fandomInterestLevel, fandomId) == null) {
            // If the user who posted the message doesn't have a "IN_CHAT" relationship to the chatroom, create the relationship
            createUserToChatRoomRelationship(user.get(), room, fandomInterestLevel);
        }

        // Create the msg to be save to the repository.
        Message msg = new Message();
        msg.setChatRoom(room);
        msg.setContent(messageContent);
        Date creationDate = new Date();
        msg.setCreationTimestamp(creationDate);
        msg.setFromId(fromUserId);
        msg.setFromUsername(user.get().getUsername());

        this.messageRepository.save(msg);
        return msg;
    }

    ;

    public Message postChatToDm(Long fromUserId, Long toUserId, String messageContent) {
        Optional<User> userFrom = this.userRepository.findById(fromUserId);
        if (!userFrom.isPresent()) {
            throw new UserNotFoundException();
        }

        Optional<User> userTo = this.userRepository.findById(toUserId);
        if (!userTo.isPresent()) {
            throw new UserNotFoundException();
        }
        ChatRoom room = this.chatRoomRepository.getChatRoomForDm(fromUserId, toUserId);
        if (room == null) {
            room = new ChatRoom();
            createUserToChatRoomRelationship(userFrom.get(), room, "dm");
            createUserToChatRoomRelationship(userTo.get(), room, "dm");
        }

        // Create the msg to be save to the repository.
        Message msg = new Message();
        msg.setChatRoom(room);
        msg.setContent(messageContent);
        Date creationDate = new Date();
        msg.setCreationTimestamp(creationDate);
        msg.setFromId(fromUserId);
        msg.setFromUsername(userFrom.get().getUsername());

        this.messageRepository.save(msg);
        return msg;
    }

}
