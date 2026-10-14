/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicbook;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final String GROUP_NAME = "SE2102-GroupXX"; // change XX to your group number
    private static final String FILE_NAME = "ComicBooks.txt";

    private final Scanner sc = new Scanner(System.in);
    private final ArrayList<ComicBook> list = new ArrayList<>();

    public static void main(String[] args) {
        new Main().run();
    }

    // ===================== MENU =====================

    private void run() {
        loadFromFile(); // load data as soon as the program starts

        int choice;
        do {
            showMenu();
            choice = readChoice();

            switch (choice) {
                case 1:
                    addComicBook();
                    break;
                case 2:
                    searchByTitle();
                    break;
                case 3:
                    searchByAuthor();
                    break;
                case 4:
                    updateRentalPrice();
                    break;
                case 5:
                    deleteComicBook();
                    break;
                case 6:
                    showAboutUs();
                    break;
                case 7:
                    saveToFile(); // auto-save when quitting
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 7.");
            }
            System.out.println();
        } while (choice != 7);
    }

    private void showMenu() {
        System.out.println("          COMIC BOOK RENTAL SHOP # " + GROUP_NAME);
        System.out.println("1. Add new comic book.");
        System.out.println("2. Search book by title.");
        System.out.println("3. Search book of an author.");
        System.out.println("4. Update book rental price.");
        System.out.println("5. Delete comic book.");
        System.out.println("6. About us.");
        System.out.println("7. Quit.");
        System.out.print("     Please select a function: ");
    }

    // Returns the number entered, or -1 if the input is not a valid integer
    private int readChoice() {
        String line = sc.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // ===================== FILE (hidden functions) =====================

    private void loadFromFile() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            System.out.println("File " + FILE_NAME + " not found. Starting with an empty list.");
            return;
        }
        try (Scanner fs = new Scanner(f)) {
            int n = Integer.parseInt(fs.nextLine().trim());
            for (int i = 0; i < n; i++) {
                int id = Integer.parseInt(fs.nextLine().trim());
                String title = fs.nextLine().trim();
                double price = Double.parseDouble(fs.nextLine().trim());
                String author = fs.nextLine().trim();
                int volume = Integer.parseInt(fs.nextLine().trim());
                list.add(new ComicBook(id, title, price, author, volume));
            }
        } catch (Exception e) {
            System.out.println("Error while reading " + FILE_NAME + ". Some data may not be loaded.");
        }
    }

    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(FILE_NAME)) {
            pw.println(list.size());
            for (ComicBook b : list) {
                pw.println(b.getId());
                pw.println(b.getTitle());
                pw.println(String.format(Locale.US, "%.2f", b.getBookRentalPrice()));
                pw.println(b.getAuthor());
                pw.println(b.getVolume());
            }
        } catch (IOException e) {
            System.out.println("Cannot save data to " + FILE_NAME + ".");
        }
    }

    // ===================== FUNCTIONS 1-6 (write the bodies here) =====================

    private void addComicBook() {
        // TODO
    }

    private void searchByTitle() {
        // TODO
    }

    private void searchByAuthor() {
        // TODO
    }

    private void updateRentalPrice() {
        // TODO
    }

    private void deleteComicBook() {
        // TODO
    }

    private void showAboutUs() {
        // TODO
    }
}

/**
 *
 * @author Suw
 */
public class Main {
    private static final String GROUP_NAME = "SE2102-Group03";
    private static final String FILE_NAME = "ComicBooks.txt";
 
    private final Scanner sc = new Scanner(System.in);
    private final ComicBookManagement manager = new ComicBookManagement();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
           new Main().run();
    }
 
    private void run() {
        loadFromFile();
 
        int choice;
        do {
            showMenu();
            choice = readChoice();
 
            switch (choice) {
                case 1:
                    addComicBook();
                    break;
                case 2:
                    searchByTitle();
                    break;
                case 3:
                    searchByAuthor();
                    break;
                case 4:
                    updateRentalPrice();
                    break;
                case 5:
                    deleteComicBook();
                    break;
                case 6:
                    showAboutUs();
                    break;
                case 7:
                    saveToFile();
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 7.");
            }
            System.out.println();
        } while (choice != 7);
    }
 
    private void showMenu() {
        System.out.println("          COMIC BOOK RENTAL SHOP # " + GROUP_NAME);
        System.out.println("1. Add new comic book.");
        System.out.println("2. Search book by title.");
        System.out.println("3. Search book of an author.");
        System.out.println("4. Update book rental price.");
        System.out.println("5. Delete comic book.");
        System.out.println("6. About us.");
        System.out.println("7. Quit.");
        System.out.print("     Please select a function: ");
    }
 
    private int readChoice() {
        String line = sc.nextLine().trim();
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
 
    private void loadFromFile() {
        File f = new File(FILE_NAME);
        if (!f.exists()) {
            System.out.println("File " + FILE_NAME + " not found. Starting with an empty list.");
            return;
        }
        try (Scanner fs = new Scanner(f)) {
            int n = Integer.parseInt(fs.nextLine().trim());
            for (int i = 0; i < n; i++) {
                int id = Integer.parseInt(fs.nextLine().trim());
                String title = fs.nextLine().trim();
                double price = Double.parseDouble(fs.nextLine().trim());
                String author = fs.nextLine().trim();
                int volume = Integer.parseInt(fs.nextLine().trim());
                list.add(new ComicBook(id, title, price, author, volume));
            }
        } catch (Exception e) {
            System.out.println("Error while reading " + FILE_NAME + ". Some data may not be loaded.");
        }
    }
 
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(FILE_NAME)) {
            pw.println(list.size());
            for (ComicBook b : list) {
                pw.println(b.getId());
                pw.println(b.getTitle());
                pw.println(String.format(Locale.US, "%.2f", b.getBookRentalPrice()));
                pw.println(b.getAuthor());
                pw.println(b.getVolume());
            }
        } catch (IOException e) {
            System.out.println("Cannot save data to " + FILE_NAME + ".");
        }
    }

}
