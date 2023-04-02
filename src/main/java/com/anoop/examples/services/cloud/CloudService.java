package com.anoop.examples.services.cloud;

import com.anoop.examples.model.Measurement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class CloudService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ioto.hub.ms.url}")
    private String baseUrl;

    public List<Measurement> send(List<Measurement> measurements){

        HttpEntity<List<Measurement>> request = new HttpEntity<>(measurements);

        String url = baseUrl + "/measurements";
        UriComponents uriComponents =
                UriComponentsBuilder.fromUriString(url)
                        .build().encode();
        log.debug("Accessing URL {}", uriComponents.toUriString());

        ResponseEntity<Measurement[]> response = restTemplate.exchange
                (uriComponents.toUri(), HttpMethod.POST, request, Measurement[].class);
        return Arrays.asList(response.getBody());
    }

}
