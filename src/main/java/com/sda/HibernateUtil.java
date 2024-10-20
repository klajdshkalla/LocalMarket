package com.sda;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            Properties hibernateProperties = new Properties();

            try (FileInputStream fis = new FileInputStream("src/main/resources/hibernate.properties")) {
                hibernateProperties.load(fis);
            } catch (IOException e) {
                throw new ExceptionInInitializerError(e);
            }

            configuration.setProperties(hibernateProperties);
            configuration.addAnnotatedClass(Products.class);
            configuration.addAnnotatedClass(Buyer.class);
            configuration.addAnnotatedClass(Bill.class);

            return configuration.buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}