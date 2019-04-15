package xyz.vusibaloyi.schoolsystem;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LessonTest {

    @Test
    void acceptQualifiedTeacher() {
        ArrayList<Subjects> subjectList = new ArrayList<Subjects>();
        subjectList.add(Subjects.ACCOUNTING);
        subjectList.add(Subjects.ECONOMICS);
        Teacher teacher = new Teacher("Mike", "John", "vusi@baloyi.com", subjectList);
        Lesson accountingLesson = new Lesson(Subjects.ACCOUNTING, "10:00");
        //create 5 students and register them for at least subjects
        for (int i = 0; i < 5; i++) {
            Student s = new Student("studentFirstname" + i, "studentLastname" + i, "student+" + i + "@mail.com");
            s.registerSubject(Subjects.ACCOUNTING);
            s.registerSubject(Subjects.ECONOMICS);
            s.registerSubject(Subjects.ENGLISH);
            accountingLesson.acceptStudent(s);
        }
        //assertion
        assertEquals(accountingLesson.acceptTeacher(teacher), "Teacher accepted");
    }

    @Test
    void rejectUnqualifiedTeacher() {
        ArrayList<Subjects> subjectList = new ArrayList<Subjects>();
        subjectList.add(Subjects.ECONOMICS);
        Teacher teacher = new Teacher("Vusi", "Baloyi", "vusi@baloyi.com", subjectList);
        Lesson accountingLesson = new Lesson(Subjects.ACCOUNTING, "12:00");
        //create 5 students and register them for at least subjects
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
    void acceptRegisteredStudent() {
        Lesson economicsLesson = new Lesson(Subjects.ECONOMICS,"15:00");
        Student vusi = new Student("Vusi","Joey","joey@mail.ru");
        vusi.registerSubject(Subjects.ENGLISH);
        vusi.registerSubject(Subjects.ECONOMICS);
        vusi.registerSubject(Subjects.ACCOUNTING);
        assertEquals(economicsLesson.acceptStudent(vusi),"Student accepted");

    }

    @Test
    void rejectUnregisteredStudent() {
        Lesson economicsLesson = new Lesson(Subjects.COMPUTER_SCIENCE,"15:00");
        Student vusi = new Student("Vusi","Joey","joey@mail.ru");
        vusi.registerSubject(Subjects.ENGLISH);
        vusi.registerSubject(Subjects.ECONOMICS);
        vusi.registerSubject(Subjects.ACCOUNTING);
        assertEquals(economicsLesson.acceptStudent(vusi),"Student rejected, not registered for subject");

    }

    @Test
    void rejectStudentWithLessThan3SubjectsRegistered() {
        Lesson economicsLesson = new Lesson(Subjects.COMPUTER_SCIENCE,"15:00");
        Student vusi = new Student("Vusi","Joey","joey@mail.ru");
        vusi.registerSubject(Subjects.COMPUTER_SCIENCE);
        vusi.registerSubject(Subjects.ECONOMICS);
        assertEquals(economicsLesson.acceptStudent(vusi),"Student rejected, not enough registered subjects");

    }



    @Test
    void startLesson() {
    }

    @Test
    void endLesson() {
    }
}