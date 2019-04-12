package xyz.vusibaloyi.schoolsystem;
import java.util.ArrayList;

public class Student {

    private final String firstName;
    private final String lastName;
    private  final String emailAddress;
    private final ArrayList<Subjects> registeredSubjects;
    private final ArrayList<Lessons> attendedLessons;
    private int tokenCount = 0;

    public Student(String firstName, String lastName, String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.registeredSubjects = new ArrayList<Subjects>();
        this.attendedLessons = new ArrayList<Lessons>();

    }
    //setters
    public void registerSubject(Subjects subject){
        this.registeredSubjects.add(subject);
    }

    public void attendLesson(Lessons lesson){
        this.attendedLessons.add(lesson);
    }

    public void updateTokenCount(int numberOfTokens){
        if(this.tokenCount + numberOfTokens >= 0){
            this.tokenCount += numberOfTokens;
        }
        this.tokenCount += numberOfTokens;
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

    public int getRegisteredSubjectsCount() {
        return registeredSubjects.size();
    }

    public int getAttendedLessonsCount() {
        return attendedLessons.size();
    }

    public int getTokenCount() {
        return tokenCount;
    }
}
