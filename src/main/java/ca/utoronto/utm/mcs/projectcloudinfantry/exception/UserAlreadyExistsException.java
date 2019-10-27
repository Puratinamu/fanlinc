package ca.utoronto.utm.mcs.projectcloudinfantry.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {};
    public UserAlreadyExistsException(String s) {
        super(s);
    }
}