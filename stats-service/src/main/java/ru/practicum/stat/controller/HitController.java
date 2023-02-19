package ru.practicum.stat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.stat.dto.CreateHitDto;
import ru.practicum.stat.dto.ResponseHitDto;
import ru.practicum.stat.dto.ResponseStatDto;
import ru.practicum.stat.service.HitService;


import java.time.LocalDateTime;
import java.util.List;

import static ru.practicum.stat.dto.HitMapper.DATE_FORMAT;

@Slf4j
@RestController
@RequiredArgsConstructor
public class HitController {
    private final HitService hitService;



    @PostMapping("/hit")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseHitDto createHit(@RequestBody CreateHitDto createHitDto) {
        log.info("Create Hit {}", createHitDto);
        return hitService.createHit(createHitDto);
    }

    @GetMapping("/stats")
    public List<ResponseStatDto> getStats(@RequestParam @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime start,
                                          @RequestParam @DateTimeFormat(pattern = DATE_FORMAT) LocalDateTime end,
                                          @RequestParam(defaultValue = "false") Boolean unique,
                                          @RequestParam(required = false) List<String> uris) {
        log.info("Get stats: start={}, end={}, unique={}, uris={}", start, end, unique, uris);
        return hitService.getStats(start, end, unique, uris);
    }
}
