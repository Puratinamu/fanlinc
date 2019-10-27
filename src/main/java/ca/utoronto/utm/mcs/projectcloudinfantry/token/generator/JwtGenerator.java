package ca.utoronto.utm.mcs.projectcloudinfantry.token.generator;

import ca.utoronto.utm.mcs.projectcloudinfantry.token.KeyUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
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
