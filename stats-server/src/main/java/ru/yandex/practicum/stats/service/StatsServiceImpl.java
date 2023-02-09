package ru.yandex.practicum.stats.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.stats.model.EndpointHitDto;
import ru.yandex.practicum.stats.model.StatsMapper;
import ru.yandex.practicum.stats.repository.StatsRepository;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepository statsRepository;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void addHit(EndpointHitDto endpointHitDto) {
        statsRepository.save(endpointHitDto);
    }

    @Override
    public List<Object> getStats(String stringStart, String stringEnd, String[] uris, Boolean unique) {
        LocalDateTime start = LocalDateTime.parse(stringStart, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(stringEnd, FORMATTER);
        List<List<Object>> hits = new ArrayList<>();
        if ((uris != null) & unique) {
            hits = statsRepository.findAllByUrisAndUnique(start, end, uris);
        } else if (uris != null && !unique) {
            hits = statsRepository.findAllByUris(start, end, uris);
        } else if (uris == null && unique) {
            hits = statsRepository.findAllByUnique(start, end);
        } else {
            hits = statsRepository.findAllByDate(start, end);
        }
        return hits.stream()
                .map(p -> StatsMapper.toViewStats(p))
                .collect(Collectors.toList());
    }
}

