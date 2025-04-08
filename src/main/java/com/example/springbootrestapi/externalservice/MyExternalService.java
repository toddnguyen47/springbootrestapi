package com.example.springbootrestapi.externalservice;

import reactor.core.publisher.Mono;

public interface MyExternalService {
    Mono<MyModel> calc(int input);
}
