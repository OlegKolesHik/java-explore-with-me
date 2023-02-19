package ru.practicum.ewm.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ru.practicum.ewm.comments.dto.CommentMapper.DATE_FORMAT;

@RequiredArgsConstructor
@Getter
public class ErrorResponseService {
    private final String message;
    private final String status;
    private final String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATE_FORMAT));
}
