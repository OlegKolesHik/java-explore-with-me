package ru.practicum.explore.compilation.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.dto.NewCompilationDto;
import ru.practicum.explore.compilation.service.CompilationService;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(
        value = "/admin",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class AdminCompilationController {
    private final CompilationService compilationService;

    @PostMapping("/compilations")
    public CompilationDto addCompilation(@Valid @RequestBody NewCompilationDto newCompilationDto) {
        log.info("Добавление подборки событий {}", newCompilationDto);
        return compilationService.addCompilation(newCompilationDto);
    }

    @DeleteMapping("/compilations/{compId}")
    public void deleteCompilation(@PathVariable Long compId) {
        log.info("Получен запрос на удаление подборки событий {}", compId);
        compilationService.deleteCompilationById(compId);
    }

    @DeleteMapping("/compilations/{compId}/events/{eventId}")
    public void deleteEventFromCompilation(@PathVariable Long compId, @PathVariable Long eventId) {
        log.info("Удаление события {} из подборки {}", compId, eventId);
        compilationService.deleteEventFromCompilation(compId, eventId);
    }

    @PatchMapping("/compilations/{compId}/events/{eventId}")
    public void addEventToCompilation(@PathVariable Long compId, @PathVariable Long eventId) {
        log.info("Добавление события {} в подборку {}", compId, eventId);
        compilationService.addEventToCompilation(compId, eventId);
    }

    @DeleteMapping("/compilations/{compId}/pin")
    public void unsetPinCompilation(@PathVariable Long compId) {
        log.info("Открепить подборку на главной странице {}", compId);
        Boolean pinned = false;
        compilationService.setPinCompilation(compId, pinned);
    }

    @PatchMapping("/compilations/{compId}/pin")
    public void setPinCompilation(@PathVariable Long compId) {
        log.info("закрепить подборку на главной странице {}", compId);
        Boolean pinned = true;
        compilationService.setPinCompilation(compId, pinned);
    }
}
