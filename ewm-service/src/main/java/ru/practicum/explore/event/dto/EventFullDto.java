package ru.practicum.explore.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.event.model.Location;
import ru.practicum.explore.user.dto.UserShortDto;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventFullDto extends EventShortDto {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
    private String description;
    private Location location;

    private int participantLimit;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private String state;

    public EventFullDto(Long id, String annotation, CategoryDto category, int confirmedRequests,
                        LocalDateTime eventDate, UserShortDto initiator, Boolean paid, String title, int views,
                        LocalDateTime createdOn, String description, Location location, int participantLimit,
                        LocalDateTime publishedOn, Boolean requestModeration, String state) {
        super(id, annotation, category, confirmedRequests, eventDate, initiator, paid, title, views);
        this.createdOn = createdOn;
        this.description = description;
        this.location = location;
        this.participantLimit = participantLimit;
        this.publishedOn = publishedOn;
        this.requestModeration = requestModeration;
        this.state = state;
    }
}
