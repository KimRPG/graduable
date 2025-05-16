package com.software.graduable.global.exception.exceptions;

import com.software.graduable.global.exception.BusinessException;

public class InvalidUserDataException extends BusinessException {
    public InvalidUserDataException(String message) {
        super(message);
    }
} 