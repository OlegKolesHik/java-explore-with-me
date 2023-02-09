package ru.yandex.practicum.stats.service;

import ru.yandex.practicum.stats.model.EndpointHitDto;

import java.util.List;

public interface StatsService {
    void addHit(EndpointHitDto endpointHitDto);

    List<Object> getStats(String start, String end, String[] uris, Boolean unique);
}
