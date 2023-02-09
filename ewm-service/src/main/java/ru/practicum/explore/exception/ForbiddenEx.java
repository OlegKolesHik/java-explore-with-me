package ru.practicum.explore.exception;

import lombok.Getter;

@Getter
public class ForbiddenEx extends RuntimeException {
    public ForbiddenEx(String message) {
        super(message);
    }
}
