package ru.practicum.service.event.service;

import ru.practicum.service.event.dto.EventFullDto;
import ru.practicum.service.event.dto.EventShortDto;
import ru.practicum.service.event.dto.NewEventDto;
import ru.practicum.service.event.dto.UpdateEventDto;
import ru.practicum.service.request.dto.RequestDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface EventService {
    List<EventShortDto> getAllEventsPublic(String text, Long[] categories, Boolean paid, String rangeStart,
                                           String rangeEnd, Boolean onlyAvailable, String sort,
                                           int from, int size, HttpServletRequest request)
            throws IllegalArgumentException;

    EventFullDto getByIdPublic(Long eventId, HttpServletRequest request);

    List<EventShortDto> getAllEventsPrivate(Long userId, int from, int size);

    EventFullDto getEventByIdPrivate(Long userId, Long eventId);

    List<RequestDto> getEventRequestsPrivate(Long userId, Long eventId);

    RequestDto confirmRequestPrivate(Long userId, Long eventId, Long requestId);

    RequestDto rejectRequestPrivate(Long userId, Long eventId, Long requestId);

    EventFullDto updateEventPrivate(Long userId, UpdateEventDto updateEventDto) throws IllegalArgumentException;

    EventFullDto addEventPrivate(Long userId, NewEventDto newEventDto) throws IllegalArgumentException;

    EventFullDto cancelEventPrivate(Long userId, Long eventId);

    List<EventFullDto> getAllByAdmin(Long[] users, String[] states, Long[] categories, String rangeStart,
                                     String rangeEnd, int from, int size);

    EventFullDto updateEventByAdmin(Long eventId, NewEventDto newEventDto);

    EventFullDto publishEventAdmin(Long eventId);

    EventFullDto rejectEventAdmin(Long eventId);

}