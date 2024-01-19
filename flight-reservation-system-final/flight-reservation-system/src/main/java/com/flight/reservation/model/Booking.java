
package com.flight.reservation.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id", nullable = false)
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(name = "booking_datetime", nullable = false)
    private LocalDateTime bookingDateTime;

    @Column(name = "cancelled", nullable = false)
    private boolean cancelled;

    public void cancelBooking() {
        this.cancelled = true;
    }
}
