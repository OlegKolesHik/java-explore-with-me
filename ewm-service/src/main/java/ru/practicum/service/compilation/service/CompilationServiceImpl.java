package ru.practicum.service.compilation.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.service.compilation.dto.CompilationDto;
import ru.practicum.service.compilation.dto.CompilationMapper;
import ru.practicum.service.compilation.dto.NewCompilationDto;
import ru.practicum.service.compilation.model.Compilation;
import ru.practicum.service.compilation.repository.CompilationRepositoryJpa;
import ru.practicum.service.event.repository.EventRepositoryJpa;
import ru.practicum.service.event.model.Event;
import ru.practicum.service.validation.Validation;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompilationServiceImpl implements CompilationService {
    private final CompilationRepositoryJpa compilationRepositoryJpa;
    private final EventRepositoryJpa eventRepositoryJpa;
    private final Validation validation;
    private final CompilationMapper compilationMapper;

    public CompilationDto addCompilation(NewCompilationDto newCompilationDto) {
        Compilation compilation = compilationMapper.toCompilation(newCompilationDto);
        compilationRepositoryJpa.save(compilation);
        return compilationMapper.toCompilationDto(compilation);
    }

    public void deleteCompilationById(Long compId) {
        validation.validateCompilation(compId);
        compilationRepositoryJpa.deleteById(compId);
    }

    @Transactional
    public void deleteEventFromCompilation(Long compId, Long eventId) {
        validation.validateCompilation(compId);
        validation.validateEvent(eventId);
        Compilation compilation = compilationRepositoryJpa.findById(compId).get();
        List<Event> eventsList = compilation.getEventsCompilations();
        eventsList.removeIf(p -> p.getId().equals(eventId));
        compilation.setEventsCompilations(eventsList);
        compilationRepositoryJpa.save(compilation);
    }

    public void addEventToCompilation(Long compId, Long eventId) {
        validation.validateCompilation(compId);
        validation.validateEvent(eventId);
        Compilation compilation = compilationRepositoryJpa.findById(compId).get();
        List<Event> eventsList = compilation.getEventsCompilations();
        eventsList.add(eventRepositoryJpa.findById(eventId).get());
        compilation.setEventsCompilations(eventsList);
        compilationRepositoryJpa.save(compilation);
    }

    public void setPinCompilation(Long compId, Boolean pinned) {
        validation.validateCompilation(compId);
        Compilation compilation = compilationRepositoryJpa.findById(compId).get();
        compilation.setPinned(pinned);
        compilationRepositoryJpa.save(compilation);
    }

    public List<CompilationDto> getAllCompilations(Boolean pinned, int from, int size) {
        Pageable pageable = PageRequest.of(from / size, size);
        List<Boolean> listPinned = pinned == null ? List.of(true, false) : List.of(pinned);
        return compilationRepositoryJpa.getAllCompilations(listPinned, pageable)
                .stream().map(p -> compilationMapper.toCompilationDto(p)).collect(Collectors.toList());
    }

    public CompilationDto getCompilationById(Long compId) {
        validation.validateCompilation(compId);
        return compilationMapper.toCompilationDto(compilationRepositoryJpa.findById(compId).get());
    }
}
