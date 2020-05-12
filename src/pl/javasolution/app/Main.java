package pl.javasolution.app;

import pl.javasolution.implementation.Pociag;
import pl.javasolution.implementation.PociagiLista;
import pl.javasolution.implementation.Wagon;
import pl.javasolution.implementation.WagonyLista;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int z = 0; // Liczba zestawow danych
        try {
            z = Integer.parseInt(scanner.nextLine()); // Przekonwertuj input uzytkownika do Integer
        } catch (NumberFormatException e) { e.printStackTrace(); } // Wyrzuc wyjatek, jesli podano inny typ danych niz int

        for (int i = 0; i < z; i++) {
            PociagiLista pociagiList = new PociagiLista(); // Utworz liste pociagow dla danego zestawu

            int n = 0;
            try {
                n = Integer.parseInt(scanner.nextLine()); // Przekonwertuj input uzytkownika do Integer
            } catch (NumberFormatException e) { e.printStackTrace(); } // Wyrzuc wyjatek

            for (int j = 0; j < n; j++) {
                String line = scanner.nextLine(); // Definicja skanera do odczytu danych
                String[] words = line.split("\\W+"); // Podziel input na pojedyncze slowa i umiesc w tablicy

                // Instrukcja wyboru
                /* Komendy jak w zadaniu */
                switch (words[0]) {
                    case "New" -> {
                        Wagon newWagon = new Wagon(words[2]);
                        WagonyLista wagonyList = new WagonyLista();
                        wagonyList.addLast(newWagon);

                        Pociag newPociag = new Pociag(wagonyList, words[1]);
                        pociagiList.addLast(newPociag);
                    }
                    case "InsertFirst" -> {
                        Wagon newWagon = new Wagon(words[2]);
                        Pociag definedPociag = pociagiList.findPociag(words[1]);
                        try {
                            definedPociag.getWagonyList().addFirst(newWagon);
                        } catch (NullPointerException e) {
                            System.out.println("Train name " + words[1] + " does not exist");
                        }
                    }
                    case "InsertLast" -> {
                        Wagon newWagon = new Wagon(words[2]);
                        Pociag definedPociag = pociagiList.findPociag(words[1]);
                        try {
                            definedPociag.getWagonyList().addLast(newWagon);
                        } catch (NullPointerException e) {
                            System.out.println("Train name " + words[1] + " does not exist");
                        }

                    }
                    case "Display" -> {
                        pociagiList.displayPociag(words[1]);
                    }
                    case "Trains" -> {
                        pociagiList.displayList();
                    }
                    case "Reverse" -> {
                        Pociag definedPociag = pociagiList.findPociag(words[1]);
                        try {
                            definedPociag.getWagonyList().reverse();
                        } catch (NullPointerException e) {
                            System.out.println("Train " + words[1] + " does not exist");
                        }
                    }
                    case "Union" -> {
                        pociagiList.union(words[1], words[2]);
                    }
                    case "DelFirst" -> {
                        pociagiList.delFirstWagon(words[1], words[2]);
                    }
                    case "DelLast" -> {
                        pociagiList.delLastWagon(words[1], words[2]);
                    }
                }
            }
        }
    }
}
