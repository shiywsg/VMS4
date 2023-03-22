package com.ntu.sctp.group1.repository;

import com.ntu.sctp.group1.entity.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolmentRepository extends JpaRepository<Enrolment, Integer> {
    
}