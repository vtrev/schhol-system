package xyz.vusibaloyi.schoolsystem;

import xyz.vusibaloyi.schoolsystem.person.Student;
import java.util.ArrayList;
import java.util.List;

public  class TestStudents {
    static List<Student> generateStudents(){
        List<Student> studentList = new ArrayList<Student>();
        for (int i = 0; i < 5; i++) {
            Student s = new Student("studentFirstname" + i, "studentLastname" + i, "student+" + i + "@mail.com");
            s.registerSubject(Subject.ACCOUNTING);
            s.registerSubject(Subject.ECONOMICS);
            s.registerSubject(Subject.ENGLISH);
            studentList.add(s);
        }
        return studentList;
    }
}