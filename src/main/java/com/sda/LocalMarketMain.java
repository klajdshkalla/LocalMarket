package com.sda;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LocalMarketMain {

    public static void main(String[] args) {

        ProductService.initializeProducts();

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
             Session session = sessionFactory.openSession();
             Scanner scanner = new Scanner(System.in)) {

            List<Product> selectedProducts = new ArrayList<>();
            double totalCmimi = 0;

            while (true) {
                List<Product> products = getProductsFromDB(session);

                displayProducts(products);

                System.out.println("Zgjidhni numrin e produktit (ose 'exit' per te printuar faturen):");
                String choice = scanner.nextLine();

                if ("exit".equalsIgnoreCase(choice)) {
                    break;
                }

                totalCmimi += handleProductSelection(session, scanner, products, selectedProducts);
            }

            Buyer buyer = getBuyerDetails(scanner);
            Bill bill = generateBill(session, totalCmimi, buyer);

            printBill(buyer, bill, selectedProducts);
        }
        HibernateUtil.shutdown();
    }

    private static List<Product> getProductsFromDB(Session session) {
        Query<Product> query = session.createQuery("FROM Product", Product.class);
        return query.getResultList();
    }

    private static void displayProducts(List<Product> products) {
        System.out.println("Produktet:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println((i + 1) + ": " + products.get(i));
        }
    }

    private static double handleProductSelection(Session session, Scanner scanner, List<Product> products, List<Product> selectedProducts) {
        try {
            int choiceIndex = Integer.parseInt(scanner.nextLine()) - 1;

            if (choiceIndex >= 0 && choiceIndex < products.size()) {
                Product selectedProduct = products.get(choiceIndex);
                System.out.println("Vendosni sasine:");
                int sasia = Integer.parseInt(scanner.nextLine());

                if (sasia > 0 && sasia <= selectedProduct.getSasia()) {
                    Product boughtProduct = new Product(selectedProduct.getTipi(), sasia, selectedProduct.getCmimi());
                    selectedProducts.add(boughtProduct);

                    updateProductStock(session, selectedProduct, sasia);
                    return boughtProduct.getTotalCmimi();
                } else {
                    System.out.println("Sasia e pavlefshme ose e pamjaftueshme.");
                }
            } else {
                System.out.println("Zgjedhje e pavlefshme.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Ju lutem vendosni nje numer te vlefshem ose 'exit'.");
        }
        return 0;
    }

    private static void updateProductStock(Session session, Product selectedProduct, int sasia) {
        session.beginTransaction();
        selectedProduct.setSasia(selectedProduct.getSasia() - sasia);
        session.update(selectedProduct);
        session.getTransaction().commit();
    }

    private static Buyer getBuyerDetails(Scanner scanner) {
        System.out.println("Vendosni emrin e bleresit:");
        String emri = scanner.nextLine();
        System.out.println("Vendosni mbiemrin e bleresit:");
        String mbiemri = scanner.nextLine();

        return new Buyer(emri, mbiemri);
    }

    private static Bill generateBill(Session session, double totalCmimi, Buyer buyer) {
        Bill bill = new Bill(totalCmimi, LocalDate.now(), buyer);

        session.beginTransaction();
        session.save(buyer);
        session.save(bill);
        session.getTransaction().commit();

        return bill;
    }

    private static void printBill(Buyer buyer, Bill bill, List<Product> selectedProducts) {
        System.out.println("\nFatura:");
        System.out.println("Bleresi: " + buyer.getEmri() + " " + buyer.getMbiemri());
        System.out.println("Data: " + bill.getData());
        System.out.println("Produktet e blera:");

        for (Product p : selectedProducts) {
            System.out.println(p.getTipi() + " - Sasia: " + p.getSasia() + " - Cmimi: " + p.getTotalCmimi());
        }

        System.out.println("Totali: " + bill.getCmimi());
    }
}
