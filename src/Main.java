import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Produkte> produkte = new ArrayList<>();
        produkte.add(new Produkte("Qumesht", 10, 300.0));
        produkte.add(new Produkte("Kos", 11, 370.0));
        produkte.add(new Produkte("Uje", 20, 500.0));
        produkte.add(new Produkte("Kafe", 6, 600.0));
        produkte.add(new Produkte("Miell", 22, 330.0));
        produkte.add(new Produkte("Vaj", 13, 380.0));
        produkte.add(new Produkte("Makarona", 15, 400.0));
        produkte.add(new Produkte("Oriz", 7, 500.0));
        produkte.add(new Produkte("Sallam", 8, 311.0));
        produkte.add(new Produkte("Mish", 9, 200.0));

        System.out.println("Produktet ne Dyqanin tone" + produkte);
    }
}