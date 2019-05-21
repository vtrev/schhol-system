package xyz.vusibaloyi.schoolsystem.person;

import org.hibernate.Session;
import org.hibernate.Transaction;
import xyz.vusibaloyi.schoolsystem.HibernateSession;


public class StudentService {
    
    private Session session = new HibernateSession().getSession();
    private Transaction tx;
        
    public String saveStudent(Student student){
        try {
            tx = session.beginTransaction();
            session.save(student);
            tx.commit();
            return "student saved";
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
            return "error saving student";
        }
        finally {
            session.close();
        }
    }
    
    public Student getStudentById(int student_id){
        Student tmpStudent = null;
        try {
            tx = session.beginTransaction();
            tmpStudent = (Student) session.get(Student.class,student_id);
            tx.commit();
            
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }
        finally {
            session.close();
        }
        return tmpStudent;
    }
}

