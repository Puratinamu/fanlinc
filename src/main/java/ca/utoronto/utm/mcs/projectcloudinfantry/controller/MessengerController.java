package ca.utoronto.utm.mcs.projectcloudinfantry.controller;
import ca.utoronto.utm.mcs.projectcloudinfantry.domain.Message;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.MessageRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.MessageRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.GetMessagesResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.MessageResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.MessengerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MessengerController {
    private final MessageRequestMapper messageRequestMapper;
    private MessengerService messengerService;

    public MessengerController(MessageRequestMapper messageRequestMapper, MessengerService messengerService) {
       this.messageRequestMapper = messageRequestMapper;
       this.messengerService = messengerService;
    }

    @RequestMapping(value = "/api/v1/messenger/dm", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<MessageResponse> postMessageDm(@RequestParam Long from, @RequestParam Long to, @Valid @RequestBody Map<String, Object> body) {
        try {
            MessageRequest msgRequest = this.messageRequestMapper.toMessageRequest(body);
            Message msg = messengerService.postChatToDm(from, to, msgRequest.getMessage());

            MessageResponse msgResponse = new MessageResponse();
            msgResponse.setFromUsername(msg.getFromUsername());
            msgResponse.setFromId(msg.getFromId());
            if(msg.getCreationTimestamp() != null) {
                msgResponse.setCreatedTimeStamp(msg.getCreationTimestamp().toString());
            }
            msgResponse.setContent(msg.getContent());
            return new ResponseEntity<>(msgResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/v1/messenger/fandom", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<MessageResponse> postMessageFandom(@RequestParam Long from, @RequestParam Long fandomId, @RequestParam String fandomInterestLevel, @Valid @RequestBody Map<String, Object> body) {
        try {
            MessageRequest message = this.messageRequestMapper.toMessageRequest(body);

            Message msg = messengerService.postChatToFandom(fandomId, fandomInterestLevel, from, message.getMessage());
            MessageResponse msgResponse = new MessageResponse();

            msgResponse.setFromUsername(msg.getFromUsername());
            msgResponse.setFromId(msg.getFromId());
            if(msg.getCreationTimestamp() != null) {
                msgResponse.setCreatedTimeStamp(msg.getCreationTimestamp().toString());
            }
            msgResponse.setContent(msg.getContent());

            return new ResponseEntity<MessageResponse>(msgResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/v1/messenger/dm", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<GetMessagesResponse> getMessagesDm(@RequestParam Long from, @RequestParam Long to) {
        try {
            List<Message> messages = this.messengerService.getChatsInDm(from, to);
            GetMessagesResponse response = new GetMessagesResponse(messages);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/v1/messenger/fandom", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<GetMessagesResponse> getMessagesFandom(@RequestParam Long fandomId, @RequestParam String fandomInterestLevel) {
        try {
            List<Message> messages = this.messengerService.getChatsInFandom(fandomId, fandomInterestLevel);
            GetMessagesResponse response = new GetMessagesResponse(messages);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException | UserNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}