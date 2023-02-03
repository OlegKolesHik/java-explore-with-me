package ru.practicum.stat.mapper;

import ru.practicum.stat.model.EndpointHit;
import ru.practicum.stat.model.Hit;
import ru.practicum.stat.model.ViewStats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HitMapper {
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Hit toHit(EndpointHit endpointHit) {
        return Hit
                .builder()
                .id(endpointHit.getId())
                .app(endpointHit.getApp())
                .ip(endpointHit.getIp())
                .uri(endpointHit.getUri())
                .timestamp(LocalDateTime.parse(endpointHit.getTimestamp(), DATE_TIME_FORMATTER))
                .build();
    }

    public static ViewStats toViewStats(Hit hit) {
        return ViewStats
                .builder()
                .app(hit.getApp())
                .uri(hit.getUri())
                .build();
    }
}