package xyz.vusibaloyi.schoolsystem;
import java.util.ArrayList;

public class Student extends Person{

    private  ArrayList<Subject> registeredSubjects;
    private  ArrayList<Lesson> attendedLessons;
    private ArrayList<LessonNote> attendanceNotes;
    private ArrayList<LessonNote> purchasedNotes;


    private boolean inLessonState = false;

    public Student(String firstName, String lastName, String emailAddress){
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.registeredSubjects = new ArrayList<Subject>();
        this.attendedLessons = new ArrayList<Lesson>();
        this.attendanceNotes = new ArrayList<LessonNote>();
        this.purchasedNotes = new ArrayList<LessonNote>();

    }
    public void registerSubject(Subject subject){
        this.registeredSubjects.add(subject);
    }

    public String attendLesson(Lesson lesson){

        if(!inLessonState){
        if(getRegisteredSubjectCount() >= 3){
            inLessonState = true;
        this.attendedLessons.add(lesson);
        return "Attending lesson";
        }
        return "Student has  less than 3 subjects registered";
        }
        return "Cannot attend lesson,student "+firstName+" already in lesson.";
    }

    public void exitLesson(){
        this.updateTokenCount(3);
        this.inLessonState = false;
    }

    public void receiveNotes(LessonNote notes){
        this.attendanceNotes.add(notes);
    }

    public String buyNotes(Subject subject){
        int notePrice = 2;
        if(!this.registeredSubjects.contains(subject)){
            notePrice = 5;
        }
        boolean canBuy = (this.tokenCount - notePrice >= 0);
        if(canBuy){
            LessonNote tempNotes = new LessonNote(subject);
            this.purchasedNotes.add(tempNotes);
            this.updateTokenCount(-notePrice); ;
            return "Bought "+tempNotes.getNotes()+" successfully.";
        }
        return "Insufficient token balance";
    }

    //getters


    public ArrayList<Subject> getRegisteredSubjects() {
        return registeredSubjects;
    }
    public int getRegisteredSubjectCount(){
        return this.registeredSubjects.size();
    }

    public int getAttendedLessonsCount() {
        return attendedLessons.size();
    }



    public ArrayList<LessonNote> getPurchasedNotes(){
        return this.purchasedNotes;
    }

    public ArrayList<LessonNote> getAttendanceNotes(){
        return this.attendanceNotes;
    }

    public boolean getInLessonState(){
        return this.inLessonState;
    }

}
