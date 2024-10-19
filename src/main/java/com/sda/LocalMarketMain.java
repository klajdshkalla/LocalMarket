package com.sda;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

public class LocalMarketMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("your-persistence-unit-name");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Scanner scanner = new Scanner(System.in);
        List<Products> selectedProducts = new ArrayList<>();
        double totalCmimi = 0;

        while (true) {

            TypedQuery<Products> query = entityManager.createQuery("FROM Produkte", Products.class);
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
                int choiceIndex = Integer.parseInt(choice) - 1; // Adjust for zero-based index
                if (choiceIndex >= 0 && choiceIndex < products.size()) {
                    Products selectedProduct = products.get(choiceIndex);
                    System.out.println("Vendosni sasine:");
                    int sasia = Integer.parseInt(scanner.nextLine());

                    if (sasia > 0 && sasia <= selectedProduct.getSasia()) {
                        Products boughtProduct = new Products(selectedProduct.getTipi(), sasia, selectedProduct.getCmimi());
                        selectedProducts.add(boughtProduct);
                        totalCmimi += boughtProduct.getTotalCmimi();
                        selectedProduct.setSasia(selectedProduct.getSasia() - sasia);
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

        System.out.println("\nFatura:");
        System.out.println("Bleresi: " + buyer.getEmri() + " " + buyer.getMbiemri());
        System.out.println("Data: " + bill.getData());
        System.out.println("Produktet e blera:");
        for (Products p : selectedProducts) {
            System.out.println(p.getTipi() + " - Sasia: " + p.getSasia() + " - Cmimi: " + p.getTotalCmimi());
        }
        System.out.println("Totali: " + bill.getCmimi());

        entityManager.close();
        entityManagerFactory.close();
        scanner.close();
    }
}