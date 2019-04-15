package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    //Testing the constructor
    private Student student;
    @Test
    void constructorTest(){
        student = new Student("Foo","Bar","foo@bar.ru");
        assertEquals(student.getFirstName(),"Foo");
        assertEquals(student.getLastName(),"Bar");
        assertEquals(student.getEmailAddress(),"foo@bar.ru");
        assertEquals(student.getTokenCount(),0);
    }


    @Test
    void registerSubject() {
        student = new Student("Foo","Bar","foo@bar.ru");
        student.registerSubject(Subjects.COMPUTER_SCIENCE);
        assertEquals(student.getRegisteredSubjects().contains(Subjects.COMPUTER_SCIENCE),true);

    }

    @Test
    void attendLessonIfRegisteredFor3OrMoreSubjects() {
        student = new Student("Foo","Bar","foo@bar.ru");
        student.registerSubject(Subjects.COMPUTER_SCIENCE);
        student.registerSubject(Subjects.ECONOMICS);
        student.registerSubject(Subjects.ACCOUNTING);

        Lesson compscieLesson = new Lesson(Subjects.COMPUTER_SCIENCE,"11:00");
        assertEquals(student.attendLesson(compscieLesson),"Attending lesson");
    }

    @Test
    void DoNotAttendLessonIfNotRegisteredFor3OrMoreSubjects() {
        student = new Student("Foo","Bar","foo@bar.ru");
        student.registerSubject(Subjects.COMPUTER_SCIENCE);
        student.registerSubject(Subjects.ECONOMICS);

        Lesson compscieLesson = new Lesson(Subjects.COMPUTER_SCIENCE,"11:00");
        assertEquals(student.attendLesson(compscieLesson),"Student has  less than 3 subjects registered");
    }


    @Test
    void exitLesson() {
    }

    @Test
    void updateTokenCount() {
    }



    @Test
    void getRegisteredSubjects() {
    }



    @Test
    void getAttendedLessonsCount() {
    }

    @Test
    void getTokenCount() {
    }
}