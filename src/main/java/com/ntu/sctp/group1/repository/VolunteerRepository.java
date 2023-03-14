package com.ntu.sctp.group1.repository;

import com.ntu.sctp.group1.entity.Volunteer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepository extends JpaRepository<Volunteer, Integer> {

    List<Volunteer> findAll();
    List<Volunteer> findByLanguage(String language);
}
