package ru.practicum.explore.event.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.client.BaseClient;
import ru.practicum.explore.event.dto.EventFullDto;
import ru.practicum.explore.event.dto.NewEventDto;
import ru.practicum.explore.event.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(
        value = "/admin/events",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AdminEventController {
    private final EventService eventService;
    private final BaseClient baseClient;

    @GetMapping
    public List<EventFullDto> getAllEvents(
            @RequestParam(name = "users", required = false) Long[] users,
            @RequestParam(name = "states", required = false) String[] states,
            @RequestParam(name = "categories", required = false) Long[] categories,
            @RequestParam(name = "rangeStart", defaultValue = "") String rangeStart,
            @RequestParam(name = "rangeEnd", defaultValue = "") String rangeEnd,
            @PositiveOrZero @RequestParam(value = "from", defaultValue = "0") int from,
            @Positive @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request) {
        baseClient.addHit(request);
        log.info("Администратором запрошен список событий");
        return eventService.getAllByAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PutMapping("/{eventId}")
    public EventFullDto updateEvent(@PathVariable Long eventId, @RequestBody NewEventDto newEventDto) {
        return eventService.updateEventByAdmin(eventId, newEventDto);
    }

    @PatchMapping("/{eventId}/publish")
    public EventFullDto publishEvent(@PathVariable Long eventId) {
        return eventService.publishEventAdmin(eventId);
    }

    @PatchMapping("/{eventId}/reject")
    public EventFullDto rejectEvent(@PathVariable Long eventId) {
        return eventService.rejectEventAdmin(eventId);
    }
}
