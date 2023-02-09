package ru.yandex.practicum.stat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.yandex.practicum.stat.model.EndpointHitDto;

import java.time.LocalDateTime;
import java.util.List;

public interface StatsRepositoryJpa extends JpaRepository<EndpointHitDto, Long> {
    @Query("SELECT  DISTINCT COUNT (distinct e.ip), e.uri, e.app FROM EndpointHit e " +
            "WHERE e.timestamp >= ?1 AND e.timestamp <= ?2 AND e.uri IN ?3 GROUP BY e.uri, e.app")
    List<List<Object>> findAllByUrisAndUnique(LocalDateTime start, LocalDateTime end, String[] uris);

    @Query("SELECT DISTINCT COUNT (e.ip), e.uri, e.app FROM EndpointHit e " +
            "where e.timestamp >= ?1 AND e.timestamp <= ?2 AND e.uri IN ?3 GROUP BY e.uri, e.app")
    List<List<Object>> findAllByUris(LocalDateTime start, LocalDateTime end, String[] uris);

    @Query("SELECT  DISTINCT COUNT (distinct e.ip), e.uri, e.app FROM EndpointHit e " +
            "WHERE e.timestamp >= ?1 AND e.timestamp <= ?2 GROUP BY e.uri, e.app ")
    List<List<Object>> findAllByUnique(LocalDateTime start, LocalDateTime end);

    @Query("SELECT DISTINCT count (e.ip), e.uri, e.app FROM EndpointHit e " +
            "WHERE e.timestamp >= ?1 AND e.timestamp <= ?2 GROUP BY e.uri, e.app")
    List<List<Object>> findAllByDate(LocalDateTime start, LocalDateTime end);
}