package ru.practicum.service.compilation.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.service.compilation.model.Compilation;

import java.util.List;

public interface CompilationRepositoryJpaCustom extends JpaRepository<Compilation, Long> {

    @Query("SELECT c FROM Compilation c WHERE (c.pinned IN ?1)")
    List<Compilation> getAllCompilations(List<Boolean> listPinned, Pageable pageable);

}