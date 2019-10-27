package ca.utoronto.utm.mcs.projectcloudinfantry.token;

public class KeyUtilException extends RuntimeException {
    public KeyUtilException(String message, Exception ex) {
        super(message, ex);
    }
}
