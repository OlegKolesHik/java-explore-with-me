package ru.yandex.practicum.stats.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "stats")
public class EndpointHitDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    @Column(name = "app", nullable = false, length = 50)
    private String app;
    @NonNull
    @Column(name = "uri", nullable = false, length = 150)
    private String uri;
    @NonNull
    @Column(name = "ip", nullable = false, length = 40)
    private String ip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
}
