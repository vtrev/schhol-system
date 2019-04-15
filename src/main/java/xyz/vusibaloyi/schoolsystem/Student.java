package xyz.vusibaloyi.schoolsystem;
import java.util.ArrayList;

public class Student {

    private final String firstName;
    private final String lastName;
    private  final String emailAddress;
    private final ArrayList<Subjects> registeredSubjects;
    private final ArrayList<Lesson> attendedLessons;
    private int tokenCount = 0;
    private boolean inLesson = false;

    public Student(String firstName, String lastName, String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.registeredSubjects = new ArrayList<Subjects>();
        this.attendedLessons = new ArrayList<Lesson>();

    }
    public void registerSubject(Subjects subject){
        this.registeredSubjects.add(subject);
    }

    public void attendLesson(Lesson lesson){
        if(this.registeredSubjects.size() >= 3){
        this.inLesson = true;
        this.attendedLessons.add(lesson);
        }

    }

    public void exitLesson(Lesson lesson){
        this.updateTokenCount(3);
        this.inLesson = false;
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

    public ArrayList<Subjects> getRegisteredSubjects() {
        return registeredSubjects;
    }
    public int getRegisteredSubjectCount(){
        return this.registeredSubjects.size();
    }

    public int getAttendedLessonsCount() {
        return attendedLessons.size();
    }

    public int getTokenCount() {
        return tokenCount;
    }
}
