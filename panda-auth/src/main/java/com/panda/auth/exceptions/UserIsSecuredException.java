package com.panda.auth.exceptions;

public class UserIsSecuredException extends RuntimeException {

    public UserIsSecuredException(String message) {
        super(message);
    }

}
