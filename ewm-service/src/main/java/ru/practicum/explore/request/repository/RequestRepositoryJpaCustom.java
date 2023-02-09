package ru.practicum.explore.request.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explore.request.model.Request;

import java.util.List;

public interface RequestRepositoryJpaCustom extends JpaRepository<Request, Long> {
    @Query("SELECT r FROM Request  r WHERE (r.requester.id = ?1)")
    List<Request> findAllByRequesterId(Long requesterId);

    @Query("SELECT r FROM Request  r WHERE (r.requester.id=?1) AND (r.event.id = ?2)")
    List<Request> getByRequesterByEvent(Long userId, Long eventId);

    @Query("SELECT r FROM Request  r WHERE (r.event.initiator.id=?1) AND (r.event.id = ?2)")
    List<Request> getEventRequestsPrivate(Long requesterId, Long eventId);

    @Query("SELECT count (r) FROM Request r WHERE r.event.id=?1")
    Integer getConfirmed(Long eventId);
}