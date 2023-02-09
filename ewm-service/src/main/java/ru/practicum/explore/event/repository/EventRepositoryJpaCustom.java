package ru.practicum.explore.event.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.model.State;
import ru.practicum.explore.request.model.Status;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepositoryJpaCustom extends JpaRepository<Event, Long> {
    @Query(
            "SELECT e FROM Event e WHERE (LOWER(e.annotation) LIKE LOWER(CONCAT('%', ?1, '%')) " +
                    "OR LOWER(e.description) LIKE LOWER(CONCAT('%', ?1, '%'))) AND (e.paid IN ?2) " +
                    "AND (e.eventDate > ?3) AND (e.eventDate < ?4) AND (e.category.id IN ?5)" +
                    "  AND (e.state = ?6)" +
                    " AND (e.participantLimit < (SELECT count (r) FROM Request r WHERE r.status=?7))"
    )
    List<Event> getEventsOnlyAvailable(String text, List<Boolean> listPaid, LocalDateTime rangeStart,
                                       LocalDateTime rangeEnd, List<Long> categories,
                                       State state, Status status,  Pageable pageable);

    @Query(
            "SELECT e FROM Event e WHERE (LOWER(e.annotation) LIKE LOWER(CONCAT('%', ?1, '%')) " +
                    "OR LOWER(e.description) LIKE LOWER(CONCAT('%', ?1, '%'))) AND (e.paid IN ?2) " +
                    "AND (e.eventDate > ?3) AND (e.eventDate < ?4) AND (e.category.id IN ?5) AND (e.state = ?6)"
    )
    List<Event> getEventsAll(String text, List<Boolean> listPaid, LocalDateTime rangeStart,
                             LocalDateTime rangeEnd, List<Long> categories,
                             State state, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE (e.id = ?1) AND (e.state = ?2)")
    Event getEventByIdByState(Long eventId, State state);

    @Query("SELECT e FROM Event e WHERE e.initiator.id = ?1")
    List<Event> findAllByInitiator(Long userId, Pageable pageable);

    @Query("SELECT e FROM Event e WHERE (e.initiator.id = ?1) and (e.id=?2)")
    Event findByIdAndInitiator(Long userId, Long eventId);

    @Query(
            "SELECT e FROM Event e WHERE (e.state IN ?1)" +
                    "AND (e.category.id IN ?2)" +
                    "AND (e.eventDate > ?3) AND (e.eventDate < ?4)"
    )
    List<Event> getAllAdmin(List<State> states, List<Long> categories, LocalDateTime start, LocalDateTime end,
                            Pageable pageable);

    @Query(
            "SELECT e FROM Event e WHERE (e.state IN ?1)" +
                    "AND (e.category.id IN ?2)" +
                    "AND (e.eventDate > ?3) AND (e.eventDate < ?4)" +
                    "AND (e.initiator.id IN ?5)"
    )
    List<Event> getAllByUsersAdmin(List<State> states, List<Long> categories, LocalDateTime start, LocalDateTime end,
                                   List<Long> users, Pageable pageable);

}