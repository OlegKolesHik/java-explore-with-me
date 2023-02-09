package ru.practicum.explore.event.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.EventShortDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.dto.UpdateEventDto;
import ru.practicum.explore.event.service.EventService;
import ru.practicum.explore.request.dto.RequestDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(
        value = "/users",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PrivateEventController {
    private final EventService eventService;

    @GetMapping("/{userId}/events")
    public List<EventShortDto> getAllEventsByUser(@PathVariable Long userId,
                                                  @PositiveOrZero @RequestParam(value = "from", defaultValue = "0")
                                                  Integer from,
                                                  @Positive @RequestParam(value = "size", defaultValue = "10")
                                                  Integer size) {
        log.info("Запрос получение событий для инициатора {}", userId);
        return eventService.getAllEventsPrivate(userId, from, size);
    }

    @PatchMapping("/{userId}/events")
    public EventFullDto updateEvent(@PathVariable Long userId, @Valid @RequestBody UpdateEventDto updateEventDto) {
        log.info("Запрос на обновление события {}", updateEventDto);
        return eventService.updateEventPrivate(userId, updateEventDto);
    }

    @PostMapping("/{userId}/events")
    public EventFullDto addEvents(@PathVariable Long userId, @Valid @RequestBody NewEventDto newEventDto) {
        log.info("Запрос на создание события {}", newEventDto);
        return eventService.addEventPrivate(userId, newEventDto);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public EventFullDto getById(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Запрос получение события по id {} для инициатора {}", eventId, userId);
        return eventService.getEventByIdPrivate(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventFullDto cancelEventById(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Запрос пользователя {} на отмену события {}", eventId, userId);
        return eventService.cancelEventPrivate(userId, eventId);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<RequestDto> getEventRequests(@PathVariable Long userId, @PathVariable Long eventId) {
        log.info("Запрос пользователя {} на на заявки на событие {}", eventId, userId);
        return eventService.getEventRequestsPrivate(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/confirm")
    public RequestDto confirmEventRequest(@PathVariable Long userId, @PathVariable Long eventId,
                                          @PathVariable Long reqId) {
        log.info("Подтверждение пользователем {} запроса {} на участие в событии {}", eventId, userId, reqId);
        return eventService.confirmRequestPrivate(userId, eventId, reqId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests/{reqId}/reject")
    public RequestDto rejectEventRequest(@PathVariable Long userId,
                                         @PathVariable Long eventId,
                                         @PathVariable Long reqId) {
        log.info("Отклонение пользователем {} запроса {} на участие в событии {}", eventId, userId, reqId);
        return eventService.rejectRequestPrivate(userId, eventId, reqId);
    }
}
