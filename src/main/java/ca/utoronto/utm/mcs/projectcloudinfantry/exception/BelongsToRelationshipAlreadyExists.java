package ca.utoronto.utm.mcs.projectcloudinfantry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class BelongsToRelationshipAlreadyExists extends RuntimeException {
}
