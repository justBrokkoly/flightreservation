package com.justBrokkoly.flightreservation.repos;



import com.justBrokkoly.flightreservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
