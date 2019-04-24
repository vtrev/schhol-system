package xyz.vusibaloyi.schoolsystem;

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
