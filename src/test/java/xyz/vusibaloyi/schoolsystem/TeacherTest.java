package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeacherTest {


    @Test
    void shouldCreateTeacher() {
//        initialize
        ArrayList<Subjects> subjectList = new ArrayList<Subjects>();
        subjectList.add(Subjects.ACCOUNTING);
        subjectList.add(Subjects.ECONOMICS);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);

        //test if getter methods return correct info
        assertEquals(teacher.getFirstName(), "Vusi");
        assertEquals(teacher.getLastName(), "Baloyi");
        assertEquals(teacher.getEmailAddress(), "vusi@baloyi.com");
        assertEquals(teacher.getTeacherSubjects(), subjectList);
        assertEquals(teacher.getTokenCount(), 0);

    }

    @Test
    void qualifiedTeacherShouldBeAbleToTeach() {
        // initialize
        ArrayList<Subjects> subjectList = new ArrayList<Subjects>();
        subjectList.add(Subjects.ACCOUNTING);
        subjectList.add(Subjects.ECONOMICS);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        Lesson accountingLesson = new Lesson(Subjects.ACCOUNTING, "10:00");
//        create 5 students and register them for at least subjects
        for (int i = 0; i < 5; i++) {
            Student s = new Student("studentFirstname" + i, "studentLastname" + i, "student+" + i + "@mail.com");
            s.registerSubject(Subjects.ACCOUNTING);
            s.registerSubject(Subjects.ECONOMICS);
            s.registerSubject(Subjects.ENGLISH);
            accountingLesson.acceptStudent(s);
        }
        //assertion
        accountingLesson.acceptTeacher(teacher);
        assertEquals(teacher.teachLesson(accountingLesson), "Lesson started");
    }

    @Test
    void unqualifiedTeacherShouldNotBeAbleToTeach() {

        ArrayList<Subjects> subjectList = new ArrayList<Subjects>();
        subjectList.add(Subjects.ECONOMICS);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        Lesson accountingLesson = new Lesson(Subjects.ACCOUNTING, "12:00");
//        create 5 students and register them for at least subjects
        for (int i = 0; i < 5; i++) {
            Student s = new Student("studentFirstname" + i, "studentLastname" + i, "student+" + i + "@mail.com");
            s.registerSubject(Subjects.ACCOUNTING);
            s.registerSubject(Subjects.ECONOMICS);
            s.registerSubject(Subjects.ENGLISH);
            accountingLesson.acceptStudent(s);
        }
        //assertion
        assertEquals(teacher.teachLesson(accountingLesson), "Teacher not qualified");
    }


    @Test
    void updateTokenCount() {
        ArrayList<Subjects> subjectList = new ArrayList<Subjects>();
        subjectList.add(Subjects.ECONOMICS);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        //token = 0 initially

        teacher.updateTokenCount(15);
        assertEquals(teacher.getTokenCount(), 15);
        teacher.updateTokenCount(-5);
        assertEquals(teacher.getTokenCount(), 10);
    }














    //    Lesson tests




}