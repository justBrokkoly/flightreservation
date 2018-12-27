package com.justBrokkoly.flightreservation.repos;


import com.justBrokkoly.flightreservation.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger,Long> {
}
