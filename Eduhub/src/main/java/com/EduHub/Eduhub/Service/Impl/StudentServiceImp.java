package com.EduHub.Eduhub.Service.Impl;

import com.EduHub.Eduhub.Dto.ForgotPassWordRequest;
import com.EduHub.Eduhub.Dto.LoginRequest;
import com.EduHub.Eduhub.Dto.RegisterStudentRequest;
import com.EduHub.Eduhub.Entity.StudentEntity;
import com.EduHub.Eduhub.Repository.StudentRepo;
import com.EduHub.Eduhub.Service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImp implements StudentService {

    @Autowired
    public final StudentRepo studentRepo;

    @Override
    public StudentEntity createStudent(RegisterStudentRequest request) {
        if(studentRepo.existsByRollNumber(request.getRollNumber())){
            throw new RuntimeException("Student Already Exists");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (request.getPassword() == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        String hashedPassword = encoder.encode(request.getPassword());

        StudentEntity student = new StudentEntity();
        student.setName(request.getName());
        student.setRollNumber(request.getRollNumber());
        student.setEmail(request.getEmail());
        student.setPassword(hashedPassword);
        student.setAddress(request.getAddress());
        student.setGender(request.getGender());
        student.setDate_of_birth(request.getDate_of_birth());
        student.setPhoneNo(request.getPhoneNo());
        student.setCourse(request.getCourse());
        student.setDepartment(request.getDepartment());
        student.setStatus(request.getStatus());

        student.setCreatedAt(LocalDateTime.now());
        student.setUpdatedAt(LocalDateTime.now());

        return studentRepo.save(student);

    }

    @Override
    public List<StudentEntity> getAllStudents() {
        return studentRepo.findAll();
    }

    @Override
    public List<StudentEntity> getStudent(Long id) {
        StudentEntity student = studentRepo.findById(id)
                .orElseThrow(() ->  new RuntimeException("User is not Exists"));
        return Collections.singletonList(student);
    }

    @Override
    public String loginStudent(LoginRequest request) {
        StudentEntity student = (StudentEntity) studentRepo.findByEmail(request.getEmail())
                .orElseThrow(()->  new RuntimeException("Invalid Email" + request.getEmail()));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if( !encoder.matches(request.getPassword(), student.getPassword())){
            return "Invalid Password";
        }

        return "User Login Successfully" + student.getName();
    }

    @Override
    public ResponseEntity<StudentEntity> forgotPassword(ForgotPassWordRequest request) {
        StudentEntity student = (StudentEntity) studentRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Student Email Not Found" + request.getEmail()));

        if (request.getNewpassword() == null || request.getNewpassword().trim().isEmpty()) {
            throw new IllegalArgumentException("New password must not be null or empty");
        }

        String passwordEncoder = new BCryptPasswordEncoder().encode(request.getNewpassword());
        student.setPassword(passwordEncoder);
        studentRepo.save(student);

        return ResponseEntity.ok(student);
    }
}
