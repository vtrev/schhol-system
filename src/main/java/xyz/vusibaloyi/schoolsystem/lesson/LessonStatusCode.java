package xyz.vusibaloyi.schoolsystem.lesson;

public enum LessonStatusCode {
    
    STUDENT_ACCEPTED("Student accepted"),
    ACCEPT_TEACHER("Teacher accepted"),
    ACCEPT_STUDENT("Student accepted"),
    REJECT_STUDENT_NOREG("Student rejected, not registered for subject"),
    REJECT_STUDENT_NO_ENOUGHSUB("Student rejected, not enough registered subjects"),
    START_LESSON("Lesson started"),
    NOSTART_NO_ENOUGHSTUD("Not enough students"),
    REJECT_TEACHER("Teacher not qualified to teach this lesson"),
    NOSTART_NO_TEACHER("Cannot start lesson, no qualified teacher available");
    
    
    
    
    
    
    
    private String message;
    
    LessonStatusCode(String message) {
        this.message = message;
    }
    
    public String getMessage(){
        return message;
    }
    
}
