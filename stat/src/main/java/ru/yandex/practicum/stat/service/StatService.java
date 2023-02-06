package ru.yandex.practicum.stat.service;

import ru.yandex.practicum.stat.model.EndpointHitDto;
import ru.yandex.practicum.stat.model.ViewStats;

import java.util.List;

public interface StatService {

    void saveHit(EndpointHitDto endpointHit);

    List<ViewStats> getStats(String start, String end, String[] uris, Boolean uniq);
}
