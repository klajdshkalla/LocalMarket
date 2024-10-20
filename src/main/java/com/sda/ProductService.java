package com.sda;

import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.Arrays;
import java.util.List;

public class ProductService {

    public static void initializeProducts() {
        clearAllData();

        List<Product> initialProducts = Arrays.asList(
                new Product("Qumesht", 50, 100.99),
                new Product("Buke", 100, 125.55),
                new Product("Veze", 200, 55.95),
                new Product("Djath", 30, 85.99),
                new Product("Sallam", 40, 355.99),
                new Product("Uje", 150, 30.99),
                new Product("Coca-Cola", 80, 155.25),
                new Product("Biskota", 60, 1700.55),
                new Product("Sheqer", 45, 160.75),
                new Product("Vaj", 35, 250.55)
        );

        for (Product product : initialProducts) {
            saveProduct(product);
        }
    }

    public static void clearAllData() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            try {
                session.createQuery("delete from Bill").executeUpdate();
                session.createQuery("delete from Buyer").executeUpdate();
                session.createQuery("delete from Product").executeUpdate();
                tx.commit();
                System.out.println("Successfully cleared all data from the database!");
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
