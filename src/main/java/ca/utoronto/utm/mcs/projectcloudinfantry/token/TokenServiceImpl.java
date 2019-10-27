package ca.utoronto.utm.mcs.projectcloudinfantry.token;

import io.jsonwebtoken.Claims;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TokenServiceImpl {

    private final TokenGenerator tokenGenerator;
    private final TokenExtractor tokenExtractor;

    public TokenServiceImpl(TokenGenerator tokenGenerator, TokenExtractor tokenExtractor) {
        this.tokenGenerator = tokenGenerator;
        this.tokenExtractor = tokenExtractor;
    }

    public String generateToken(Long oidUser, Map<String, Object> claims) {
        return tokenGenerator.generateToken(oidUser, claims);
    }

    public Claims extractToken(String token) {
        return tokenExtractor.extractToken(token);
    }

    public boolean authenticate(String token, Long oidUser) {
        Claims claims = extractToken(token);
        return Long.valueOf(claims.getSubject()).equals(oidUser);
    }

}
