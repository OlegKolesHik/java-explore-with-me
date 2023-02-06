package ru.yandex.practicum.stat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Getter
@Setter
@Builder
public class EndpointHitDto {
    private Integer id;

    private String app;

    private String uri;

    private String ip;

    private String timestamp;

}
