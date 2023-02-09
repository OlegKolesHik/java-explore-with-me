package ru.practicum.service.request.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.practicum.service.request.dto.RequestDto;
import ru.practicum.service.request.service.RequestService;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(
        value = "/users/{userId}/requests",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PrivateRequestController {
    private final RequestService requestService;

    @GetMapping
    public List<RequestDto> getAllRequests(@PathVariable Long userId) {
        log.info("Получение информации о заявках текущего пользователя на участие в чужих событиях {}", userId);
        return requestService.getAllByUserRequests(userId);
    }

    @PostMapping
    public RequestDto addRequests(@PathVariable Long userId,
                                  @RequestParam(value = "eventId", required = false) Long eventId) {
        if (eventId == null) {
            throw new IllegalArgumentException("Добавление запроса от текущего пользователя на" +
                    " участие в событии. В запросе отсутвует обязательное поле requests");
        }
        log.info("Добавление запроса от текущего пользователя на участие в событии {}", eventId);
        return requestService.addRequest(userId, eventId);
    }

    @PatchMapping("/{requestId}/cancel")
    public RequestDto cancelRequests(@PathVariable Long userId, @PathVariable Long requestId) {
        log.info("Отмена своего {} запроса на участие в событии {}", userId, requestId);
        return requestService.cancelRequest(userId, requestId);
    }
}
