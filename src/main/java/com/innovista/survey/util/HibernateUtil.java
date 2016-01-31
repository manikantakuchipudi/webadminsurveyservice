package com.innovista.survey.util;

/**
 * Created by mkuchipudi on 29-09-2015.
 */
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration= new Configuration();
            sessionFactory = configuration.configure("hibernate.cfg.xml")
                    .buildSessionFactory();



        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}