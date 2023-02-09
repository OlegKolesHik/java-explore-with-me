package ru.practicum.service.event.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.service.client.BaseClient;
import ru.practicum.service.event.dto.EventFullDto;
import ru.practicum.service.event.dto.EventShortDto;
import ru.practicum.service.event.service.EventService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(
        value = "/events",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PublicEventController {
    private final EventService eventService;
    private final BaseClient baseClient;

    @GetMapping
    public ResponseEntity<List<EventShortDto>> getAllPublicEvents(
            @RequestParam(name = "text", defaultValue = "") String text,
            @RequestParam(name = "categories", required = false) Long[] categories,
            @RequestParam(name = "paid", required = false) Boolean paid,
            @RequestParam(name = "rangeStart", defaultValue = "") String rangeStart,
            @RequestParam(name = "rangeEnd", defaultValue = "") String rangeEnd,
            @RequestParam(name = "onlyAvailable", defaultValue = "false") Boolean onlyAvailable,
            @RequestParam(name = "sort", required = false) String sort,
            @PositiveOrZero @RequestParam(value = "from", defaultValue = "0") int from,
            @Positive @RequestParam(value = "size", defaultValue = "10") int size,
            HttpServletRequest request) throws IllegalArgumentException {

        log.info("Запрошен список событий");
        return new ResponseEntity<>(eventService.getAllEventsPublic(
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, request), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public EventFullDto getEventsById(@PathVariable(name = "id") Long eventId, HttpServletRequest request) {
        log.info("Запрошено событие id: {}", eventId);
        return eventService.getByIdPublic(eventId, request);
    }
}
