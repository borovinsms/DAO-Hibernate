package ru.netology.task8ormhibernate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundPersonException extends RuntimeException {

    public NotFoundPersonException(String message) {
        super(message);
    }
}
