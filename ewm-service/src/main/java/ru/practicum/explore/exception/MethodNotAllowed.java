package ru.practicum.explore.exception;

import lombok.Getter;

@Getter
public class MethodNotAllowed extends RuntimeException {
    public MethodNotAllowed(String message) {
        super(message);
    }
}


