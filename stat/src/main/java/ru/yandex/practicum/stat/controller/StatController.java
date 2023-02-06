package ru.yandex.practicum.stat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.stat.model.EndpointHit;
import ru.yandex.practicum.stat.service.StatService;

@Slf4j
@RestController
@RequestMapping(path = "")
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;

    @PostMapping("/hit")
    public ResponseEntity<Object> saveHit(@RequestBody EndpointHit endpointHit) {
        log.info("save endpouintHit uri {}", endpointHit.getUri());
        statService.saveHit(endpointHit);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/stats") // List<ViewStats>
    public ResponseEntity<Object> getStats(@RequestParam String start,
                                           @RequestParam String end,
                                           @RequestParam String[] uris,
                                           @RequestParam(defaultValue = "false") Boolean unique) {
        log.info("get statistic for uris {}", uris);

        return new ResponseEntity<>(statService.getStats(start, end, uris, unique), HttpStatus.OK);
    }
}
