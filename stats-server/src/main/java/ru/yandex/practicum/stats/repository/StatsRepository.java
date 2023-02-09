package ru.yandex.practicum.stats.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.stats.model.EndpointHitDto;


@Repository
public interface StatsRepository extends JpaRepository<EndpointHitDto, Long>, StatsRepositoryJpa {
}