package com.flight.reservation.exception.FlightException;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String message) {
        super(message);
    }

}
