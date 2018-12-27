package com.justBrokkoly.flightreservation.service;

import com.justBrokkoly.flightreservation.dto.ReservationRequest;
import com.justBrokkoly.flightreservation.entity.Reservation;
import org.springframework.stereotype.Service;


public interface ReservationService {

    Reservation bookFlight(ReservationRequest request);
}
