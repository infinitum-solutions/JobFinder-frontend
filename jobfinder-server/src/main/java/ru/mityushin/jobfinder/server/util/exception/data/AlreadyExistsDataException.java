package ru.mityushin.jobfinder.server.util.exception.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.mityushin.jobfinder.server.util.exception.ServerException;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsDataException extends ServerException {
}
