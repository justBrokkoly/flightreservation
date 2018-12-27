package com.justBrokkoly.flightreservation.repos;

import com.justBrokkoly.flightreservation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
}
