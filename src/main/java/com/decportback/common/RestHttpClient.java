package com.decportback.common;

import lombok.Getter;
import lombok.NonNull;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

import java.util.HashMap;
import java.util.Map;

@Getter
public class RestHttpClient {
    private HttpMethod method;
    private String url;
    private Map<String, String> params;
    private HttpHeader headers;

    public RestHttpClient(@NonNull HttpMethod method, @NonNull String url) {
        this.method = method;
        this.url = url;
    }

    public RestHttpClient(@NonNull HttpMethod method, @NonNull String url, Map<String, String> params) {
        this.method = method;
        this.url = url;
        this.params = params;
    }

    public RestHttpClient(@NonNull HttpMethod method, @NonNull String url, HttpHeader headers) {
        this.method = method;
        this.url = url;
        this.headers = headers;
    }

    public RestHttpClient(@NonNull HttpMethod method, @NonNull String url, Map<String, String> params, HttpHeader headers) {
        this.method = method;
        this.url = url;
        this.params = params;
        this.headers = headers;
    }

    public void addHeader(String key, String value) {
        this.getHeaders().addHeader(key, value);
    }

    public void addHeader(Map<String, String> newKeyValue) {
        this.getHeaders().addHeader(newKeyValue);
    }

    public void addParam(String key, String value) {
        if (this.params == null)
            this.params = new HashMap<>();
        this.params.put(key, value);
    }

    public HttpEntity<Map<String, String>> toEntity() {
        return new HttpEntity<>(this.params, this.headers.build());
    }
}