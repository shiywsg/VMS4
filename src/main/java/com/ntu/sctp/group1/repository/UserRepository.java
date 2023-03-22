package com.ntu.sctp.group1.repository;

import com.ntu.sctp.group1.entity.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserCredentials, Integer> {

    Optional<UserCredentials> findByUid(String uid);
}
