package ru.practicum.stats.model;

import java.util.List;

import static java.lang.Integer.parseInt;

public class StatsMapper {
    public static ViewStats toViewStats(List<Object> object) {
        return new ViewStats(
                object.get(2).toString(),
                object.get(1).toString(),
                Integer.reverse(parseInt(object.get(0).toString())));

    }
}
