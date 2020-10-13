package com.mthree.bullsandcows.controller;

import com.mthree.bullsandcows.service.GameCompleteException;
import com.mthree.bullsandcows.service.GameDoesNotExistException;
import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class BCControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MysqlDataTruncation.class)
    public final ResponseEntity handleRoundInstantiationException(MysqlDataTruncation ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid input.");
    }

    @ExceptionHandler(GameDoesNotExistException.class)
    public final ResponseEntity handleRoundInstantiationException(GameDoesNotExistException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Game does not exist.");
    }

    @ExceptionHandler(GameCompleteException.class)
    public final ResponseEntity handleRoundInstantiationException(GameCompleteException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Game already complete.");
    }

}
