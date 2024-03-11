package com.tigerbk.project1.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String code;
    private String msg;
    private HttpStatus data;
}