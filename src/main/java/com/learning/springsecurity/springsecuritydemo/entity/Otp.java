package com.learning.springsecurity.springsecuritydemo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "otp")
@Data
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String otp;
}
