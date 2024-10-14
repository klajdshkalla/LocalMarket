package com.sda;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class ProductService {

    public List<Products> getAllProduct() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Products> products;
        try {
            tx = session.beginTransaction();
            products = session.createQuery("FROM products").list();
            for (Iterator iterator = products.iterator(); iterator.hasNext(); ) {
                Products product = (Products) iterator.next();
                System.out.print("Tipi: " + product.getTipi());
                System.out.print("  Sasia: " + product.getSasia());
                System.out.println("  Cmimi: " + product.getCmimi());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return List.of();
    }

    public Products findById(Long id) {
        return null;
    }


}
