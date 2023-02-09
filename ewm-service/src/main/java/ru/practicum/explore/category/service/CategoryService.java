package ru.practicum.explore.category.service;

import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.dto.NewCategoryDto;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<CategoryDto> createCategory(NewCategoryDto newCategoryDto);

    Optional<CategoryDto> updateCategory(CategoryDto categoryDto);

    void deleteCategoryById(long id);

    List<CategoryDto> getAllCategories(int from, int size);

    Optional<CategoryDto> getCategoryById(long id);

}
