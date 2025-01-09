package org.turtle.minecraft_service.exception;

import lombok.Getter;
import org.turtle.minecraft_service.constant.InternalErrorType;

@Getter
public class InternalErrorException extends RuntimeException{

    private final InternalErrorType internalErrorType;

    public InternalErrorException(InternalErrorType internalError) {
        super(internalError.getMessage());
        this.internalErrorType = internalError;
    }
}
