package com.example.exam2.Controller;

import com.example.exam2.Model.Student;
import com.example.exam2.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity getAllStudents(){
        return ResponseEntity.status(200).body(studentService.getAllStudent());
    }
    @PostMapping("/add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        studentService.addStudent(student);
        return ResponseEntity.status(200).body("student added");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateStudent(@PathVariable String id, @RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = studentService.updateStudent(id,student);
        if(isUpdated){
            return ResponseEntity.status(200).body("student updated");
        }else return ResponseEntity.status(400).body("student not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteStudent(@PathVariable String id){
        boolean isRemoved = studentService.deleteStudent(id);
        if(isRemoved){
            return ResponseEntity.status(200).body("student removed");
        }else return ResponseEntity.status(400).body("student not found");
    }
    @GetMapping("/student-by/{name}")
    public ResponseEntity getStudentByName(@PathVariable String name){
        Student student = studentService.getStudentByName(name);
        if(student != null){
            return ResponseEntity.status(200).body(student);
        }else return ResponseEntity.status(400).body("student not found");
    }

    @GetMapping("students-by/{major}")
    public ResponseEntity getStudentsByMajor(@PathVariable String major){
        ArrayList<Student> studentsByMajor = studentService.getStudentsByMajor(major);
        if(studentsByMajor.isEmpty()){
            return ResponseEntity.status(400).body("there are no student by major: "+ major);
        }else return ResponseEntity.status(200).body(studentsByMajor);
    }
}
