package com.panda.auth.exceptions;

public class PermissionInUseException extends RuntimeException {

    public PermissionInUseException(String message) {
        super(message);
    }

}
