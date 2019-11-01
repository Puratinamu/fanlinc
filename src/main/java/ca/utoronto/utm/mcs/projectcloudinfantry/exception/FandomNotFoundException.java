package ca.utoronto.utm.mcs.projectcloudinfantry.exception;

public class FandomNotFoundException extends RuntimeException {
    public FandomNotFoundException() { }
    public FandomNotFoundException(String s) {
        super(s);
    }
}
