package ru.bse71.learnup.spring.rest.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by bse71
 * Date: 02.09.2021
 * Time: 1:21
 */

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handleException(NullPointerException err) {
        return new ResponseEntity<>(
                "Something wrong: No object. " + err.getMessage(),
                new HttpHeaders(),
                HttpStatus.FORBIDDEN);
    }

}
