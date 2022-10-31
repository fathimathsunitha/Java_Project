package com.example.Task1.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidNameException extends RuntimeException {
    private final String name;

    public InvalidNameException(String name) {
        super(String.format("'%s' does not contain a space. Requires firstname and lastname with a separator space.", name));
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

