/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicbook;

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
        manager.loadFromFile(FILE_NAME);
 
        int choice;
        do {
            showMenu();
            choice = readChoice();
 
            switch (choice) {
                case 1:
                    manager.addComicBook(sc);
                    break;
                case 2:
                    manager.searchByTitle(sc);
                    break;
                case 3:
                    manager.searchByAuthor(sc);
                    break;
                case 4:
                    manager.updateRentalPrice(sc);
                    break;
                case 5:
                    manager.deleteComicBook(sc);
                    break;
                case 6:
                    manager.showAboutUs();
                    break;
                case 7:
                    manager.saveToFile(FILE_NAME);
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
}
