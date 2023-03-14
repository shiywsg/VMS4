package com.ntu.sctp.group1.repository;

import com.ntu.sctp.group1.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailabilityRepository extends JpaRepository<Availability, Integer> {
}
