package ru.practicum.service.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.service.event.dto.EventShortDto;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompilationDto {
    private Long id;
    private List<EventShortDto> events;
    private boolean pinned;
    private String title;
}
