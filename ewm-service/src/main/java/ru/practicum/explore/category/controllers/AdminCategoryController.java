package ru.practicum.explore.category.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.category.dto.CategoryDto;
import ru.practicum.explore.category.dto.NewCategoryDto;
import ru.practicum.explore.category.service.CategoryService;
import ru.practicum.explore.exception.NotFoundEx;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(
        value = "/admin/categories",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AdminCategoryController {
    private final CategoryService categoryService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody NewCategoryDto newCategoryDto) {
        log.info("Добавлена категория: {}", newCategoryDto);
        return categoryService.createCategory(newCategoryDto).map(newUser -> new ResponseEntity<>(
                newUser, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable long id) throws NotFoundEx {
        log.info("Удалена категория: {}", id);
        categoryService.deleteCategoryById(id);
    }

    @PatchMapping
    public ResponseEntity<CategoryDto> updateItem(@RequestBody CategoryDto categoryDto)
            throws NotFoundEx {
        log.info("Изменена категория: {}", categoryDto);
        return categoryService.updateCategory(categoryDto).map(itemResult -> new ResponseEntity<>(itemResult,
                        HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }
}
