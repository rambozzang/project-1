package com.tigerbk.project1.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class DefaultException extends RuntimeException{
 
    public DefaultException(String message) {
        super(message);
    }

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

}
