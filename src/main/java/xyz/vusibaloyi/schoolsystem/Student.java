package xyz.vusibaloyi.schoolsystem;
import java.util.ArrayList;

public class Student {

    private final String firstName;
    private final String lastName;
    private  final String emailAddress;
    private final ArrayList<Subject> registeredSubjects;
    private final ArrayList<Lesson> attendedLessons;
    private int tokenCount = 0;
    private boolean inLessonState = false;

    public Student(String firstName, String lastName, String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.registeredSubjects = new ArrayList<Subject>();
        this.attendedLessons = new ArrayList<Lesson>();

    }
    public void registerSubject(Subject subject){
        this.registeredSubjects.add(subject);
    }

    public String attendLesson(Lesson lesson){

        if(!this.inLessonState){
        if(this.registeredSubjects.size() >= 3){
            this.inLessonState = true;
        this.attendedLessons.add(lesson);
        return "Attending lesson";
        }
        return "Student has  less than 3 subjects registered";
        }
        return "Cannot attend lesson,student "+this.firstName+" already in lesson.";
    }

    public void exitLesson(){
        this.updateTokenCount(3);
        this.inLessonState = false;
    }

    public void updateTokenCount(int numberOfTokens){
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

    public ArrayList<Subject> getRegisteredSubjects() {
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
    public boolean getInLessonState(){
        return this.inLessonState;
    }
}
