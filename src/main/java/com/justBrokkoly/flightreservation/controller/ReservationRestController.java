package com.justBrokkoly.flightreservation.controller;

import com.justBrokkoly.flightreservation.dto.ResrvationUpdateRequest;
import com.justBrokkoly.flightreservation.entity.Reservation;
import com.justBrokkoly.flightreservation.repos.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationRestController {

    private static final Logger LOGGER=LoggerFactory.getLogger(ReservationRestController.class);
    private ReservationRepository reservationRepository;

    public ReservationRestController(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @GetMapping("/reservations/{id}")
    public Reservation findReservation(@PathVariable("id") Long id){
        LOGGER.info("inside findReservation() for id: "+id);
        return reservationRepository.findById(id).orElse(null);
    }

    @PostMapping("/reservations")
    public Reservation updateReservation(@RequestBody ResrvationUpdateRequest request){
        LOGGER.info("inside updateReservation() for "+request);
        Reservation reservation = reservationRepository.findById(request.getId()).orElse(null);
        reservation.setNumberOfBags(request.getNumberOfBags());
        reservation.setCheckedIn(request.getCheckedIn());
        LOGGER.info("saving reservation");
        return reservationRepository.save(reservation);
    }
}
