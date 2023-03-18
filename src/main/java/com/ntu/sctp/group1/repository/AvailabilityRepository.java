package com.ntu.sctp.group1.repository;

import com.ntu.sctp.group1.entity.Availability;
import com.ntu.sctp.group1.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {

    Optional<Availability> findByVolunteerAndDate(Volunteer volunteer, LocalDate date);
    List<Availability> findByDate(LocalDate date);
}
