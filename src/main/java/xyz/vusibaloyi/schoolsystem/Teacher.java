package xyz.vusibaloyi.schoolsystem;

import java.util.ArrayList;

public class Teacher {
    private final String firstName;
    private final String lastName;
    private final String emailAddress;
    private final  ArrayList<Subjects> teacherSubjects;

    public Teacher(String firstName, String lastName, String emailAddress,ArrayList<Subjects> subjects) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.teacherSubjects = subjects;
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
}
