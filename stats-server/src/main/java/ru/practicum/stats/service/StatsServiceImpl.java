package ru.practicum.stats.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.stats.model.StatsMapper;
import ru.practicum.stats.model.EndpointHit;
import ru.practicum.stats.repository.StatsRepositoryJpa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {
    private final StatsRepositoryJpa statsRepositoryJpa;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public void addHit(EndpointHit endpointHit) {
        statsRepositoryJpa.save(endpointHit);
    }

    @Override
    public List<Object> getStats(String stringStart, String stringEnd, String[] uris, Boolean unique) {
        LocalDateTime start = LocalDateTime.parse(stringStart, FORMATTER);
        LocalDateTime end = LocalDateTime.parse(stringEnd, FORMATTER);
        List<List<Object>> hits = new ArrayList<>();
        if ((uris != null) & unique) {
            hits = statsRepositoryJpa.findAllByUrisAndUnique(start, end, uris);
        } else if (uris != null && !unique) {
            hits = statsRepositoryJpa.findAllByUris(start, end, uris);
        } else if (uris == null && unique) {
            hits = statsRepositoryJpa.findAllByUnique(start, end);
        } else {
            hits = statsRepositoryJpa.findAllByDate(start, end);
        }
        return hits.stream()
                .map(p -> StatsMapper.toViewStats(p))
                .collect(Collectors.toList());
    }
}
