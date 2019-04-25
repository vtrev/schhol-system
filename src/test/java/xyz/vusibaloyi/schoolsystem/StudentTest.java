package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

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
        Student jamesStudentMock = spy(new Student("James","Bond","bond@media.com"));
        //mock a registered subject count of 4
        doReturn(4).when(jamesStudentMock).getRegisteredSubjectCount();

        //assertions
        Lesson computerScienceLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");
        assertEquals(jamesStudentMock.attendLesson(computerScienceLesson),"Attending lesson");
        assertEquals(jamesStudentMock.getInLessonState(),true);
    }

    @Test
    void doNotAttendLessonIfAlreadyInAnotherLesson() {
//        Student foo = new Student("Foo","Bar","foo@bar.ru");
        Student fooStudentMock = spy(new Student("James","Bond","bond@media.com"));
        //mock a registered subject count of 4
        doReturn(4).when(fooStudentMock).getRegisteredSubjectCount();


        //SubjectRegistrar.registerSubjects(foo,"COMPUTER_SCIENCE,ECONOMICS,ENGLISH");
        Lesson computerScienceLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");
        Lesson economicsLesson = new Lesson(Subject.ECONOMICS,"11:15");

        //assertions
        assertEquals(fooStudentMock.attendLesson(computerScienceLesson),"Attending lesson");
        assertEquals(fooStudentMock.getInLessonState(),true);
        //next assertion should fail since student already in the 11:00 ComputerScience lesson lesson
        assertEquals(fooStudentMock.attendLesson(economicsLesson),"Cannot attend lesson,student James already in lesson.");
    }

    @Test
    void DoNotAttendLessonIfNotRegisteredFor3OrMoreSubjects() {
       Student student = new Student("Foo","Bar","foo@bar.ru");
        student.registerSubject(Subject.COMPUTER_SCIENCE);
        student.registerSubject(Subject.ECONOMICS);
        Lesson computerScienceLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");

        assertEquals(student.attendLesson(computerScienceLesson),"Student has  less than 3 subjects registered");
    }

    @Test
    void updateTokenCount() {

        Student mike = new Student("Mike","Davids","chakaron@makaron.com");
        double currentTokenCount = mike.getTokenCount();
        mike.updateTokenCount(15);

        assertEquals(currentTokenCount+15,mike.getTokenCount());
    }

    @Test
    void exitLesson() {
        Student joe = new Student("Joe","Doe","joe@doe.za");
        SubjectRegistrar.registerSubjects(joe,"COMPUTER_SCIENCE,ECONOMICS,MATHEMATICS");
        Lesson computerScienceLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");
        joe.attendLesson(computerScienceLesson);

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

    @Test
    void studentShouldReceiveNotes() {
        Student beki = new Student("Beki","Khosa","bekinkosikhosa@gmail.com");
        LessonNote economicsNotes = new LessonNote(Subject.ECONOMICS);
        beki.receiveNotes(economicsNotes);
        assertEquals(beki.getAttendanceNotes().contains(economicsNotes),true);
    }

//    @Test
//    void shouldBuyNotesForRegisteredSubject() {
//        Lesson economicsLesson = new Lesson(Subject.ECONOMICS,"11:00");
//        Lesson accountingLesson = new Lesson(Subject.ACCOUNTING,"12:00");
//
//        //generate and enroll students into lessons
//        Student beki = new Student("Beki","Khosa","bekinkosikhosa@gmail.com");
//        SubjectRegistrar.registerSubjects(beki,Subject.ECONOMICS,Subject.values().length);
//        List<Student> testStudents = TestStudents.generateStudents();
//        //add student beki into the list of students
//        testStudents.add(beki);
//        for(Student student:testStudents){
//            economicsLesson.acceptStudent(student);
//            accountingLesson.acceptStudent(student);
//        }
//        //start and end lessons for students to get  6 tokens after both lessons
//        economicsLesson.startLesson();
//        economicsLesson.endLesson();
//        accountingLesson.startLesson();
//        accountingLesson.endLesson();
//        //assertions
//        assertEquals(beki.buyNotes(Subject.ECONOMICS),"Bought ECONOMICS_NOTES successfully.");
//        //beki must have a balance of 4 after buying notes for a registered subjects @2 tokens
//        assertEquals(beki.getTokenCount(),4);
//    }

    @Test
    void shouldBuyNotesForNonRegisteredSubject() {
        Lesson economicsLesson = new Lesson(Subject.ECONOMICS,"11:00");
        Lesson accountingLesson = new Lesson(Subject.ACCOUNTING,"12:00");

        //generate and enroll students into lessons
        Student vusi = new Student("Vusi","Baloyi","vusibaloyi@gmail.com");
        //register subjects manually in order to omit some
        vusi.registerSubject(Subject.ACCOUNTING);
        vusi.registerSubject(Subject.ECONOMICS);
        vusi.registerSubject(Subject.ENGLISH);

        List<Student> testStudents = TestStudents.generateStudents();
        //add student vusi into the list of students
        testStudents.add(vusi);
        for(Student student:testStudents){
            economicsLesson.acceptStudent(student);
            accountingLesson.acceptStudent(student);
        }
        //start and end lessons for students to get  6 tokens after both lessons
        economicsLesson.startLesson();
        economicsLesson.endLesson();
        accountingLesson.startLesson();
        accountingLesson.endLesson();
        //assertions
        assertEquals(vusi.buyNotes(Subject.COMPUTER_SCIENCE),"Bought COMPUTER_SCIENCE_NOTES successfully.");
        //vusi must have a balance of 1 after buying notes for unregistered subject @ 5 tokens
        assertEquals(vusi.getTokenCount(),1);
    }
}