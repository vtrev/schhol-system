package xyz.vusibaloyi.schoolsystem.person;

import xyz.vusibaloyi.schoolsystem.lesson.Lesson;
import xyz.vusibaloyi.schoolsystem.Subject;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Teacher extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "teacher_id",nullable = false,updatable = false)
    private int teacher_id;
    @Transient
    private ArrayList<Subject> teacherSubjects;
    @Transient
    private int lessonsTaughtCount = 0;

    public Teacher(String firstName, String lastName, String emailAddress,ArrayList<Subject> subjects) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmailAddress(emailAddress);
        setTeacherSubjects(subjects);
    }

    public String teachLesson(Lesson lesson){
        if(lesson.startLesson().equals("Lesson started")){
            this.updateTokenCount(5);
            this.lessonsTaughtCount++;
            return lesson.startLesson();
        }
        return lesson.startLesson();
    }
    
    //getters
    @Access(AccessType.PROPERTY)
    @Column(name="first_name")
    public String getFirstName(){
        return super.getFirstName();
    }
    
    @Access(AccessType.PROPERTY)
    @Column(name="last_name")
    public String getLastName(){
        return super.getLastName();
    }
    
    @Access(AccessType.PROPERTY)
    @Column(name="email_address")
    public String getEmailAddress(){
        return super.getEmailAddress();
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

    //setters
    
    public void setFirstName(String name){
        this.firstName = name;
    }
    public void setLastName(String name){
        this.lastName = name;
    }
    
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }
    
    public void setTeacherSubjects(ArrayList<Subject> teacherSubjects) {
        this.teacherSubjects = teacherSubjects;
    }
}
