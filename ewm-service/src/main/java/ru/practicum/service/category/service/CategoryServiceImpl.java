package ru.practicum.service.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.service.category.dto.CategoryDto;
import ru.practicum.service.category.dto.CategoryMapper;
import ru.practicum.service.category.dto.NewCategoryDto;
import ru.practicum.service.category.model.Category;
import ru.practicum.service.category.repository.CategoryRepositoryJpa;
import ru.practicum.service.exception.ConflictEx;
import ru.practicum.service.exception.NotFoundEx;
import ru.practicum.service.validation.Validation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepositoryJpa repository;
    private final Validation validation;

    @Override
    public Optional<CategoryDto> createCategory(NewCategoryDto newCategoryDto) {
        if (repository.findAllByName(newCategoryDto.getName()) > 0) {
            throw new ConflictEx(newCategoryDto.getName(), "categories");
        }
        return Optional.ofNullable(CategoryMapper
                .toCategoryDto(repository.save(CategoryMapper.toCategory(newCategoryDto))));
    }

    @Override
    public Optional<CategoryDto> updateCategory(CategoryDto categoryDto) {
        if (repository.findAllByName(categoryDto.getName()) > 0) {
            throw new ConflictEx(categoryDto.getName(), "categories");
        }
        validation.validateCategory(categoryDto.getId());
        Category category = repository.findById(categoryDto.getId()).get();
        category.setName(categoryDto.getName());
        return Optional.ofNullable(CategoryMapper
                .toCategoryDto(repository.save(category)));
    }

    @Override
    public void deleteCategoryById(long id) throws NotFoundEx {
        validation.validateCategory(id);
        repository.deleteById(id);
    }

    @Override
    public List<CategoryDto> getAllCategories(int from, int size) {
        validation.validatePagination(from, size);
        int page = from / size;
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).stream()
                .map(p -> CategoryMapper.toCategoryDto(p)).collect(Collectors.toList());
    }

    @Override
    public Optional<CategoryDto> getCategoryById(long id) {
        validation.validateCategory(id);
        return Optional.ofNullable(CategoryMapper.toCategoryDto(repository.findById(id).get()));
    }
}