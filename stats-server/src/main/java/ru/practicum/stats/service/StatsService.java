package ru.practicum.stats.service;

import ru.practicum.stats.model.EndpointHit;

import java.util.List;

public interface StatsService {
    void addHit(EndpointHit endpointHit);

    List<Object> getStats(String start, String end, String[] uris, Boolean unique);
}