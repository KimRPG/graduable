package com.software.graduable.global.exception.exceptions;

import com.software.graduable.global.exception.BusinessException;

public class UserNotFoundException extends BusinessException {
    public UserNotFoundException(String message) {
        super(message);
    }
} 