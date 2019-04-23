package xyz.vusibaloyi.schoolsystem;

public class SubjectRegistrar {

    public static void registerSubjects(Student student, Subject mandatorySubject, int numberOfSubjectsToRegister) {
        //register student for a given number of subjects
        student.registerSubject(mandatorySubject);
        int count = 0;
        for (Subject subject : Subject.values()) {
            if (subject != mandatorySubject) {
                student.registerSubject(subject);
                count++;
            }
            if (count == numberOfSubjectsToRegister) {
                break;
            }
        }
    }
}
