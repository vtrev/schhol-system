package xyz.vusibaloyi.schoolsystem;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateSession {
    
    private Configuration config = new Configuration().configure("hibernate.cfg.xml");
    private ServiceRegistry serviceReg = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
    private SessionFactory sessionFactory = config.buildSessionFactory(serviceReg);
    private Session session;
    
    public HibernateSession(){
        createSession();
    }
    
    private void createSession() {
        try {
            session = sessionFactory.openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //getter
    public Session getSession(){
        return session;
    }
}
