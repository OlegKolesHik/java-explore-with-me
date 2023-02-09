package ru.yandex.practicum.stats.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewStats {

    private String app;

    private String uri;

    private int hits;
}

