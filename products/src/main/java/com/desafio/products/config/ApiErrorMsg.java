package com.desafio.products.config;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ApiErrorMsg {
    
    private int status_code;
    private List<String> message;

    public ApiErrorMsg(int status_code, List<String> message) {
        super();
        this.status_code = status_code;
        this.message = message;
    }

    public ApiErrorMsg(int status_code, String error) {
        super();
        this.status_code = status_code;
        message = Arrays.asList(error);
    }

}
