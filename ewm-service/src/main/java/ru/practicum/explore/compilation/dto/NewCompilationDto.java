package ru.practicum.explore.compilation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewCompilationDto {
    private Long id;
    @NotNull
    private List<Long> events;
    private boolean pinned;
    @NotBlank
    private String title;
}
