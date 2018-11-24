package ru.mityushin.jobfinder.server.util.exception.data;

import ru.mityushin.jobfinder.server.util.exception.data.DataCreateException;

public class UserAlreadyExistsException extends DataCreateException {

    private static final String MESSAGE = "This username is already in use";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
