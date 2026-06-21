package sn.l2gl.suunu.daara.util;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass() //"utilise cette classe comme table"
                    .addAnnotatedClass()
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}

