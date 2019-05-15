package xyz.vusibaloyi.schoolsystem;
import xyz.vusibaloyi.schoolsystem.person.StudentService;
import xyz.vusibaloyi.schoolsystem.person.Teacher;
import xyz.vusibaloyi.schoolsystem.person.TeacherService;

import java.util.ArrayList;

public class Main{

public static void main(String[]args){
    StudentService studentService = new StudentService();
    TeacherService teacherService = new TeacherService();
    
    ArrayList<Subject> subjects = new ArrayList<Subject>();
    
    Teacher bill =  new Teacher("Bill","Tucker","billtucker@uwc.ac.za",subjects);
    teacherService.saveTeacher(bill);
    
    }
}