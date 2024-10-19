package com.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class LocalMarketMain {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Scanner scanner = new Scanner(System.in);
        List<Products> selectedProducts = new ArrayList<>();
        double totalCmimi = 0;

        while (true) {
            Query<Products> query = session.createQuery("FROM Products", Products.class);
            List<Products> products = query.getResultList();

            System.out.println("Produktet:");
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ": " + products.get(i));
            }
            System.out.println("Zgjidhni numrin e produktit (ose 'exit' per te printuar faturen):");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                int choiceIndex = Integer.parseInt(choice) - 1;
                if (choiceIndex >= 0 && choiceIndex < products.size()) {
                    Products selectedProduct = products.get(choiceIndex);
                    System.out.println("Vendosni sasine:");
                    int sasia = Integer.parseInt(scanner.nextLine());

                    if (sasia > 0 && sasia <= selectedProduct.getSasia()) {
                        Products boughtProduct = new Products(selectedProduct.getTipi(), sasia, selectedProduct.getCmimi());
                        selectedProducts.add(boughtProduct);
                        totalCmimi += boughtProduct.getTotalCmimi();

                        // Update product quantity in database
                        session.beginTransaction();
                        selectedProduct.setSasia(selectedProduct.getSasia() - sasia);
                        session.update(selectedProduct);
                        session.getTransaction().commit();
                    } else {
                        System.out.println("Sasia e pavlefshme ose e pamjaftueshme.");
                    }
                } else {
                    System.out.println("Zgjedhje e pavlefshme.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ju lutem vendosni nje numer te vlefshem ose 'exit'.");
            }
        }

        System.out.println("Vendosni emrin e bleresit:");
        String emri = scanner.nextLine();
        System.out.println("Vendosni mbiemrin e bleresit:");
        String mbiemri = scanner.nextLine();

        Buyer buyer = new Buyer(emri, mbiemri);
        Bill bill = new Bill(totalCmimi, LocalDate.now(), buyer);

        session.beginTransaction();
        session.save(buyer);
        session.save(bill);
        session.getTransaction().commit();

        System.out.println("\nFatura:");
        System.out.println("Bleresi: " + buyer.getEmri() + " " + buyer.getMbiemri());
        System.out.println("Data: " + bill.getData());
        System.out.println("Produktet e blera:");
        for (Products p : selectedProducts) {
            System.out.println(p.getTipi() + " - Sasia: " + p.getSasia() + " - Cmimi: " + p.getTotalCmimi());
        }
        System.out.println("Totali: " + bill.getCmimi());

        session.close();
        HibernateUtil.shutdown();
        scanner.close();
    }
}