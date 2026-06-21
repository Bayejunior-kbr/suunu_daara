package sn.l2gl.suunu.daara.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import sn.l2gl.suunu.daara.model.models.Talibes;
import sn.l2gl.suunu.daara.model.models.Classe;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            sessionFactory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Talibes.class) //"utilise cette classe comme table"
                    .addAnnotatedClass(Classe.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }
}

