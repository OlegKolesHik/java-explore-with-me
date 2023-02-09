package ru.practicum.explore.request.dto;


import ru.practicum.explore.request.model.Request;

import java.time.format.DateTimeFormatter;

public class RequestMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static RequestDto toRequestDto(Request request) {
        if (request == null) return null;
        else
            return new RequestDto(
                    request.getId(),
                    request.getEvent().getId(),
                    request.getCreated() == null ? null : request.getCreated().format(FORMATTER),
                    request.getRequester().getId(),
                    request.getStatus()
            );
    }
}
