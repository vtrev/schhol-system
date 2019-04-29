package xyz.vusibaloyi.schoolsystem.lesson;

import xyz.vusibaloyi.schoolsystem.Subject;

public class LessonNote {
    private Subject subject;

    public LessonNote(Subject subject){
        this.subject = subject;
    }

    public Subject getSubject(){
        return this.subject;
    }

    public String getNotes(){
        return this.subject.name()+"_NOTES";
    }
}
