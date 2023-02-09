package ru.practicum.service.category.dto;

import ru.practicum.service.category.model.Category;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        if (category == null) return null;
        else return new CategoryDto(
                category.getId(),
                category.getName()
        );
    }

    public static Category toCategory(NewCategoryDto newCategoryDto) {
        if (newCategoryDto == null) return null;
        else return new Category(
                0L,
                newCategoryDto.getName()
        );
    }
}
