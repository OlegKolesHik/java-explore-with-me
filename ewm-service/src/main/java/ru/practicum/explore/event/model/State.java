package ru.practicum.explore.event.model;

import java.util.Optional;

public enum State {
    PENDING,
    PUBLISHED,
    CANCELED;

    public static Optional<State> from(String strState) {
        for (State state : values()) {
            if (state.name().equalsIgnoreCase(strState)) {
                return Optional.of(state);
            }
        }
        return Optional.empty();
    }
}
