package ru.practicum.explore.request.service;

import ru.practicum.explore.request.dto.RequestDto;

import java.util.List;

public interface RequestService {
    List<RequestDto> getAllByUserRequests(Long userId);

    RequestDto addRequest(Long userId, Long eventId);

    RequestDto cancelRequest(Long userId, Long requestId);
}
