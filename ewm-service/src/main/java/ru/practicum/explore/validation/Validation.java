package ru.practicum.explore.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.explore.category.repository.CategoryRepositoryJpa;
import ru.practicum.explore.compilation.repository.CompilationRepositoryJpa;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.State;
import ru.practicum.explore.event.repository.EventRepositoryJpa;
import ru.practicum.explore.exception.NotFoundEx;
import ru.practicum.explore.request.repository.RequestRepositoryJpa;
import ru.practicum.explore.user.repository.UserRepositoryJpa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class Validation {
    private final UserRepositoryJpa userRepository;
    private final EventRepositoryJpa eventRepositoryJpa;
    private final CategoryRepositoryJpa categoryRepository;
    private final RequestRepositoryJpa requestRepositoryJpa;
    private final CompilationRepositoryJpa compilationRepositoryJpa;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void validateDate(LocalDateTime start, LocalDateTime end) throws IllegalArgumentException {
        if (end.isBefore(start)) {
            throw new IllegalArgumentException("Дата завершения раньше чем дата старта");
        }
    }

    public void validateEventDate(LocalDateTime eventDate) throws IllegalArgumentException {
        if (eventDate.isBefore(LocalDateTime.now().plusHours(2))) {
            throw new IllegalArgumentException("Обратите внимание: дата и время на которые намечено событие не может" +
                    " быть раньше, чем через два часа от текущего момента");
        }
    }

    public void validateEventOwnerState(Long userId, Long eventId) throws IllegalArgumentException {

        if (!eventRepositoryJpa.findById(eventId).get().getInitiator().getId().equals(userId)) {
            throw new IllegalArgumentException("Попытка именения события добавленного не текущим пользователем");
        }
    }

    public void validateEventStatePendingOrCanceled(Long eventId) throws IllegalArgumentException {
        Event event = eventRepositoryJpa.findById(eventId).get();
        if (event.getState() != State.PENDING && event.getState() != State.CANCELED) {
            throw new IllegalArgumentException("Изменить можно только отмененные события или события в состоянии" +
                    " ожидания модерации");
        }
    }

    public void validateEventOwner(Long userId, Long eventId) throws IllegalArgumentException {
        if (!eventRepositoryJpa.findById(eventId).get().getInitiator().getId().equals(userId)) {
            throw new IllegalArgumentException("Вы не являетесь инициатором события");
        }
    }

    public void validateEventStatePending(Long eventId) throws IllegalArgumentException {
        Event event = eventRepositoryJpa.findById(eventId).get();
        if (event.getState() != State.PENDING) {
            throw new IllegalArgumentException("Отменить можно только событие в состоянии ожидания модерации");
        }
    }

    public void validateCompilation(Long compilationId) throws NotFoundEx {
        if (compilationRepositoryJpa.findById(compilationId).isEmpty()) {
            throw new NotFoundEx("compilationId", compilationId);
        }
    }

    public void validateUser(Long userId) throws NotFoundEx {
        if (userRepository.findById(userId).isEmpty()) {
            throw new NotFoundEx("userId", userId);
        }
    }

    public void validateRequest(Long requestId) throws NotFoundEx {
        if (requestRepositoryJpa.findById(requestId).isEmpty()) {
            throw new NotFoundEx("requestId", requestId);
        }
    }

    public void validateCategory(Long categoryId) throws NotFoundEx {
        if (categoryRepository.findById(categoryId).isEmpty()) {
            throw new NotFoundEx("categoryId", categoryId);
        }
    }

    public void validateEvent(Long eventId) throws NotFoundEx {
        if (eventRepositoryJpa.findById(eventId).isEmpty()) {
            throw new NotFoundEx("eventId", eventId);
        }
    }

    public void sort(String sort) throws IllegalArgumentException {
        if (!sort.equals("EVENT_DATE") || !sort.equals("VIEWS")) {
            throw new IllegalArgumentException("Wrong sort");
        }
    }

    public void validatePagination(int from, int size) throws IllegalArgumentException {
        if (from < 0) {
            throw new IllegalArgumentException("Argument from incorrect");
        } else if (size <= 0) {
            throw new IllegalArgumentException("Argument size incorrect");
        }
    }
}
