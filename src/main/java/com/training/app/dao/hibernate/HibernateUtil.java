package com.training.app.dao.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Create by Roman Hayda on 10.04.2017.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }

}
