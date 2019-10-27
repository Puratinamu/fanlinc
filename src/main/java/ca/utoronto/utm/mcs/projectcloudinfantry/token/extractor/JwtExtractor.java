package ca.utoronto.utm.mcs.projectcloudinfantry.token.extractor;

import ca.utoronto.utm.mcs.projectcloudinfantry.token.KeyUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class JwtExtractor implements TokenExtractor {

    // Always expect a signature since this class can only validates signed JWT.
    private final static Pattern pattern = Pattern.compile("^Bearer (?<JWT>[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_=]+\\.[A-Za-z0-9-_.+/=]+)$");

    private JwtExtractionConfig jwtExtractionConfig;

    public JwtExtractor(JwtExtractionConfig jwtExtractionConfig) {
        this.jwtExtractionConfig = jwtExtractionConfig;
    }

    @Override
    public Claims extractToken(String token) {
        String jwt = extractJwt(token);
        return Jwts.parser()
                .setSigningKey(KeyUtil.parsePublicKey(jwtExtractionConfig.getPublicKey()))
                .parseClaimsJws(jwt)
                .getBody();
    }

    private String extractJwt(String authorizationHeader) throws JwtExtractionException {
        if (StringUtils.isEmpty(authorizationHeader)) {
            throw new JwtExtractionException("Authorization header is empty");
        }

        Matcher matcher = pattern.matcher(authorizationHeader);

        if (!matcher.matches()) {
            throw new JwtExtractionException("Invalid JWT");
        }

        return matcher.group("JWT");
    }
}
