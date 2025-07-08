package com.EduHub.Eduhub.Controller;

import com.EduHub.Eduhub.Dto.ForgotPassWordRequest;
import com.EduHub.Eduhub.Dto.LoginRequest;
import com.EduHub.Eduhub.Entity.StudentEntity;
import com.EduHub.Eduhub.Dto.RegisterStudentRequest;
import com.EduHub.Eduhub.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")  // root path
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/RegisterStudent")
    public ResponseEntity<StudentEntity> registerStudent(@RequestBody RegisterStudentRequest request) {
        StudentEntity student = studentService.createStudent(request);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentEntity>> getAllStudents() {
        List<StudentEntity> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.ACCEPTED);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<List<StudentEntity>> getUserById(@PathVariable Long id) {
        List<StudentEntity> student = studentService.getStudent(id);
        return new ResponseEntity<>(student,HttpStatus.ACCEPTED);
    }

    @PostMapping("/Login")
    public String LoginStudent(@RequestBody LoginRequest request){
        studentService.loginStudent(request);
        return "Student Login Successfully";
    }

    @PutMapping("/forgotten-Password")
    public ResponseEntity<StudentEntity> forgotPassword(@RequestBody ForgotPassWordRequest request){
        return studentService.forgotPassword(request);
    }
}
