package ca.utoronto.utm.mcs.projectcloudinfantry.token.extractor;

import io.jsonwebtoken.Claims;

public interface TokenExtractor {

    Claims extractToken(String token);

}
