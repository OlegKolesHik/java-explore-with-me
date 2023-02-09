package ru.practicum.explore.compilation.service;

import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.NewCompilationDto;

import java.util.List;

public interface CompilationService {
    CompilationDto addCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilationById(Long compId);

    void deleteEventFromCompilation(Long compId, Long eventId);

    void addEventToCompilation(Long compId, Long eventId);

    void setPinCompilation(Long compId, Boolean pinned);

    List<CompilationDto> getAllCompilations(Boolean pinned, int from, int size);

    CompilationDto getCompilationById(Long compId);
}