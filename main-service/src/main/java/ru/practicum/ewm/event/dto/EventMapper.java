package ru.practicum.ewm.event.dto;

import org.mapstruct.*;
import ru.practicum.ewm.categories.dto.CategoryMapper;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.comments.dto.CommentMapper;
import ru.practicum.ewm.event.model.Event;
import ru.practicum.ewm.user.dto.UserMapper;
import ru.practicum.ewm.user.model.User;

import static ru.practicum.ewm.comments.dto.CommentMapper.DATE_FORMAT;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {CategoryMapper.class, UserMapper.class, CommentMapper.class})
public interface EventMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "paid", defaultValue = "false")
    @Mapping(target = "participantLimit", defaultValue = "0")
    @Mapping(target = "requestModeration", defaultValue = "true")
    @Mapping(target = "state", constant = "PENDING")
    @Mapping(target = "category", source = "categories")
    @Mapping(target = "initiator", source = "initiator")
    Event toEvent(NewEventDto newEventDto, User initiator, Category categories);

    @Mapping(target = "eventDate", dateFormat = DATE_FORMAT)
    @Mapping(target = "createdOn", dateFormat = DATE_FORMAT)
    @Mapping(target = "publishedOn", dateFormat = DATE_FORMAT)
    @Mapping(target = "category", source = "event.category")
    @Mapping(target = "initiator", source = "event.initiator")
    @Mapping(target = "comments", source = "event.comments")
    @Mapping(target = "confirmedRequests", source = "confirmedRequests")
    EventFullDto toEventFullDto(Event event, int confirmedRequests);

    @Mapping(target = "eventDate", dateFormat = DATE_FORMAT)
    EventShortDto toEventShortDto(Event event);

    @Mapping(target = "category", source = "categories")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event updateEvent(UpdateEventUserRequest updateEventUserRequest, @MappingTarget Event event, Category categories);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Event updateEvent(AdminUpdateEventRequest adminUpdateEventRequest, @MappingTarget Event event);
}
