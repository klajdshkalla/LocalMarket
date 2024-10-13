import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        ArrayList<Produkte> produkte = new ArrayList<>();
        produkte.add(new Produkte("Qumesht", 10, 350.50));
        produkte.add(new Produkte("Kos", 11, 100.10));
        produkte.add(new Produkte("Uje", 20, 550.40));
        produkte.add(new Produkte("Kafe", 6, 600.50));
        produkte.add(new Produkte("Miell", 22, 330.40));
        produkte.add(new Produkte("Vaj", 13, 380.10));
        produkte.add(new Produkte("Makarona", 15, 400.10));
        produkte.add(new Produkte("Oriz", 7, 500.50));
        produkte.add(new Produkte("Sallam", 8, 311.60));
        produkte.add(new Produkte("Mish", 9, 12999.10));

        Scanner scanner = new Scanner(System.in);
        ArrayList<Produkte> selectedProducts = new ArrayList<>();
        double totalCmimi = 0;

        while (true) {
            System.out.println("Produktet:");
            for (int i = 0; i < produkte.size(); i++) {
                System.out.println(i + ": " + produkte.get(i));
            }
            System.out.println("Zgjidhni numrin e produktit (ose 'exit' per te printuar faturen):");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                int choiceIndex = Integer.parseInt(choice);
                if (choiceIndex >= 0 && choiceIndex < produkte.size()) {
                    Produkte selectedProduct = produkte.get(choiceIndex);
                    System.out.println("Vendosni sasine:");
                    int sasia = Integer.parseInt(scanner.nextLine());

                    if (sasia > 0 && sasia <= selectedProduct.getSasia()) {
                        Produkte boughtProduct = new Produkte(selectedProduct.getTipi(), sasia, selectedProduct.getCmimi());
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

        Bleresi bleresi = new Bleresi(emri, mbiemri);
        Fatura fatura = new Fatura(totalCmimi, LocalDate.now(), bleresi);

        System.out.println("\nFatura:");
        System.out.println("Bleresi: " + bleresi.getEmri() + " " + bleresi.getMbiemri());
        System.out.println("Data: " + fatura.getData());
        System.out.println("Produktet e blera:");
        for (Produkte p : selectedProducts) {
            System.out.println(p.getTipi() + " - Sasia: " + p.getSasia() + " - Cmimi: " + p.getTotalCmimi());
        }
        System.out.println("Totali: " + fatura.getCmimi());

        scanner.close();
    }
}