package ca.utoronto.utm.mcs.projectcloudinfantry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Text post with this id was not found")
public class TextPostNotFoundException extends RuntimeException {
}