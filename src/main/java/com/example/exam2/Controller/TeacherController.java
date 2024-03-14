package com.example.exam2.Controller;

import com.example.exam2.Model.Teacher;
import com.example.exam2.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("/teachers")
    public ResponseEntity getTeachers(){
        ArrayList<Teacher> teachers = teacherService.getTeachers();
        if(teachers.isEmpty()){
            return ResponseEntity.status(400).body("there are no teachers");
        }return ResponseEntity.status(200).body(teachers);
    }

    @PostMapping("/add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        teacherService.addTeacher(teacher);
        return ResponseEntity.status(200).body("teacher added");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateTeacher(@PathVariable String id, @RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdated = teacherService.updateTeacher(id,teacher);
        if(isUpdated){
            return ResponseEntity.status(200).body("teacher updated");
        }else return ResponseEntity.status(400).body("teacher not found");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable String id){
        boolean isRemoved = teacherService.deleteTeacher(id);
        if(isRemoved){
            return ResponseEntity.status(200).body("teacher removed");
        }else return ResponseEntity.status(400).body("teacher not found");
    }

    @GetMapping("/teacher-by/{id}")
    public ResponseEntity getTeacherById(@PathVariable String id){
        Teacher teacher = teacherService.getTeacherById(id);
        if(teacher != null){
            return ResponseEntity.status(200).body(teacher);
        }else return ResponseEntity.status(400).body("teacher not found");
    }

    @GetMapping("/teacher-by-salary/{salary}")
    public ResponseEntity getTeacherBySalary(@PathVariable double salary){
        ArrayList<Teacher> teachersBySalary = teacherService.getTeacherBySalary(salary);
        if(teachersBySalary.isEmpty()){
            return ResponseEntity.status(400).body("there are no teacher with this or above salary: "+salary);
        }else return ResponseEntity.status(200).body(teachersBySalary);
    }
}