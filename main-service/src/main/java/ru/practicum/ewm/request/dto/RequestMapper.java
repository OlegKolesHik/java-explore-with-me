package ru.practicum.ewm.request.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.request.model.Request;

import static ru.practicum.ewm.comments.dto.CommentMapper.DATE_FORMAT;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "event", source = "eventId")
    @Mapping(target = "requester", source = "userId")
    @Mapping(target = "status", constant = "PENDING")
    @Mapping(target = "created", ignore = true)
    Request toRequest(Long userId, Long eventId);

    @Mapping(target = "created", dateFormat = DATE_FORMAT)
    ParticipationRequestDto toParticipationRequestDto(Request request);
}
