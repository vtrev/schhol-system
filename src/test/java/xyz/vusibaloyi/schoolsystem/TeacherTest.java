package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;
import xyz.vusibaloyi.schoolsystem.lesson.Lesson;
import xyz.vusibaloyi.schoolsystem.person.Student;
import xyz.vusibaloyi.schoolsystem.person.Teacher;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherTest {


    @Test
    void constructorTest() {
        ArrayList<Subject> subjectList = new ArrayList<Subject>();
        subjectList.add(Subject.ACCOUNTING);
        subjectList.add(Subject.ECONOMICS);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);

        //assertions
        assertEquals(teacher.getFirstName(), "Vusi");
        assertEquals(teacher.getLastName(), "Baloyi");
        assertEquals(teacher.getEmailAddress(), "vusi@baloyi.com");
        assertEquals(teacher.getTeacherSubjects(), subjectList);
        assertEquals(teacher.getTokenCount(), 0);

    }

    @Test
    void qualifiedTeacherShouldBeAbleToTeach() {
        // initialize
        ArrayList<Subject> subjectList = new ArrayList<Subject>(){{
            add(Subject.ACCOUNTING);
            add(Subject.ECONOMICS);
        }};

        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        Lesson accountingLesson = new Lesson(Subject.ACCOUNTING, "10:00");
        List<Student> dummyStudents = TestStudents.generateStudents();

        for(Student student : dummyStudents){
            accountingLesson.acceptStudent(student);
        }
        //assertion

        accountingLesson.acceptTeacher(teacher);
        assertEquals(teacher.teachLesson(accountingLesson), "Lesson started");
    }

    @Test
    void unqualifiedTeacherShouldNotBeAbleToTeach() {

        ArrayList<Subject> subjectList = new ArrayList<Subject>();
        subjectList.add(Subject.ECONOMICS);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        Lesson accountingLesson = new Lesson(Subject.ACCOUNTING, "12:00");

        //assertion
        assertEquals(teacher.teachLesson(accountingLesson), "Cannot start lesson, no qualified teacher available");
    }

//    @Test
//    void updateTokenCount() {
//        ArrayList<Subject> subjectList = new ArrayList<Subject>();
//        subjectList.add(Subject.ECONOMICS);
//        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
//        //token = 0 initially
//        teacher.updateTokenCount(15);
//        assertEquals(teacher.getTokenCount(), 15);
//        teacher.updateTokenCount(-5);
//        assertEquals(teacher.getTokenCount(), 10);
//    }
}