package com.justBrokkoly.flightreservation.controller;

import com.justBrokkoly.flightreservation.dto.ReservationRequest;
import com.justBrokkoly.flightreservation.entity.Flight;
import com.justBrokkoly.flightreservation.entity.Reservation;
import com.justBrokkoly.flightreservation.repos.FlightRepository;
import com.justBrokkoly.flightreservation.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationController {
    private FlightRepository flightRepository;
    private ReservationService reservationService;

    public ReservationController(FlightRepository flightRepository, ReservationService reservationService) {
        this.flightRepository = flightRepository;
        this.reservationService = reservationService;
    }

    private static final Logger LOGGER=LoggerFactory.getLogger(ReservationController.class);

    @RequestMapping("/showCompleteReservation")
    public String showCompleteReservation(@RequestParam("flightId") Long flightId, Model model){
        LOGGER.info("showCompleteReservation() invoked with the Flight Id: "+ flightId);
        Flight flight=flightRepository.findById(flightId).get();
        model.addAttribute("flight", flight);
        LOGGER.info("Flight is "+flight);
        return "completeReservation";
    }

    @PostMapping("/completeReservation")
    public String completeReservation(ReservationRequest request, Model model){
        LOGGER.info("completeReservation() "+ request);
        Reservation reservation = reservationService.bookFlight(request);
        model.addAttribute("msg", "reservation created successfully and the id is "+reservation.getId());

        return "reservationConfirmation";
    }
}
