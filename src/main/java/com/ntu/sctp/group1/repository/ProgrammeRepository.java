package com.ntu.sctp.group1.repository;

import com.ntu.sctp.group1.entity.Programme;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgrammeRepository extends JpaRepository<Programme, Integer> {

    // Optional<Profile> findByVolunteerId(Integer volunteerid);
}