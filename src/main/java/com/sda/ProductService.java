package com.sda;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Arrays;
import java.util.List;

public class ProductService {

    public static void initializeProducts() {
        List<Product> initialProducts = Arrays.asList(
                new Product("Qumesht", 50, 1.80),
                new Product("Buke", 100, 0.50),
                new Product("Veze", 200, 0.15),
                new Product("Djath", 30, 5.50),
                new Product("Sallam", 40, 3.20),
                new Product("Uje", 150, 0.70),
                new Product("Coca-Cola", 80, 1.20),
                new Product("Biskota", 60, 1.50),
                new Product("Sheqer", 45, 1.30),
                new Product("Vaj", 35, 2.40)
        );

        /*try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // Check if products already exist
                Long productCount = session.createQuery("select count(*) from Products", Long.class)
                        .getSingleResult();

                if (productCount == 0) {
                    for (Product product : initialProducts) {
                        session.persist(product);
                    }
                    System.out.println("Successfully initialized products database!");
                } else {
                    System.out.println("Products already exist in database. Skipping initialization.");
                }

                tx.commit();
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        } catch (Exception e) {
            System.err.println("Error initializing products: " + e.getMessage());
            e.printStackTrace();
        }*/

        for (Product product : initialProducts) {
            saveProduct(product);
        }
    }

    public void clearAllData() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // Delete in correct order to respect foreign key constraints
                session.createQuery("delete from Bill").executeUpdate();
                session.createQuery("delete from Buyer").executeUpdate();
                session.createQuery("delete from Products").executeUpdate();

                tx.commit();
                System.out.println("Successfully cleared all data from database!");
            } catch (Exception e) {
                tx.rollback();
                throw e;
            }
        } catch (Exception e) {
            System.err.println("Error clearing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveProduct(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void resetDatabase() {
        clearAllData();
    }
}