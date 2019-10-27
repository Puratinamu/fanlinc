package ca.utoronto.utm.mcs.projectcloudinfantry.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;

@Component
public class JwtExtractor implements TokenExtractor {

    @Value("${jwt.public-key}")
    private static Key publicKey;

    @Override
    public Claims extractToken(String token) {
        return Jwts.parser()
                .setSigningKey(publicKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
