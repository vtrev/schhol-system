package xyz.vusibaloyi.schoolsystem.lesson;

import xyz.vusibaloyi.schoolsystem.subject.Subject;
import xyz.vusibaloyi.schoolsystem.person.Student;
import xyz.vusibaloyi.schoolsystem.person.Teacher;

import java.util.ArrayList;
import java.util.List;

public class Lesson {

    private Subject subject;
    private String lessonTime;
    private List<Student> studentsInLesson = new ArrayList<Student>();
    private boolean lessonInProgress = false;
    private LessonNote notes;

    public Lesson(Subject subject, String time) {
        this.subject = subject;
        this.lessonTime = time;
        this.notes = new LessonNote(subject);
    }


    public String acceptTeacher(Teacher teacher) {
        if (teacher.getTeacherSubjects().contains(this.subject)) {
            this.lessonInProgress = true;
            return "Teacher accepted";
        }
        return "Teacher rejected,unqualified teacher";
    }

    public String acceptStudent(Student student) {
        if (student.getRegisteredSubjectCount() >= 3) {
            if (student.getRegisteredSubjects().contains(subject)) {
                studentsInLesson.add(student);
                return "Student accepted";
            }
            return "Student rejected, not registered for subject";
        }
        return "Student rejected, not enough registered subjects";
    }

    public String startLesson() {
        if (this.lessonInProgress) {
            if (this.studentsInLesson.size() >= 5) {
                for (Student s : this.studentsInLesson) {
                    s.attendLesson(this);
                }
                return "Lesson started";
            }
            return "Not enough students";
        }
        return "Teacher not qualified to teach this lesson";
    }

    public void endLesson() {
        for (Student student : this.studentsInLesson) {
            student.receiveNotes(this.notes);
            student.updateTokenCount(3);
            student.exitLesson();
        }
    }
}
