package com.anoop.examples.config;

import com.anoop.examples.auth.AuthService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;

@Component
public class HeaderInterceptor implements ClientHttpRequestInterceptor {
    public static final String AUTH_HEADER = "Authorization";

    @Autowired
    private AuthService authService;

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        String token = requestAttributes.getRequest().getHeader(AUTH_HEADER);
        if(Strings.isEmpty(token)) {
            token = authService.getToken();
        }
        HttpHeaders headers = request.getHeaders();
        headers.add(AUTH_HEADER, token);
        ClientHttpResponse response = execution.execute(request, body);
        return response;
    }
}
