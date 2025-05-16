package com.software.graduable.global.exception.exceptions;

import com.software.graduable.global.exception.BusinessException;

public class UserAlreadyExistsException extends BusinessException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
} 