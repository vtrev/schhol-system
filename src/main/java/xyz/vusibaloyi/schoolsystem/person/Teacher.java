package xyz.vusibaloyi.schoolsystem.person;

import xyz.vusibaloyi.schoolsystem.lesson.Lesson;
import xyz.vusibaloyi.schoolsystem.subject.Subject;

import java.util.ArrayList;

public class Teacher extends Person {

    private final  ArrayList<Subject> teacherSubjects;
    private int lessonsTaughtCount = 0;

    public Teacher(String firstName, String lastName, String emailAddress,ArrayList<Subject> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.teacherSubjects = subjects;
    }

    public String teachLesson(Lesson lesson){
        if(lesson.startLesson().equals("Lesson started")){
            this.updateTokenCount(5);
            this.lessonsTaughtCount++;
            return "Lesson started";
        }
        return lesson.startLesson();
    }

    public void updateTokenCount(double numberOfTokens){
        if(this.tokenCount + numberOfTokens >= 0){
            this.tokenCount   += numberOfTokens;
        }
    }

    //getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public ArrayList<Subject> getTeacherSubjects() {
        return teacherSubjects;
    }

    public double getTokenCount(){
        return tokenCount;
    }

    public int getLessonsTaughtCount() {
        return lessonsTaughtCount;
    }
}
