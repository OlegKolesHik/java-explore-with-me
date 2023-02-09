package ru.yandex.practicum.stat.model;

import java.util.List;

public class StatsMapper {
    public static ViewStats toViewStats(List<Object> object) {
        return new ViewStats(
                object.get(2).toString(),
                object.get(1).toString(),
                Integer.parseInt(object.get(0).toString()));
    }
}
