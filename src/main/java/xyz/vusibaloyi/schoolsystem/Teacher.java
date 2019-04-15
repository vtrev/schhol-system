package xyz.vusibaloyi.schoolsystem;

import java.util.ArrayList;

public class Teacher {
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final  ArrayList<Subjects> teacherSubjects;
    private int tokenCount = 0;

    public Teacher(String firstName, String lastName, String emailAddress,ArrayList<Subjects> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.teacherSubjects = subjects;
    }

    public String teachLesson(Lesson lesson){
        if(lesson.startLesson().equals("Lesson started")){
            this.updateTokenCount(5);
            return "Lesson started";
        }
        return lesson.startLesson();
    }


    //setters
    public void updateTokenCount(int numberOfTokens){
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

    public ArrayList<Subjects> getTeacherSubjects() {
        return teacherSubjects;
    }

    public int getTokenCount(){
        return tokenCount;
    }
}
