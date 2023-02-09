package ru.practicum.explore.compilation.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.practicum.explore.compilation.dto.CompilationDto;
import ru.practicum.explore.compilation.service.CompilationService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping(
        value = "/compilations",
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class PublicCompilationController {
    private final CompilationService compilationService;

    @GetMapping
    public List<CompilationDto> getAllCompilations(@RequestParam(value = "pinned", required = false) Boolean pinned,
                                                   @PositiveOrZero @RequestParam(value = "from", defaultValue = "0")
                                                   int from,
                                                   @Positive @RequestParam(value = "size", defaultValue = "10")
                                                   int size) {
        log.info("Получение подборок событий");
        return compilationService.getAllCompilations(pinned, from, size);
    }

    @GetMapping("/{compId}")
    public CompilationDto getCompilationById(@PathVariable Long compId) {
        log.info("Получение подборки событий по его id {}", compId);
        return compilationService.getCompilationById(compId);
    }
}
