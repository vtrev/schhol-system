package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;
import xyz.vusibaloyi.schoolsystem.lesson.Lesson;
import xyz.vusibaloyi.schoolsystem.lesson.LessonNote;
import xyz.vusibaloyi.schoolsystem.person.Student;
import xyz.vusibaloyi.schoolsystem.subject.Subject;
import xyz.vusibaloyi.schoolsystem.subject.SubjectRegistrar;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class StudentTest {

    @Test
    void constructorTest(){
      Student student = new Student("Foo","Bar","foo@bar.ru");
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
    void DoNotAttendLessonIfNotRegisteredFor3OrMoreSubjects() {
        Student studentX = new Student("Foo","Bar","foo@bar.ru");
        //register only 2 subjects for studentX
        SubjectRegistrar.registerSubjects(studentX,"COMPUTER_SCIENCE,ECONOMICS");
        Lesson computerScienceLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");

        //assertion
        assertEquals(studentX.attendLesson(computerScienceLesson),"Student has  less than 3 subjects registered");
    }

    @Test
    void doNotAttendLessonIfAlreadyInAnotherLesson() {
        Student fooStudentMock = spy(new Student("James","Bond","bond@media.com"));
        doReturn(4).when(fooStudentMock).getRegisteredSubjectCount();

        Lesson computerScienceLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");
        Lesson economicsLesson = new Lesson(Subject.ECONOMICS,"11:15");

        //assertions
        assertEquals(fooStudentMock.attendLesson(computerScienceLesson),"Attending lesson");
        assertEquals(fooStudentMock.getInLessonState(),true);

        //next assertion should fail since student already in the 11:00 ComputerScience lesson lesson
        assertEquals(fooStudentMock.attendLesson(economicsLesson),"Cannot attend lesson,student James already in lesson.");
    }

    @Test
    void exitLesson() {
        Student joeStudentMock = spy(new Student("Joe","Doe","joe@doe.za"));
        doReturn(4).when(joeStudentMock).getRegisteredSubjectCount();

        Lesson computerScienceLesson = new Lesson(Subject.COMPUTER_SCIENCE,"11:00");
        joeStudentMock.attendLesson(computerScienceLesson);

        //assertions
        assertTrue(joeStudentMock.getInLessonState());
        //lesson state should be false after exit
        joeStudentMock.exitLesson();
        assertFalse(joeStudentMock.getInLessonState());
    }


    @Test
    void studentShouldReceiveNotes() {
        Student beki = new Student("Beki","Khosa","bekinkosikhosa@gmail.com");
        LessonNote economicsNotes = new LessonNote(Subject.ECONOMICS);
        beki.receiveNotes(economicsNotes);
        assertTrue(beki.getAttendanceNotes().contains(economicsNotes));
    }

    @Test
    void shouldBuyNotes() {
        Lesson economicsLesson = new Lesson(Subject.ECONOMICS,"11:00");
        Lesson accountingLesson = new Lesson(Subject.ACCOUNTING,"12:00");

        LessonNote notesMock = spy(new LessonNote(Subject.ECONOMICS));
        Student buyerStudentMock = spy(new Student("Buyer","Buy","ibuy@gmail.com"));
        Student sellerStudentMock = spy(new Student("Seller","Sell","isell@gmail.com"));
        ArrayList<LessonNote> sellerNotes = new ArrayList<LessonNote>(){{
            add(notesMock);
        }};

        doReturn(sellerNotes).when(sellerStudentMock).getPurchasedNotes();
        doReturn(true).when(buyerStudentMock).isRegisteredSubject(Subject.ECONOMICS);
        doReturn(4).when(buyerStudentMock).getRegisteredSubjectCount();
        doReturn(45.00).when(buyerStudentMock).getTokenCount();

        //assertion
        assertEquals(buyerStudentMock.buyNotes(notesMock,sellerStudentMock),"Bought ECONOMICS_NOTES successfully.");

    }

    @Test
    void shouldNotBuyNotesWthoutEnoughTokens() {
        Lesson economicsLesson = new Lesson(Subject.ECONOMICS,"11:00");
        Lesson accountingLesson = new Lesson(Subject.ACCOUNTING,"12:00");

        LessonNote notesMock = spy(new LessonNote(Subject.ECONOMICS));
        Student buyerStudentMock = spy(new Student("Buyer","Buy","ibuy@gmail.com"));
        Student sellerStudentMock = spy(new Student("Seller","Sell","isell@gmail.com"));

        ArrayList<LessonNote> sellerNotes = new ArrayList<LessonNote>(){{
            add(notesMock);
        }};

        doReturn(sellerNotes).when(sellerStudentMock).getAttendanceNotes();
        doReturn(true).when(buyerStudentMock).isRegisteredSubject(Subject.ECONOMICS);
        doReturn(4).when(buyerStudentMock).getRegisteredSubjectCount();
        doReturn(1.00).when(buyerStudentMock).getTokenCount();

        //assertion
        assertEquals(buyerStudentMock.buyNotes(notesMock,sellerStudentMock),"Insufficient token balance");

    }

    //move this test to Person test


}