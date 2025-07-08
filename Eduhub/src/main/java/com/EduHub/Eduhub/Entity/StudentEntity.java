package com.EduHub.Eduhub.Entity;

import jakarta.persistence.*;
import jdk.jfr.Timestamp;
import lombok.*;
import org.aspectj.bridge.Message;
import org.aspectj.lang.annotation.RequiredTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "students")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @Column(name = "roll_number",nullable = false, unique = true)
    private Long rollNumber;
    private String name;
    private String email;
    private String password;
    private String PhoneNo;
    private String address;
    private String gender;
    private LocalDate date_of_birth;
    private String course;
    private String department;
    private String status;

    @Timestamp
    private LocalDateTime createdAt;

    @Timestamp
    private LocalDateTime updatedAt;
}

