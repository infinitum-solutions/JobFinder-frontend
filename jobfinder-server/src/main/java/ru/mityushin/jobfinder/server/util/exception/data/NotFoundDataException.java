package ru.mityushin.jobfinder.server.util.exception.data;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.mityushin.jobfinder.server.util.exception.ServerException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundDataException extends ServerException {
}
