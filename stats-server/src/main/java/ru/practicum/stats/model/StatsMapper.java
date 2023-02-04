package ru.practicum.stats.model;

import java.util.List;

public class StatsMapper {
    public static ViewStats toViewStats(List<Object> object) {
        return new ViewStats(
                object.get(2).toString(),
                object.get(1).toString(),
                Integer.reverse(Integer.parseInt((String) object.get(0))));

    }
}
