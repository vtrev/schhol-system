package xyz.vusibaloyi.schoolsystem;

import java.util.ArrayList;
import java.util.List;

public class Lesson {
//    todo : try to take in a Localtime Object instead of string

    private Subject subject;
    private  Teacher teacher;
    private String lessonTime;
    private List<Student>  studentsInLesson = new ArrayList<Student>();
    private boolean lessonInProgress = false;

    public Lesson(Subject subject, String time){
        this.subject = subject;
        this.lessonTime = time;
//            this.teacher = teacher;
//            this.lessonInProgress = true;
    }


    public String acceptTeacher(Teacher teacher){
        if(teacher.getTeacherSubjects().contains(this.subject)){
            this.teacher = teacher;
            this.lessonInProgress = true;
            return "Teacher accepted";
        }
        return "Teacher rejected,unqualified teacher";
    }

    public String acceptStudent(Student student){
        if(student.getRegisteredSubjectCount()>= 3){
        if(student.getRegisteredSubjects().contains(this.subject)){
        studentsInLesson.add(student);
        return "Student accepted";
        }
        return "Student rejected, not registered for subject";
        }
        return "Student rejected, not enough registered subjects";
    }

    public String startLesson(){
        if(this.lessonInProgress){
        if(this.studentsInLesson.size() >= 5){
                for(Student s:this.studentsInLesson){
                    s.attendLesson(this);
                }
            return "Lesson started";
            }
        return "Not enough students";
        }
        return "Teacher not qualified to teach this lesson";
    }

    public void endLesson(){
        for(Student s:this.studentsInLesson){
            s.exitLesson();
        }
    }
}
