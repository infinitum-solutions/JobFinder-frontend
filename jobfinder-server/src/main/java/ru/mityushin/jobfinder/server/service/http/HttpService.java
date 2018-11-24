package ru.mityushin.jobfinder.server.service.http;

import org.springframework.http.HttpHeaders;

import java.util.Map;

public interface HttpService {
    HttpHeaders createHttpHeaders(Map<String, String> map);
    Map<String, String> createMessage(String message);
}
