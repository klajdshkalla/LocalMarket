package com.sda;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class ProductService {

    public List<Products> getAllProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        List<Products> products = null;

        try {
            tx = session.beginTransaction();
            products = session.createQuery("FROM Products", Products.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

        return products != null ? products : List.of();
    }

    public Products findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Products product = null;

        try {
            product = session.get(Products.class, id);
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return product;
    }

    public void updateProduct(Products product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.merge(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void saveProduct(Products product) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.persist(product);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}