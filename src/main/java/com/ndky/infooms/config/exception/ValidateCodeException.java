package com.ndky.infooms.config.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author chenqingsheng
 * @date 2021/1/23 22:25
 */
public class ValidateCodeException extends AuthenticationException {
    public ValidateCodeException(String message) {
        super(message);
    }
}
