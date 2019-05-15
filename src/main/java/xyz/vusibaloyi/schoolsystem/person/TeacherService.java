package xyz.vusibaloyi.schoolsystem.person;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class TeacherService {
    private Configuration config = new Configuration().configure("hibernate.cfg.xml");
    private ServiceRegistry serviceReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    private SessionFactory sessionFactory = config.buildSessionFactory(serviceReg);
    private Session session = sessionFactory.openSession();
    private Transaction tx = session.beginTransaction();
    
    public String saveTeacher(Teacher teacher){
        try {
            session.save(teacher);
            tx.commit();
            return "teacher saved";
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
            return "error saving teacher";
        }
        finally {
            session.close();
        }
    }
}
