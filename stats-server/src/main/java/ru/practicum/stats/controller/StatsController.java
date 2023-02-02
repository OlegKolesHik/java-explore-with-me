package ru.practicum.stats.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stats.model.EndpointHit;
import ru.practicum.stats.service.StatsService;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(
        consumes = MediaType.ALL_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class StatsController {
    private final StatsService statsService;

    @PostMapping(value = "/hit")
    public void addHit(@RequestBody EndpointHit endpointHit) {
        statsService.addHit(endpointHit);
        log.info("Получен запрос на добавление эндпоинта", endpointHit);
    }

    @GetMapping("/stats")
    public ResponseEntity<List<Object>> getHits(
            @RequestParam(name = "start", defaultValue = "") String start,
            @RequestParam(name = "end", defaultValue = "") String end,
            @RequestParam(name = "uris", required = false) String[] uris,
            @RequestParam(name = "unique", defaultValue = "false") Boolean unique
    ) {
        log.info("Получен запрос на получение списка статистики");
        return new ResponseEntity<>(statsService.getStats(start, end, uris, unique), HttpStatus.OK);
    }
}

