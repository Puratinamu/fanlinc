package ca.utoronto.utm.mcs.projectcloudinfantry.controller;


import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.*;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.AddContactRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.LoginRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.RegistrationRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.RegistrationResponseMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.AddContactRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.LoginResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.ProfileResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.RegistrationResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.UserContactsResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class UserController {
    private UserService userService;
    private RegistrationRequestMapper registrationRequestMapper;
    private RegistrationResponseMapper registrationResponseMapper;
    private LoginRequestMapper loginRequestMapper;
    private AddContactRequestMapper addContactRequestMapper;

    public UserController(UserService userService, RegistrationRequestMapper registrationRequestMapper, RegistrationResponseMapper registrationResponseMapper, LoginRequestMapper loginRequestMapper, AddContactRequestMapper addContactRequestMapper) {
        this.userService = userService;
        this.registrationRequestMapper = registrationRequestMapper;
        this.registrationResponseMapper = registrationResponseMapper;
        this.loginRequestMapper = loginRequestMapper;
        this.addContactRequestMapper = addContactRequestMapper;
    }

    @RequestMapping(value = "/api/v1/addUser", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RegistrationResponse> addUser(@Valid @RequestBody Map<String, Object> body) {
        try {
            RegistrationRequest registrationRequest = registrationRequestMapper.toRegisrationRequest(body);
            User user = this.userService.registerUser(registrationRequest);
            RegistrationResponse registrationResponse = registrationResponseMapper.toRegisrationReponse(user);
            return new ResponseEntity<>(registrationResponse, HttpStatus.OK);
        } catch (UserAlreadyExistsException | IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @RequestMapping(value = "/api/v1/login", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity login(@RequestBody Map<String, Object> body) {
        try {
            LoginRequest request = loginRequestMapper.toLoginRequest(body);
            LoginResponse response = this.userService.loginUser(request);
            return new ResponseEntity<>( response, HttpStatus.OK);
        } catch (UserNotFoundException | IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (NotAuthorizedException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/v1/addContact", method = PUT, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity addContact(@Valid @RequestBody Map<String, Object> body) {
        try {
            AddContactRequest addContactRequest = addContactRequestMapper.toAddContactRequest(body);
            this.userService.addContact(addContactRequest);
            return new ResponseEntity(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (BelongsToRelationshipAlreadyExists e) {
            return new ResponseEntity(HttpStatus.CONFLICT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/v1/getProfile", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getProfile(@RequestParam String oidUser) {
        try {
            ProfileResponse userProfile = userService.getProfile(oidUser);
            return new ResponseEntity<>(userProfile, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Return the request

    }

    @RequestMapping(value = "/api/v1/getContacts", method = GET, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getContacts(@RequestParam Long oidUser) {
        try {
            UserContactsResponse userContacts = userService.getContacts(oidUser);
            return new ResponseEntity<>(userContacts, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
