package ru.practicum.ewm.categories.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.ewm.categories.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "id", ignore = true)
    Category toCategory(NewCategoryDto category);

    CategoryDto toCategoryDto(Category category);

    ResponseCategoryDto toResponseCategoryDto(Category category);
}
