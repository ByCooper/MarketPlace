package ru.ByCooper.marketplace.utils.exception;

import org.springframework.http.HttpStatus;

public interface CustomException {

    String getMessage();

    HttpStatus getStatus();
}