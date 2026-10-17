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

    private static final String GROUP_NAME = "SE2102-Group03";
    private static final String FILE_PATH = "./src/data/comic.txt";
    private ComicBookManagement cm;

    private final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        new Main().run();
    }

    private void run() {
        try {
            cm = new ComicBookManagement(FILE_PATH);
            cm.loadComicBook();
        } catch (Exception e) {
            System.out.println("Error: Cannot Load File! " + e.getMessage());
        }
        int choice;
        do {
            showMenu();
            choice = readChoice();

            switch (choice) {
                case 1:
                    cm.addComicBook(sc);
                    break;
                case 2:
                    cm.searchByTitle(sc);
                    break;
                case 3:
                    cm.findBookByAuthor(sc);
                    break;
                case 4:
                    cm.updateComicBook(sc);
                    break;
                case 5:
                    cm.deleteComicBook(sc);
                    break;
                case 6:
                    showAboutUs();
                    break;
                case 7:
                    try {
                        cm.saveComicBook();
                    } catch (IOException e) {
                        System.out.println("Cannot save data to " + FILE_PATH + ".");
                    }
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

    public void showAboutUs() {
        System.out.println("1. FPT University campus Can Tho");
        System.out.println("2. Information technology college - Software engineering department");
        System.out.println("3. CEA201 - Computer Organization and Architecture");
        System.out.println("4. Class SE2102");
        System.out.println("5. Mentor Vo Hong Khanh");
        System.out.println("6. Group 03");
        System.out.println("7. Lý Hoàng Mến - CE210682 - Leader\n"
                + "   Đặng Thái Hưng - CE210124 - Member\n"
                + "   Nguyễn Vũ Phước Vinh - CE210586 - Member\n"
                + "   Đinh Minh Khôi - CE210346 - Member");
    }
}
