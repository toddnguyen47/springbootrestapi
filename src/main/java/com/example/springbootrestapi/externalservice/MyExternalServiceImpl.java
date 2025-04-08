package com.example.springbootrestapi.externalservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MyExternalServiceImpl implements MyExternalService {

    private final WebClient webClient;

    public MyExternalServiceImpl(
            final WebClient.Builder webClientBuilder, @Value("${external.path}") final String baseUrl) {
        this.webClient = webClientBuilder.baseUrl(baseUrl).build();
    }

    @Override
    public Mono<MyModel> calc(int input) {
        Mono<MyModel> response = webClient
                .get()
                .uri(uriBuilder ->
                        uriBuilder.path("/calc").queryParam("input", input).build())
                .retrieve()
                .bodyToMono(MyModel.class);
        return response;
    }
}
