package xyz.vusibaloyi.schoolsystem;
import xyz.vusibaloyi.schoolsystem.person.Student;
import xyz.vusibaloyi.schoolsystem.person.StudentService;
import xyz.vusibaloyi.schoolsystem.person.Teacher;
import xyz.vusibaloyi.schoolsystem.person.TeacherService;

import java.util.ArrayList;

public class Main{

public static void main(String[]args){
    StudentService studentService = new StudentService();
    TeacherService teacherService = new TeacherService();
    ArrayList<Subject> subjects = new ArrayList<Subject>();
    
    
//    Student rendani =  new Student("Rendani","Lukhwa","rendani@mail.com");

//    studentService.saveStudent(rendani);
    
    Student rendani = studentService.getStudentById(140);
    System.out.println(rendani);

    }
}