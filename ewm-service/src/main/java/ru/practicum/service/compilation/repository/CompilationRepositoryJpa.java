package ru.practicum.service.compilation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.service.compilation.model.Compilation;

@Repository
public interface CompilationRepositoryJpa extends JpaRepository<Compilation, Long>, CompilationRepositoryJpaCustom {
}
