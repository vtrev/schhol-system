package xyz.vusibaloyi.schoolsystem.person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class StudentService {
    
    private Configuration config = new Configuration().configure("hibernate.cfg.xml");
    private ServiceRegistry serviceReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    private SessionFactory sessionFactory = config.buildSessionFactory(serviceReg);
    private Session session = sessionFactory.openSession();
    private Transaction tx = session.beginTransaction();
        
    public String saveStudent(Student student){
        try {
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
}
