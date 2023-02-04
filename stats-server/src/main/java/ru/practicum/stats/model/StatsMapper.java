package ru.practicum.stats.model;

import java.util.ArrayList;
import java.util.List;

public class StatsMapper {
    public static ViewStats toViewStats(List<Object> object) {
        List<Object> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        return new ViewStats(
                object.get(2).toString(),
                object.get(1).toString(),
                Integer.reverse((Integer) object.get(0)));

    }
}
