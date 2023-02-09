package ru.practicum.service.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.service.event.model.Event;

@Repository
public interface EventRepositoryJpa extends JpaRepository<Event, Long>, EventRepositoryJpaCustom {
}
