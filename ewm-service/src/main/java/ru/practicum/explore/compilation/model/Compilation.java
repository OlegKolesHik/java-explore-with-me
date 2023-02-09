package ru.practicum.explore.compilation.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.explore.event.model.Event;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@Entity
@Table(name = "compilations")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Compilation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany
    @JoinTable(name = "events_compilations", joinColumns = @JoinColumn(name = "compilation_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id"))
    private List<Event> eventsCompilations;
    @Column(name = "pinned", nullable = false)
    private boolean pinned;
    @Column(name = "title", nullable = false)
    private String title;
}
