package ru.practicum.service.exception;

import lombok.Getter;

@Getter
public class ConflictEx extends RuntimeException {
    public ConflictEx(String sql, String categoryName) {
        super("could not execute statement; SQL [" + sql + "]; constraint [" + categoryName + "uq_category_name];");
    }
}
