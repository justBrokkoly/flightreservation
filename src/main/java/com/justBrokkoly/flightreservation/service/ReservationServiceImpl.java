package com.justBrokkoly.flightreservation.service;

import com.justBrokkoly.flightreservation.Utils.EmailUtil;
import com.justBrokkoly.flightreservation.Utils.PDFGenerator;
import com.justBrokkoly.flightreservation.controller.UserController;
import com.justBrokkoly.flightreservation.dto.ReservationRequest;
import com.justBrokkoly.flightreservation.entity.Flight;
import com.justBrokkoly.flightreservation.entity.Passenger;
import com.justBrokkoly.flightreservation.entity.Reservation;
import com.justBrokkoly.flightreservation.repos.FlightRepository;
import com.justBrokkoly.flightreservation.repos.PassengerRepository;
import com.justBrokkoly.flightreservation.repos.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReservationServiceImpl implements ReservationService {

    FlightRepository flightRepository;
    PassengerRepository passengerRepository;
    PDFGenerator pdfGenerator;
    EmailUtil emailUtil;
    ReservationRepository reservationRepository;

    public ReservationServiceImpl(FlightRepository flightRepository, PassengerRepository passengerRepository,
                                  PDFGenerator pdfGenerator, EmailUtil emailUtil, ReservationRepository reservationRepository) {
        this.flightRepository = flightRepository;
        this.passengerRepository = passengerRepository;
        this.pdfGenerator = pdfGenerator;
        this.emailUtil = emailUtil;
        this.reservationRepository = reservationRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${com.justBrokkoly.flightreservation.itinerary.dirpath}")
    private  String ITINERARY_DIR;

    @Override
    @Transactional
    public Reservation bookFlight(ReservationRequest request) {

        LOGGER.info("inside bookFlight()");

     //Make payment

        Long flightId = request.getFlightId();
        LOGGER.info("Fetching flight for flight id: "+flightId);
        Flight flight = flightRepository.findById(flightId).orElse(null);

        Passenger passenger = new Passenger();
        passenger.setFirstName(request.getPassengerFirstName());
        passenger.setLastName(request.getPassengerLastName());
        passenger.setEmail(request.getPassengerEmail());
        passenger.setPhone(request.getPassengerPhone());
        LOGGER.info("Saving the passenger:"+passenger);
        Passenger savedPassenger = passengerRepository.save(passenger);

        Reservation reservation = new Reservation();
        reservation.setFlight(flight);
        reservation.setPassenger(savedPassenger);
        reservation.setCheckedIn(false);

        LOGGER.info("Saving the reservation:"+reservation);
        Reservation savedReservation = reservationRepository.save(reservation);

        String filePath = ITINERARY_DIR+savedReservation.getId()+".pdf";
        LOGGER.info("Generating itinerary");
        pdfGenerator.generateItinerary(savedReservation,filePath);
        LOGGER.info("Emailing the itinerary");
        emailUtil.sendItinerary(passenger.getEmail(),filePath);

        return savedReservation;
    }
}
