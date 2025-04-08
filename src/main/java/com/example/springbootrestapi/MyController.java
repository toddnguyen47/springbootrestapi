package com.example.springbootrestapi;

import com.example.springbootrestapi.externalservice.MyExternalService;
import com.example.springbootrestapi.externalservice.MyModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MyController {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private final MyExternalService myExternalService;

    public MyController(final MyExternalService myExternalService) {
        this.myExternalService = myExternalService;
    }

    @PostMapping("/calc")
    public Mono<MyModel> calc(@RequestBody String body) {
        final MyRequestBody myRequestBody;
        try {
            myRequestBody = MAPPER.readValue(body, MyRequestBody.class);
        } catch (final JsonProcessingException exception) {
            return Mono.error(exception);
        }
        Mono<MyModel> result = this.myExternalService.calc(myRequestBody.getIntToCalc());
        return result;
    }
}
