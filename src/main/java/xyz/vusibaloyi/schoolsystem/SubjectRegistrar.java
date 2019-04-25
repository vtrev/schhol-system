package xyz.vusibaloyi.schoolsystem;

import java.util.ArrayList;

public class SubjectRegistrar {

    public static void registerSubjects(Student student, String subjectsToRegister) {

        String[] subjectList = subjectsToRegister.split(",");

        //register student for the given  subjects

        int count = 0;
        for (String subject : subjectList) {
            try{
                student.registerSubject(Subject.valueOf(subject));
            }catch (IllegalArgumentException | NullPointerException e){
                System.out.println("Error Subject"+subject+"is not valid");
            }
        }
    }
}
