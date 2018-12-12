package ru.mityushin.jobfinder.server.util.exception.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.mityushin.jobfinder.server.util.exception.ServerException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing required parameter(s)")
public class RequiredParametersDataException extends ServerException {
}
