package ru.practicum.explore.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.event.model.Event;

@Repository
public interface EventRepositoryJpa extends JpaRepository<Event, Long>, EventRepositoryJpaCustom {
}
