package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;
import xyz.vusibaloyi.schoolsystem.lesson.Lesson;
import xyz.vusibaloyi.schoolsystem.person.Student;
import xyz.vusibaloyi.schoolsystem.person.Teacher;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class LessonTest {

    @Test
    void acceptQualifiedTeacher() {
        ArrayList<Subject> subjectList = new ArrayList<Subject>(){{
            add(Subject.ACCOUNTING);
            add(Subject.ECONOMICS);
        }};
        Teacher teacher = new Teacher("Mike", "John", "vusi@baloyi.com", subjectList);
        Lesson accountingLesson = new Lesson(Subject.ACCOUNTING, "10:00");

        //assertion
        assertEquals(accountingLesson.acceptTeacher(teacher), "Teacher accepted");
    }

    @Test
    void rejectUnqualifiedTeacher() {
        ArrayList<Subject> subjectList = new ArrayList<Subject>(){{
            add(Subject.ECONOMICS);
        }};
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        Lesson accountingLesson = new Lesson(Subject.ACCOUNTING, "12:00");

        //assertion
        assertEquals(teacher.teachLesson(accountingLesson), "Teacher not qualified to teach this lesson");
    }

    @Test
    void acceptRegisteredStudent() {
        Lesson economicsLesson = new Lesson(Subject.ECONOMICS,"15:00");
        Student vusi = new Student("Vusi","Joey","joey@mail.ru");
        vusi.registerSubject(Subject.ECONOMICS);
        vusi.registerSubject(Subject.ENGLISH);
        vusi.registerSubject(Subject.ACCOUNTING);
        
        //asserion
        assertEquals(economicsLesson.acceptStudent(vusi),"Student accepted");
    }

    @Test
    void rejectUnregisteredStudent() {
        Student vusi = spy(new Student("Vusi","Nthuxi","vusi@bal.com"));
        //mock a registered subject count of 5
        doReturn(5).when(vusi).getRegisteredSubjectCount();

        Lesson economicsLesson = new Lesson(Subject.STATISTICS,"15:00");
        vusi.registerSubject(Subject.ENGLISH);
        vusi.registerSubject(Subject.ECONOMICS);
        vusi.registerSubject(Subject.ACCOUNTING);

        //assertion
        assertEquals(economicsLesson.acceptStudent(vusi),"Student rejected, not registered for subject");
    }

    @Test
    void rejectStudentWithLessThan3SubjectsRegistered() {
        Lesson economicsLesson = new Lesson(Subject.COMPUTER_SCIENCE,"15:00");
        Student vusi = new Student("Vusi","Joey","joey@mail.ru");
        vusi.registerSubject(Subject.COMPUTER_SCIENCE);
        vusi.registerSubject(Subject.ECONOMICS);
        assertEquals(economicsLesson.acceptStudent(vusi),"Student rejected, not enough registered subjects");
    }

    @Test
    void startLessonIfAllRequirementsMet() {
        ArrayList<Subject> subjectList = new ArrayList<Subject>();
        subjectList.add(Subject.ECONOMICS);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        Lesson economicsLesson = new Lesson(Subject.ECONOMICS, "15:00");
        List<Student> dummyStudents = TestStudents.generateStudents();

        for(Student student : dummyStudents){
            economicsLesson.acceptStudent(student);
        }
        economicsLesson.acceptTeacher(teacher);
        //assertion
        assertEquals(economicsLesson.startLesson(),"Lesson started");
    }

    @Test
    void doNotStartLessonIfTeacherRequirementsNotMet() {
        ArrayList<Subject> subjectList = new ArrayList<Subject>();
        subjectList.add(Subject.COMPUTER_SCIENCE);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        Lesson economicsLesson = new Lesson(Subject.ECONOMICS, "15:00");
        economicsLesson.acceptTeacher(teacher);
        //assertion
        assertEquals(economicsLesson.startLesson(),"Teacher not qualified to teach this lesson");
    }

    @Test
    void doNotStartLessonIfStudentsRequirementsNotMet() {
        ArrayList<Subject> subjectList = new ArrayList<Subject>();
        subjectList.add(Subject.COMPUTER_SCIENCE);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        Lesson computerScienceLesson = new Lesson(Subject.COMPUTER_SCIENCE, "15:00");
        computerScienceLesson.acceptTeacher(teacher);
        //no students will be entered into this lesson in order to test

        //assertion
        assertEquals(computerScienceLesson.startLesson(),"Not enough students");
    }
}