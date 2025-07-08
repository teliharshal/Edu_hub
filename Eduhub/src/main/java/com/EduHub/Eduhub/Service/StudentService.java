package com.EduHub.Eduhub.Service;

import com.EduHub.Eduhub.Dto.ForgotPassWordRequest;
import com.EduHub.Eduhub.Dto.LoginRequest;
import com.EduHub.Eduhub.Dto.RegisterStudentRequest;
import com.EduHub.Eduhub.Entity.StudentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    StudentEntity createStudent(RegisterStudentRequest request);

    List<StudentEntity> getAllStudents();

    List<StudentEntity> getStudent(Long id);

    String loginStudent(LoginRequest request);

    ResponseEntity<StudentEntity> forgotPassword(ForgotPassWordRequest request);
}
