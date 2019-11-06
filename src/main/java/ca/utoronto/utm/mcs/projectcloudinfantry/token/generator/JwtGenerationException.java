package ca.utoronto.utm.mcs.projectcloudinfantry.token.generator;

public class JwtGenerationException extends RuntimeException {
    public JwtGenerationException(String message, Exception e) {
        super(message, e);
    }
}
