package com.example.exam2.Service;

import com.example.exam2.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();


    public ArrayList<Student> getAllStudent(){
        return students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public boolean updateStudent(String id,Student student){
        for(int i=0; i<students.size(); i++){
            if(students.get(i).getId().equalsIgnoreCase(id)){
                students.set(i, student);
                return true;
            }
        }
        return false;
    }
    public boolean deleteStudent(String id){
        for(int i = 0; i<students.size(); i++){
            if(students.get(i).getId().equalsIgnoreCase(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public Student getStudentByName(String name){
        for(int i=0; i<students.size();i++){
            if(students.get(i).getName().equalsIgnoreCase(name)){
                return students.get(i);
            }
        }
        return null;
    }
    public ArrayList<Student> getStudentsByMajor(String major){
        ArrayList<Student> studentsByMajor = new ArrayList<>();
        for(int i = 0; i<students.size(); i++){
            if(students.get(i).getMajor().equalsIgnoreCase(major)){
                studentsByMajor.add(students.get(i));
            }
        }
        return studentsByMajor;
    }

}
