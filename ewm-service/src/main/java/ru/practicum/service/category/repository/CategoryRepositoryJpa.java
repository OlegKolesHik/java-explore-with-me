package ru.practicum.service.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.service.category.model.Category;

@Repository
public interface CategoryRepositoryJpa extends JpaRepository<Category, Long>, CategoriesRepositoryJpaCustom {
}
