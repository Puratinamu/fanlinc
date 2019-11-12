package ca.utoronto.utm.mcs.projectcloudinfantry.controller;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.Map;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class MessengerController {

    //?from={fromOidUser}&to={toOidUser}
    @RequestMapping(value = "/api/v1/messenger/dm", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postMessageDm(@RequestParam String from, @RequestParam String to, @Valid @RequestBody Map<String, Object> body) {
        try {
            return new ResponseEntity<String>("posted message", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //?from={fromOidUser}&fandomId={idOfTargetFandom}
    @RequestMapping(value = "/api/v1/messenger/fandom", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> postMessageFandom(@RequestParam String from, @RequestParam String fandomId, @Valid @RequestBody Map<String, Object> body) {
        try {
            return new ResponseEntity<String>("posted message", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //?from={fromOidUser}&fandomId={idOfTargetFandom}
    @RequestMapping(value = "/api/v1/messenger/dm", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getMessagesDm(@RequestParam String from, @RequestParam String to) {
        try {
            return new ResponseEntity<String>("posted message", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //?from={fromOidUser}&fandomId={idOfTargetFandom}
    @RequestMapping(value = "/api/v1/messenger/fandom", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getMessagesFandom(@RequestParam String fandomId) {
        try {
            return new ResponseEntity<String>("posted message", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}