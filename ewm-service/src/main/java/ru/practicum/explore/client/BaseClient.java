package ru.practicum.explore.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import ru.practicum.explore.client.dto.EndpointHit;
import ru.practicum.explore.client.dto.ViewStats;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BaseClient {
    protected final RestTemplate rest;
    protected final String uri;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BaseClient(@Value("${stats-server.url}") String uri, RestTemplateBuilder rest) {
        this.uri = uri;
        this.rest = rest.uriTemplateHandler(new DefaultUriBuilderFactory(uri))
                .requestFactory(HttpComponentsClientHttpRequestFactory::new).build();
    }

    public HashMap<String, Integer> getStats(LocalDateTime strStart, LocalDateTime strEnd, @Nullable String[] uris,
                                             boolean unique) {
        Map<String, Object> parameters = new HashMap<>();
        List<ViewStats> resultList;
        HashMap<String, Integer> result = new HashMap<>();
        String start = strStart.format(FORMATTER);
        String end = strEnd.format(FORMATTER);

        if (uris != null) {
            parameters = Map.of("start", start, "end", end,
                    "uris", uris, "unique", unique);
            resultList = rest.exchange("/stats?start={start}&end={end}&uris={uris}&unique={unique}", HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<ViewStats>>() {
                    }, parameters).getBody();
        } else {
            parameters = Map.of("start", start, "end", end,
                    "unique", unique);
            resultList = rest.exchange("/stats?start={start}&end={end}&unique={unique}", HttpMethod.GET,
                    null, new ParameterizedTypeReference<List<ViewStats>>() {
                    }, parameters).getBody();
        }
        for (ViewStats viewStats : resultList) {
            result.put(viewStats.getUri(), viewStats.getHits());
        }
        return result;
    }

    public void addHit(HttpServletRequest request) {
        EndpointHit endpointHit = new EndpointHit("ewm-service",
                request.getRequestURI(),
                request.getRemoteAddr(), LocalDateTime.now().format(FORMATTER));
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        rest.exchange("/hit", HttpMethod.POST, new HttpEntity<>(endpointHit, headers), EndpointHit.class);
    }
}
