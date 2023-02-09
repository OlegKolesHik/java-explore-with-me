package ru.practicum.explore.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.request.model.Status;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
    private Long id;
    private Long event;
    String created;
    private Long requester;
    private Status status;
}

