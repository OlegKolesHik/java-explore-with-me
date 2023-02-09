package ru.practicum.explore.exception;

import lombok.Getter;

@Getter
public class NotFoundEx extends RuntimeException {
    public NotFoundEx(String name, Long message) {
        super("Event with " + name + "=" + message + " was not found.");
    }
}
