package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    void constructorTest(){
      Student  student = new Student("Foo","Bar","foo@bar.ru");
        assertEquals(student.getFirstName(),"Foo");
        assertEquals(student.getLastName(),"Bar");
        assertEquals(student.getEmailAddress(),"foo@bar.ru");
        assertEquals(student.getTokenCount(),0);
    }

    @Test
    void registerSubject() {
       Student student = new Student("Foo","Bar","foo@bar.ru");
        student.registerSubject(Subject.COMPUTER_SCIENCE);
        assertEquals(student.getRegisteredSubjects().contains(Subject.COMPUTER_SCIENCE),true);
    }

    @Test
    void attendLessonIfRegisteredFor3OrMoreSubjects() {
        Student student = new Student("Foo","Bar","foo@bar.ru");
        student.registerSubject(Subject.COMPUTER_SCIENCE);
        student.registerSubject(Subject.ECONOMICS);
        student.registerSubject(Subject.ACCOUNTING);

        Lesson compscieLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");
        assertEquals(student.attendLesson(compscieLesson),"Attending lesson");
        assertEquals(student.getInLessonState(),true);
    }

    @Test
    void doNotAttendLessonIfAlreadyInAnotherLesson() {
        Student foo = new Student("Foo","Bar","foo@bar.ru");
        foo.registerSubject(Subject.COMPUTER_SCIENCE);
        foo.registerSubject(Subject.ECONOMICS);
        foo.registerSubject(Subject.ACCOUNTING);

        Lesson compscieLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");
        Lesson economicsLesson = new Lesson(Subject.ECONOMICS,"11:15");
        assertEquals(foo.attendLesson(compscieLesson),"Attending lesson");
        assertEquals(foo.getInLessonState(),true);
//        next assertion should fail since student already in the 11:00 ComputerScience lesson lesson
        assertEquals(foo.attendLesson(economicsLesson),"Cannot attend lesson,student Foo already in lesson.");
    }

    @Test
    void DoNotAttendLessonIfNotRegisteredFor3OrMoreSubjects() {
       Student student = new Student("Foo","Bar","foo@bar.ru");
        student.registerSubject(Subject.COMPUTER_SCIENCE);
        student.registerSubject(Subject.ECONOMICS);

        Lesson compscieLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");
        assertEquals(student.attendLesson(compscieLesson),"Student has  less than 3 subjects registered");
    }

    @Test
    void updateTokenCount() {

        Student mike = new Student("Mike","Davids","chakaron@makaron.com");
        int currentTokenCount = mike.getTokenCount();
        mike.updateTokenCount(15);
        assertEquals(currentTokenCount+15,mike.getTokenCount());
    }

    @Test
    void exitLesson() {

        Student joe = new Student("Joe","Doe","joe@doe.za");
        joe.registerSubject(Subject.COMPUTER_SCIENCE);
        joe.registerSubject(Subject.ECONOMICS);
        joe.registerSubject(Subject.ACCOUNTING);
        Lesson accountingLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");
        joe.attendLesson(accountingLesson);
        assertEquals(joe.getInLessonState(),true);
        //lesson state should be false after exit
        joe.exitLesson();
        assertEquals(joe.getInLessonState(),false);
    }




    @Test
    void getTokenCount() {
        Student dan = new Student("Dan","Waters","dan@mail.com");
        //token count is always 0 initially
        assertEquals(0,dan.getTokenCount());
    }
}