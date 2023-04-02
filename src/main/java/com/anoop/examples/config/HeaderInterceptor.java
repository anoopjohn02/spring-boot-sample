package com.anoop.examples.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

public class HeaderInterceptor implements ClientHttpRequestInterceptor {
    private static final String AUTH_HEADER = "Authorization";
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader(AUTH_HEADER);
        HttpHeaders headers = request.getHeaders();
        headers.add(AUTH_HEADER, token);
        return execution.execute(request, body);
    }
}
