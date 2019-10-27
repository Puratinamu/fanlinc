package ca.utoronto.utm.mcs.projectcloudinfantry.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class JwtGenerator implements TokenGenerator {

    @Value("${jwt.token-validity}")
    private static long tokenValidity = 60 * 60 * 24; // 24hrs expiry

    @Value("${jwt.private-key}")
    private static Key privateKey;

    @Override
    public String generateToken(Long oidUser, Map<String, Object> claims) {

        Date issuedAt = Date.from(Instant.now());
        Date expiry = Date.from(Instant.now().plusMillis(tokenValidity * 1000));

        return Jwts.builder()
                .setSubject(oidUser.toString())
                .setClaims(claims)
                .setIssuedAt(issuedAt)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

}
