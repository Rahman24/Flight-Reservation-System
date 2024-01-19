package com.flight.reservation.exception.PassengerException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PassengerExceptionHandler {

    @ExceptionHandler(PassengerNotFoundException.class)
    public ResponseEntity<String> handlePassengerException(PassengerNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    // Add more @ExceptionHandler methods for other Passenger-related exceptions if
    // needed
}
