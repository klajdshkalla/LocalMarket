package com.sda;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Arrays;
import java.util.List;

public class ProductService {

    public void initializeProducts() {
        List<Products> initialProducts = Arrays.asList(
                new Products("Qumesht", 50, 1.80),
                new Products("Buke", 100, 0.50),
                new Products("Veze", 200, 0.15),
                new Products("Djath", 30, 5.50),
                new Products("Sallam", 40, 3.20),
                new Products("Uje", 150, 0.70),
                new Products("Coca-Cola", 80, 1.20),
                new Products("Biskota", 60, 1.50),
                new Products("Sheqer", 45, 1.30),
                new Products("Vaj", 35, 2.40)
        );

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                // Check if products already exist
                Long productCount = session.createQuery("select count(*) from Products", Long.class)
                        .getSingleResult();

                if (productCount == 0) {
                    for (Products product : initialProducts) {
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

    public void resetDatabase() {
        clearAllData();
        initializeProducts();
    }
}