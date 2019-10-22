package ca.utoronto.utm.mcs.projectcloudinfantry.controller;

import ca.utoronto.utm.mcs.projectcloudinfantry.exception.FandomNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.NotAuthorizedException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserAlreadyExistsException;
import ca.utoronto.utm.mcs.projectcloudinfantry.exception.UserNotFoundException;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.LoginRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.RegistrationRequestMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.mapper.UserMapper;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.LoginRequest;
import ca.utoronto.utm.mcs.projectcloudinfantry.request.RegistrationRequest;
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

@RestController
public class UserController {
    private UserService userService;
    private UserMapper userMapper;
    private RegistrationRequestMapper registrationRequestMapper;
    private LoginRequestMapper loginRequestMapper;

    public UserController(UserService userService, UserMapper userMapper, RegistrationRequestMapper registrationRequestMapper, LoginRequestMapper loginRequestMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.registrationRequestMapper = registrationRequestMapper;
        this.loginRequestMapper = loginRequestMapper;
    }

    @RequestMapping(value = "/api/v1/addUser", method = POST, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<RegistrationResponse> addUser(@Valid @RequestBody Map<String, Object> body) {
        try {
            RegistrationRequest request = registrationRequestMapper.toRegisrationRequest(body);
            this.userService.registerUser(request);
            return new ResponseEntity<>(new RegistrationResponse(request), HttpStatus.OK);
        } catch (UserAlreadyExistsException | IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (FandomNotFoundException e) {
            e.printStackTrace();
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
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (NotAuthorizedException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}