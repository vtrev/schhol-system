package xyz.vusibaloyi.schoolsystem;
import java.util.ArrayList;

public class Student {

    private final String firstName;
    private final String lastName;
    private  final String emailAddress;
    private  ArrayList<Subject> registeredSubjects;
    private  ArrayList<Lesson> attendedLessons;
    private ArrayList<LessonNote> studentNotes;
    private int tokenCount = 0;
    private boolean inLessonState = false;

    public Student(String firstName, String lastName, String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.registeredSubjects = new ArrayList<Subject>();
        this.attendedLessons = new ArrayList<Lesson>();
        this.studentNotes = new ArrayList<LessonNote>();
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

    public void receiveNotes(LessonNote notes){
        this.studentNotes.add(notes);
    }
    public String buyNotes(Subject subject){
        int notePrice = 2;
        if(!this.registeredSubjects.contains(subject)){
            notePrice = 5;
        }
        boolean canBuy = (this.tokenCount - notePrice >= 0);
        if(canBuy){
            LessonNote tempNotes = new LessonNote(subject);
            this.studentNotes.add(tempNotes);
            this.updateTokenCount(-notePrice); ;
            return "Bought "+tempNotes.getNotes()+" successfully.";
        }
        return "Insufficient token balance";
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
    public ArrayList<LessonNote> getStudentNotes(){
        return this.studentNotes;
    }
}
