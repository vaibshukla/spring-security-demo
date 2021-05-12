package com.learning.springsecurity.springsecuritydemo.repository;

import com.learning.springsecurity.springsecuritydemo.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<Otp , Long> {

    Optional<Otp> findByUsername(String otp);
}
