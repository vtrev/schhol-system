package xyz.vusibaloyi.schoolsystem.person;
import xyz.vusibaloyi.schoolsystem.lesson.Lesson;
import xyz.vusibaloyi.schoolsystem.lesson.LessonNote;
import xyz.vusibaloyi.schoolsystem.Subject;

import java.util.ArrayList;

public class Student extends Person {

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
        registeredSubjects.add(subject);
    }

    public String attendLesson(Lesson lesson){

        if(!inLessonState){
        if(getRegisteredSubjectCount() >= 3){
            inLessonState = true;
            attendedLessons.add(lesson);
        return "Attending lesson";
        }
        return "Student has  less than 3 subjects registered";
        }
        return "Cannot attend lesson,student "+firstName+" already in lesson.";
    }

    public void exitLesson(){
        inLessonState = false;
    }

    public void receiveNotes(LessonNote notes){
        attendanceNotes.add(notes);
    }

    public boolean isRegisteredSubject(Subject subject){
        return getRegisteredSubjects().contains(subject);
    }

    public String buyNotes(LessonNote notes,Student seller){
        if(seller.getPurchasedNotes().contains(notes) | seller.getAttendanceNotes().contains(notes)){
        int notePrice = 2;
        if(!isRegisteredSubject(notes.getSubject())){
            notePrice = 5;
        }
        boolean canBuy = (getTokenCount() - notePrice >= 0);
        if(canBuy){
            purchasedNotes.add(notes);
            updateTokenCount(-notePrice); ;
            return "Bought "+notes.getNotes()+" successfully.";
        }
        return "Insufficient token balance";
        }
        return "Cannot complete transaction,seller "+seller.getFirstName()+ " does not have "+notes.getNotes();
    }
    
    
    //setter
    public void setPurchasedNotes(LessonNote notes){
        this.purchasedNotes.add(notes);
    }

    
    //getters

    public ArrayList<Subject> getRegisteredSubjects() {
        return registeredSubjects;
    }

    public int getRegisteredSubjectCount(){
        return registeredSubjects.size();
    }

    public int getAttendedLessonsCount() {
        return attendedLessons.size();
    }

    public ArrayList<LessonNote> getPurchasedNotes(){
        return purchasedNotes;
    }

    public ArrayList<LessonNote> getAttendanceNotes(){
        return attendanceNotes;
    }

    public boolean getInLessonState(){
        return inLessonState;
    }

 
}
