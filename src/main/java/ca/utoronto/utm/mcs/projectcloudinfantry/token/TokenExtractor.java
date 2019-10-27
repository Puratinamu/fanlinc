package ca.utoronto.utm.mcs.projectcloudinfantry.token;

import io.jsonwebtoken.Claims;

public interface TokenExtractor {

    Claims extractToken(String token);

}
