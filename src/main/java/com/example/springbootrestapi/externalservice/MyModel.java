package com.example.springbootrestapi.externalservice;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyModel {
    @JsonProperty("result")
    private int result;
}
