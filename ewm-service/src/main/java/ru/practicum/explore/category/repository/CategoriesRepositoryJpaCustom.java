package ru.practicum.explore.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.explore.category.model.Category;

import java.util.List;

public interface CategoriesRepositoryJpaCustom extends JpaRepository<Category, Long> {

    @Query("SELECT c.id FROM Category  c")
    List<Long> findAllId();

    @Query("SELECT COUNT (c) FROM Category  c WHERE c.name LIKE ?1")
    Integer findAllByName(String name);
}