package com.desafio.products.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ApiErrorMsg {
    
    private HttpStatus status_code;
    private List<String> message;

    public ApiErrorMsg(HttpStatus status_code, List<String> message) {
        super();
        this.status_code = status_code;
        this.message = message;
    }

    public ApiErrorMsg(HttpStatus status_code, String error) {
        super();
        this.status_code = status_code;
        message = Arrays.asList(error);
    }

}
