package com.flight.reservation.service;

import com.flight.reservation.exception.PassengerException.PassengerNotFoundException;
import com.flight.reservation.model.Passenger;
import com.flight.reservation.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import org.slf4j.*;

@Service
public class PassengerService {

    private static final Logger logInfo = LoggerFactory.getLogger(PassengerService.class);

    @Autowired
    PassengerRepository passengerRepository;

    public Passenger getPassengerById(Long id) {

        Passenger passenger = passengerRepository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException("Passenger Not Found With ID : " + id));
        logInfo.info("Retrieved Passenger Details by Passenger ID..");
        return passenger;

    }

    public List<Passenger> getAllPassengers() {
        logInfo.info("Retrieved All Passengers..");
        return passengerRepository.findAll();

    }

    public Passenger createPassenger(Passenger passenger) {
        logInfo.info("New Passenger created Successfully...");
        return passengerRepository.save(passenger);
    }

    public Passenger updatePassenger(Long id, Passenger passenger) {
        Passenger existPassenger = getPassengerById(id);
        existPassenger.setFirstName(passenger.getFirstName());
        existPassenger.setLastName(passenger.getLastName());
        existPassenger.setEmail(passenger.getEmail());
        logInfo.info("Updated Passenger Successfully..");
        return passengerRepository.save(existPassenger);

    }

    public void deletePassenger(Long id) {
        if (!passengerRepository.existsById(id)) {
            throw new PassengerNotFoundException("Invalid Passenger ID. Please Enter Valid Id to Delete Passenger. ");
        }
        logInfo.info("Passenger Deleted Successfully");
        passengerRepository.deleteById(id);
    }
}