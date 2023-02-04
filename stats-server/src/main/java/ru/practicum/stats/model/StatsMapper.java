package ru.practicum.stats.model;

import java.util.Collection;
import java.util.List;

import static java.util.Collections.reverse;
import static org.hibernate.loader.internal.AliasConstantsHelper.get;

public class StatsMapper {
    public static ViewStats toViewStats(List<Object> object) {
        return new ViewStats(
                object.get(0).toString(),
                object.get(1).toString(),
                Integer.parseInt(object.get(2).toString()));
    }
}
