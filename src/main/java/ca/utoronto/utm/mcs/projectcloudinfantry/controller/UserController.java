package ca.utoronto.utm.mcs.projectcloudinfantry.controller;


import ca.utoronto.utm.mcs.projectcloudinfantry.domain.User;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.*;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.LoginRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.RegistrationRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.RegistrationResponseMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.ProfileResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.response.RegistrationResponse;
import ca.utoronto.utm.mcs.projectcloudinfantry.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class UserController {
    private UserService userService;
    private RegistrationRequestMapper registrationRequestMapper;
    private RegistrationResponseMapper registrationResponseMapper;
    private LoginRequestMapper loginRequestMapper;

    public UserController(UserService userService, RegistrationRequestMapper registrationRequestMapper, RegistrationResponseMapper registrationResponseMapper, LoginRequestMapper loginRequestMapper) {
        this.userService = userService;
        this.registrationRequestMapper = registrationRequestMapper;
        this.registrationResponseMapper = registrationResponseMapper;
        this.loginRequestMapper = loginRequestMapper;
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
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

        @RequestMapping(value = "/api/v1/login", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity login(@RequestBody Map<String, Object> body) {
        try {
            LoginRequest request = loginRequestMapper.toLoginRequest(body);
            this.userService.loginUser(request);
            return new ResponseEntity(HttpStatus.OK);
        } catch (UserNotFoundException | IllegalArgumentException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (NotAuthorizedException e) {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/v1/getProfile", method = GET, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity getProfile(@RequestBody Map<String, Object> body) {
        // Check body args
        if (!body.containsKey("oidUser")) return new ResponseEntity(HttpStatus.BAD_REQUEST);

        // Query for user data
        Long oidUser = Long.parseLong(((Integer) body.get("oidUser")).toString());
        ProfileResponse userProfile;
        try {
            userProfile = userService.getProfile(oidUser);
        } catch (UserNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

        // Return the request
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}
