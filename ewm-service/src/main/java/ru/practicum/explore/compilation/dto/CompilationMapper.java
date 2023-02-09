package ru.practicum.explore.compilation.dto;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.explore.compilation.model.Compilation;
import ru.practicum.explore.event.dto.EventMapper;
import ru.practicum.explore.event.model.Event;
import ru.practicum.explore.event.repository.EventRepositoryJpa;
import ru.practicum.explore.validation.Validation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompilationMapper {
    private final EventRepositoryJpa eventRepositoryJpa;
    private final EventMapper eventMapper;
    private final Validation validation;

    public Compilation toCompilation(NewCompilationDto newCompilationDto) {
        if (newCompilationDto == null) return null;
        else {
            Compilation compilation = new Compilation(
                    newCompilationDto.getId(),
                    null,
                    newCompilationDto.isPinned(),
                    newCompilationDto.getTitle()
            );
            List<Event> events = new ArrayList<>();
            for (Long eventId : newCompilationDto.getEvents()) {
                validation.validateEvent(eventId);
                System.out.println(eventId);
                Event event = eventRepositoryJpa.findById(eventId).get();
                events.add(event);
            }
            compilation.setEventsCompilations(events);
            return compilation;
        }
    }

    public CompilationDto toCompilationDto(Compilation compilation) {
        if (compilation == null) return null;
        else {
            CompilationDto compilationDto = new CompilationDto(
                    compilation.getId(),
                    null,
                    compilation.isPinned(),
                    compilation.getTitle()
            );
            compilationDto.setEvents(compilation.getEventsCompilations().stream()
                    .map(p -> eventMapper.toEventShortDto(p)).collect(Collectors.toList()));
            return compilationDto;
        }
    }
}
