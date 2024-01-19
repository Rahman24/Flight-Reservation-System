package com.flight.reservation.service;

import com.flight.reservation.exception.FlightException.FlightNotFoundException;
import com.flight.reservation.model.Flight;
import com.flight.reservation.repository.FlightRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FlightService {

    private static final Logger logInfo = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        logInfo.info("Getting All Flights from the Repository..");
        return flightRepository.findAll();
    }

    public Flight getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(
                        () -> new FlightNotFoundException("Flight Not Found with ID : " + id));
        logInfo.info("Getting Flight with ID Successfully..");
        return flight;
    }

    public Flight createFlight(Flight flight) {
        logInfo.info(" New Flight Created Successfully!");
        return flightRepository.save(flight);
    }

    public void deleteFlight(Long id) {
        if (!flightRepository.existsById(id)) {
            throw new FlightNotFoundException("Flight Not Found With ID :" + id);
        }
        logInfo.info("Flight Deleted by ID Successfully..");
        flightRepository.deleteById(id);
    }
}
