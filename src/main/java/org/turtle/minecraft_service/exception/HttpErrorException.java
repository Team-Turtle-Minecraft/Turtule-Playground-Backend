package org.turtle.minecraft_service.exception;

import lombok.Getter;
import org.turtle.minecraft_service.config.HttpErrorCode;

@Getter
public class HttpErrorException extends RuntimeException{
    private final HttpErrorCode httpErrorCode;

    public HttpErrorException(HttpErrorCode httpErrorCode){
        super(httpErrorCode.getMessage());
        this.httpErrorCode = httpErrorCode;
    }
}

