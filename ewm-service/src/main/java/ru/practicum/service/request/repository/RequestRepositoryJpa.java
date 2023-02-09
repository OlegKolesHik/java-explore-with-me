package ru.practicum.service.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.service.request.model.Request;

@Repository
public interface RequestRepositoryJpa extends JpaRepository<Request, Long>, RequestRepositoryJpaCustom {
}
