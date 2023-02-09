package ru.yandex.practicum.stat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yandex.practicum.stat.model.EndpointHitDto;


@Repository
public interface StatsRepository extends JpaRepository<EndpointHitDto, Long>, StatsRepositoryJpa {
}