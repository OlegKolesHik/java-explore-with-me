package ru.practicum.ewm.comments.dto;

import org.mapstruct.*;
import ru.practicum.ewm.comments.model.Comment;
import ru.practicum.ewm.user.model.User;

@Mapper (componentModel = "spring")
public interface CommentMapper {
    static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "author")
    @Mapping(target = "event", source = "eventId")
    @Mapping(target = "status", constant = "PENDING")
    Comment toComment(NewUpdateCommentDto newUpdateCommentDto, User author, Long eventId);

    @Mapping(target = "created", dateFormat = DATE_FORMAT)
    CommentShortDto toCommentShortDto(Comment comment);

    @Mapping(target = "created", dateFormat = DATE_FORMAT)
    CommentFullDto toCommentFullDto(Comment comment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Comment toUpdateComment(NewUpdateCommentDto newUpdateCommentDto, @MappingTarget Comment comment);
}
