package ca.utoronto.utm.mcs.projectcloudinfantry.token.generator;

import ca.utoronto.utm.mcs.projectcloudinfantry.token.key.KeyUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class JwtGenerator implements TokenGenerator {

    private final JwtGenerationConfig jwtGenerationConfig;

    public JwtGenerator(JwtGenerationConfig jwtGenerationConfig) {
        this.jwtGenerationConfig = jwtGenerationConfig;
    }

    private static final long timeToLive = 60 * 60 * 24; // 24hrs expiry

    @Override
    public String generateToken(Long oidUser, Map<String, Object> claims) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.forName(jwtGenerationConfig.getAlgorithm());
        Date issuedAt = Date.from(Instant.now());
        Date expiry = Date.from(Instant.now().plusMillis(timeToLive * 1000));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(oidUser.toString())
                .setIssuedAt(issuedAt)
                .setExpiration(expiry)
                .signWith(signatureAlgorithm, KeyUtil.parsePrivateKey(jwtGenerationConfig.getSecretKey()))
                .compact();
    }
}
