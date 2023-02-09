package ru.practicum.explore.event.model;

import lombok.*;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.compilation.model.Compilation;
import ru.practicum.explore.user.model.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@Table(name = "events")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Event implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "annotation",nullable = false)
    private String annotation;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "paid", nullable = false)
    private Boolean paid;

    @Column(name = "publishet_on")
    private LocalDateTime publishetOn;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "created", nullable = false)
    private LocalDateTime createdOn;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "participant_limit", nullable = false)
    private Integer participantLimit;

    @Column(name = "request_moderation", nullable = false)
    private Boolean requestModeration;

    @Enumerated(EnumType.STRING)
    private State state;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "lon", column = @Column(name = "location_lon", nullable = false)),
            @AttributeOverride(name = "lat", column = @Column(name = "location_lat", nullable = false))})
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "initiator_id")
    @ToString.Exclude
    private User initiator;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    private Category category;
    @ManyToMany
    @JoinTable(name = "events_compilations", joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "compilation_id"))
    private List<Compilation> compilations;
    @Transient
    private Integer views;
}
