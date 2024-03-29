package ru.ByCooper.marketplace.controllers.Impl;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.ByCooper.marketplace.utils.exception.*;
import ru.ByCooper.marketplace.utils.exception.Error;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

@RestControllerAdvice()
public class ControllerAdvisor {

    @ExceptionHandler(value = {EntityNotFound.class, WrongPasswordException.class, RuntimeIOException.class,
            UserAlreadyExistsException.class, NotAuthorizedException.class}
    )
    public ResponseEntity<ru.ByCooper.marketplace.utils.exception.Error> handleEntityNotFoundException(CustomException ex, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status = ex.getStatus();
        ru.ByCooper.marketplace.utils.exception.Error error = new Error();
        error.setTimestamp(new Timestamp(System.currentTimeMillis()));
        error.setStatus(status.value());
        error.setMessage(ex.getMessage());
        error.setPath(request.getRequestURI());
        return new ResponseEntity<>(error, headers, status);
    }
}