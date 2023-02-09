package ru.practicum.explore.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.user.dto.UserShortDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventShortDto {
    protected Long id;
    @NotBlank
    protected String annotation;
    @NotNull
    protected CategoryDto category;
    @PositiveOrZero
    protected int confirmedRequests;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    protected LocalDateTime eventDate;
    @NotNull
    protected UserShortDto initiator;
    protected Boolean paid;
    @NotBlank
    protected String title;
    @PositiveOrZero
    protected Integer views;
}
