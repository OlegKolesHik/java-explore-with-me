package ru.practicum.stat.service;

import ru.practicum.stat.model.EndpointHit;
import ru.practicum.stat.model.ViewStats;

import java.util.List;

public interface StatService {

    void saveHit(EndpointHit endpointHit);

    List<ViewStats> getStats(String start, String end, String[] uris, Boolean uniq);
}