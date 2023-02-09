package ru.practicum.service.event.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.service.category.dto.CategoryMapper;
import ru.practicum.service.event.model.Event;
import ru.practicum.service.event.model.State;
import ru.practicum.service.request.repository.RequestRepositoryJpa;
import ru.practicum.service.user.dto.UserMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class EventMapper {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private final RequestRepositoryJpa requestRepositoryJpa;

    public EventFullDto toEventFullDto(Event event) {
        if (event == null) return null;
        else
            return new EventFullDto(
                    event.getId(),
                    event.getAnnotation(),
                    CategoryMapper.toCategoryDto(event.getCategory()),
                    requestRepositoryJpa.getConfirmed(event.getId()),
                    event.getEventDate(),
                    UserMapper.toUserShortDto(event.getInitiator()),
                    event.getPaid(),
                    event.getTitle(),
                    event.getViews() == null ? 0 : event.getViews(),
                    event.getCreatedOn(),
                    event.getDescription(),
                    event.getLocation(),
                    event.getParticipantLimit(),
                    event.getPublishetOn(),
                    event.getRequestModeration(),
                    event.getState().toString()
            );
    }

    public EventShortDto toEventShortDto(Event event) {
        if (event == null) return null;
        else
            return new EventShortDto(
                    event.getId(),
                    event.getAnnotation(),
                    CategoryMapper.toCategoryDto(event.getCategory()),
                    requestRepositoryJpa.getConfirmed(event.getId()),
                    event.getEventDate(),
                    UserMapper.toUserShortDto(event.getInitiator()),
                    event.getPaid(),
                    event.getTitle(),
                    event.getViews() == null ? 0 : event.getViews()
            );
    }


    public Event toEvent(NewEventDto newEventDto) {
        if (newEventDto == null) return null;
        else
            return new Event(
                    0L,
                    newEventDto.getAnnotation(),
                    newEventDto.getTitle(),
                    newEventDto.getPaid(),
                    null,
                    newEventDto.getEventDate(),
                    LocalDateTime.now(),
                    newEventDto.getDescription(),
                    newEventDto.getParticipantLimit(),
                    newEventDto.getRequestModeration(),
                    State.PENDING,
                    newEventDto.getLocation(),
                    null,
                    null,
                    null,
                    0
            );
    }
}
