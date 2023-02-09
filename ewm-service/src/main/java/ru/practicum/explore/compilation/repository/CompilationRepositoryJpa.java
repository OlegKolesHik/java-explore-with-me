package ru.practicum.explore.compilation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.explore.compilation.model.Compilation;

@Repository
public interface CompilationRepositoryJpa extends JpaRepository<Compilation, Long>, CompilationRepositoryJpaCustom {
}
