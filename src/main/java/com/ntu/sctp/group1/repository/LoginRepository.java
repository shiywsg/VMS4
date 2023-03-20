package com.ntu.sctp.group1.repository;

import com.ntu.sctp.group1.entity.Login;

// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

    // Optional<Profile> findByVolunteerId(Integer volunteerid);
}