package com.ntu.sctp.group1.repository;

import com.ntu.sctp.group1.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

    List<Volunteer> findAll();
    List<Volunteer> findByLanguage(String language);
}

