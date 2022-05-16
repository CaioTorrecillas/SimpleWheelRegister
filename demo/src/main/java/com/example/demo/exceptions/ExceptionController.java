package com.example.demo.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(RodasNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> objectNotFound(RodasNotFoundException objectNotFound){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "A Roda/Marca não foi encontrada");
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }
}
