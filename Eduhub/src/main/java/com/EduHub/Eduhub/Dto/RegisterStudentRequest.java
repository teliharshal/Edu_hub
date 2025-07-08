package com.EduHub.Eduhub.Dto;

import jdk.jfr.Timestamp;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class RegisterStudentRequest {
    private Long rollNumber;
    private String name;
    private String email;
    private String password;
    private String phoneNo;
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
