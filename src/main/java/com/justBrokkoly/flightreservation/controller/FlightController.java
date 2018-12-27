package com.justBrokkoly.flightreservation.controller;

import com.justBrokkoly.flightreservation.entity.Flight;
import com.justBrokkoly.flightreservation.repos.FlightRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
public class FlightController {

    private FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    private static final Logger LOGGER=LoggerFactory.getLogger(FlightController.class);

    @PostMapping("/findFlights")
    public String findFlights(@RequestParam("from") String from,@RequestParam("to") String to,
                 @RequestParam("departureDate") @DateTimeFormat(pattern = "MM-dd-yyyy")Date departureDate,Model model){
        LOGGER.info("inside  findFlights() "+from+" TO: "+to+" Departure Date: "+departureDate);
        List<Flight> flights=flightRepository.findFlights(from,to,departureDate);
        model.addAttribute("flights",flights);
        LOGGER.info("Flights Found are:"+flights);
        return "displayFlights";
    }

    @GetMapping("admin/showAddFlight")
    public String showAddFlight(){
        return "addFlight";
    }
}
