package com.panda.auth.base;

import com.panda.auth.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.management.relation.RoleNotFoundException;

/**
 * @Author: JLP
 * @CreateTime: 2022-08-01  11:19
 * @Description: Handles the exceptions globally in this microservice
 * @Version: 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({InvalidEmailException.class, InvalidGenderException.class, InvalidUserDataException.class,
            InvalidUserIdentifierException.class, InvalidRoleIdentifierException.class, InvalidUsernameException.class,
            InvalidLoginException.class, InvalidPermissionDataException.class, InvalidRoleDataException.class,
            RoleInUseException.class, PermissionInUseException.class})
    public ResponseEntity<ErrorDetails> handleAsBadRequest(RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({RoleNotFoundException.class, UserNotFoundException.class, UserIsSecuredException.class,
            PermissionNotFoundException.class})
    public ResponseEntity<ErrorDetails> handleAsNotFound(RuntimeException ex) {
        ErrorDetails errorDetails = new ErrorDetails(ex.getMessage());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

}
