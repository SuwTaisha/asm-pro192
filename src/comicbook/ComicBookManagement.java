package comicbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author Suw
 */
public class ComicBookManagement {

    private String DATA_FILE;
    private ArrayList<ComicBook> comicBooks;
    private int quantity;

    public ComicBookManagement(String DATA_FILE) throws Exception {
        if (DATA_FILE.equals("")) {
            throw new Exception("The url of comic books data file can't be empty!");
        } else {
            this.DATA_FILE = DATA_FILE;
            this.comicBooks = new ArrayList<>();
            this.quantity = 0;
        }
    }

    public void loadComicBook()
            throws IOException, Exception {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("The data file " + DATA_FILE + " does not exist. Creating new data file... Done!");
            this.quantity = 0;
        } else {
            BufferedReader br = new BufferedReader(new FileReader(DATA_FILE));
            try {
                this.comicBooks.clear();

                String firstLine = br.readLine();
                if (firstLine == null || firstLine.trim().isEmpty()) {
                    this.quantity = 0;
                    return;
                }
                int n = Integer.parseInt(firstLine.trim());

                for (int i = 0; i < n; i++) {
                    int id = Integer.parseInt(readNextLine(br).trim());
                    String title = readNextLine(br).trim();
                    double bookRentalPrice = Double.parseDouble(readNextLine(br).trim());
                    String author = readNextLine(br).trim();
                    int volume = Integer.parseInt(readNextLine(br).trim());

                    this.comicBooks.add(new ComicBook(id, title, bookRentalPrice, author, volume));
                }
                this.quantity = this.comicBooks.size();
                System.out.println("Loaded " + this.quantity + " comic book(s) from " + DATA_FILE + ".");
            } catch (NumberFormatException e) {
                throw new Exception("The data file has an invalid number format: " + e.getMessage());
            } finally {
                br.close();
            }
        }
    }

    private String readNextLine(BufferedReader br) throws IOException, Exception {
        String line = br.readLine();
        if (line == null) {
            throw new Exception("The data file is incomplete: fewer comic books than the number declared on line 1.");
        }
        return line;
    }

    public void addComicBook(Scanner sc) {
        try {
            int id = 1;
            for (ComicBook cb : comicBooks) {
                if (cb.getId() >= id) {
                    id = cb.getId() + 1;
                }
            }

            System.out.print("Enter title: ");
            String title = sc.nextLine().trim();
            System.out.print("Enter book rental price: ");
            double price = Double.parseDouble(sc.nextLine().trim());
            System.out.print("Enter author: ");
            String author = sc.nextLine().trim();
            System.out.print("Enter volume: ");
            int volume = Integer.parseInt(sc.nextLine().trim());

            if (title.isEmpty() || author.isEmpty() || price <= 0 || volume <= 0) {
                System.out.println("Invalid data. Comic book was not added.");
                return;
            }

            comicBooks.add(new ComicBook(id, title, price, author, volume));
            quantity = comicBooks.size();
            System.out.println("Added successfully! ID = " + id);
        } catch (NumberFormatException e) {
            System.out.println("Price and volume must be numbers. Comic book was not added.");
        }
    }

    public void updateComicBook(Scanner sc) {
        if (comicBooks.isEmpty()) {
            System.out.println("The comic book list is empty. Nothing to update.");
            return;
        }

        displayAll();

        ComicBook book = findBookById(sc);

        System.out.println("Press Enter to keep the current value.");
        String title = readTitle(sc, book.getTitle());
        double price = readPrice(sc, book.getBookRentalPrice());
        String author = readAuthor(sc, book.getAuthor());
        int volume = readVolume(sc, book.getVolume());

        if (isDuplicate(book, title, author, volume)) {
            System.out.println("Error: another comic book with the same title, author and volume already exists. Update cancelled.");
            return;
        }

        book.setTitle(title);
        book.setBookRentalPrice(price);
        book.setAuthor(author);
        book.setVolume(volume);

        System.out.println("Update successful.");
        displayAll();
    }

    private ComicBook findBookById(Scanner sc) {
        while (true) {
            System.out.print("Enter the ID of the comic book to update: ");
            String input = sc.nextLine().trim();
            try {
                int id = Integer.parseInt(input);
                for (ComicBook cb : comicBooks) {
                    if (cb.getId() == id) {
                        return cb;
                    }
                }
                System.out.println("Error: comic book with ID " + id + " was not found. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Error in ID: it must be a whole number (digits only).");
            }
        }
    }

    private String readTitle(Scanner sc, String current) {
        while (true) {
            System.out.print("New title [" + current + "]: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return current;
            }
            try {
                if (!input.matches("[\\p{L}0-9 :!&'.,?-]+")) {
                    throw new IllegalArgumentException(
                            "Title contains invalid characters. Allowed: letters, digits, spaces and : ! & ' . , ? -");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error in TITLE: " + e.getMessage());
            }
        }
    }

    private double readPrice(Scanner sc, double current) {
        while (true) {
            System.out.print("New rental price [" + String.format(java.util.Locale.US, "%.2f", current) + "]: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return current;
            }
            try {
                if (!input.matches("\\d+(\\.\\d{1,2})?")) {
                    throw new IllegalArgumentException(
                            "Rental price must be a number with at most 2 decimals, like 2.5 or 2.50 (digits and one dot only).");
                }
                double price = Double.parseDouble(input);
                if (price <= 0) {
                    throw new IllegalArgumentException("Rental price must be greater than 0.");
                }
                return price;
            } catch (IllegalArgumentException e) {
                System.out.println("Error in RENTAL PRICE: " + e.getMessage());
            }
        }
    }

    private String readAuthor(Scanner sc, String current) {
        while (true) {
            System.out.print("New author [" + current + "]: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return current;
            }
            try {
                if (!input.matches("[\\p{L} .'-]+")) {
                    throw new IllegalArgumentException(
                            "Author name contains invalid characters. Allowed: letters, spaces and . ' -");
                }
                return input;
            } catch (IllegalArgumentException e) {
                System.out.println("Error in AUTHOR: " + e.getMessage());
            }
        }
    }

    private int readVolume(Scanner sc, int current) {
        while (true) {
            System.out.print("New volume [" + current + "]: ");
            String input = sc.nextLine().trim();
            if (input.isEmpty()) {
                return current;
            }
            try {
                int volume = Integer.parseInt(input);
                if (volume <= 0) {
                    throw new IllegalArgumentException("Volume must be greater than 0.");
                }
                return volume;
            } catch (NumberFormatException e) {
                System.out.println("Error in VOLUME: it must be a whole number (digits only, not too large).");
            } catch (IllegalArgumentException e) {
                System.out.println("Error in VOLUME: " + e.getMessage());
            }
        }
    }

    private boolean isDuplicate(ComicBook self, String title, String author, int volume) {
        for (ComicBook cb : comicBooks) {
            if (cb != self
                    && cb.getTitle().equalsIgnoreCase(title)
                    && cb.getAuthor().equalsIgnoreCase(author)
                    && cb.getVolume() == volume) {
                return true;
            }
        }
        return false;
    }

    private void displayAll() {
        if (comicBooks.isEmpty()) {
            System.out.println("The comic book list is empty.");
            return;
        }

        int idW = 2, titleW = 5, priceW = 5, authorW = 6, volW = 6; // widths of the header names
        for (ComicBook cb : comicBooks) {
            idW = Math.max(idW, String.valueOf(cb.getId()).length());
            titleW = Math.max(titleW, cb.getTitle().length());
            priceW = Math.max(priceW, String.format(java.util.Locale.US, "%.2f", cb.getBookRentalPrice()).length());
            authorW = Math.max(authorW, cb.getAuthor().length());
            volW = Math.max(volW, String.valueOf(cb.getVolume()).length());
        }

        String line = "+" + dashes(idW + 2) + "+" + dashes(titleW + 2) + "+" + dashes(priceW + 2)
                + "+" + dashes(authorW + 2) + "+" + dashes(volW + 2) + "+";
        String headerFormat = "| %-" + idW + "s | %-" + titleW + "s | %-" + priceW + "s | %-" + authorW + "s | %-" + volW + "s |%n";
        String rowFormat = "| %" + idW + "d | %-" + titleW + "s | %" + priceW + "s | %-" + authorW + "s | %" + volW + "d |%n";

        System.out.println(line);
        System.out.printf(headerFormat, "ID", "Title", "Price", "Author", "Volume");
        System.out.println(line);
        for (ComicBook cb : comicBooks) {
            System.out.printf(rowFormat, cb.getId(), cb.getTitle(),
                    String.format(java.util.Locale.US, "%.2f", cb.getBookRentalPrice()),
                    cb.getAuthor(), cb.getVolume());
        }
        System.out.println(line);
    }

    private String dashes(int n) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append('-');
        }
        return sb.toString();
    }

    public void saveComicBook() throws IOException {
        try (PrintWriter pw = new PrintWriter(DATA_FILE)) {
            pw.println(comicBooks.size());
            for (ComicBook cb : comicBooks) {
                pw.println(cb.getId());
                pw.println(cb.getTitle());
                pw.println(String.format(java.util.Locale.US, "%.2f", cb.getBookRentalPrice()));
                pw.println(cb.getAuthor());
                pw.println(cb.getVolume());
            }
        }
    }

    public void findBookByAuthor(Scanner sc) {
        System.out.print("Please enter author: ");
        String findBook = sc.nextLine().trim();

        ArrayList<ComicBook> result = new ArrayList<>();

        for (ComicBook cb : comicBooks) {
            if (cb.getAuthor().equalsIgnoreCase(findBook)) {
                result.add(cb);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No book found!");
        } else {
            System.out.println("Found " + result.size() + " comic book(s):");
            printSearchResult(result);
        }
    }

    public void searchByTitle(Scanner sc) {
        if (comicBooks.isEmpty()) {
            System.out.println("The comic book store is empty.");
            return;
        }
        String query;
        do {
            System.out.print("Enter title keyword to search: ");
            query = sc.nextLine().trim().toLowerCase();
            if (query.isEmpty()) {
                System.out.println("Search key cannot be empty. Please try again.");
            }
        } while (query.isEmpty());

        ArrayList<ComicBook> result = new ArrayList<>();
        for (ComicBook b : comicBooks) {
            if (b.getTitle().toLowerCase().contains(query) == true) {
                result.add(b);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No comic book found with title containing \"" + query + "\".");
        } else {
            System.out.println("Found " + result.size() + " comic book(s):");
            printSearchResult(result);
        }
    }

    private void printSearchResult(ArrayList<ComicBook> list) {
        int wTitle = 5, wAuthor = 6;
        for (ComicBook b : list) {
            wTitle = Math.max(wTitle, b.getTitle().length());
            wAuthor = Math.max(wAuthor, b.getAuthor().length());
        }
        String line = "+" + dashes(6) + "+" + dashes(wTitle + 2) + "+" + dashes(14)
                + "+" + dashes(wAuthor + 2) + "+" + dashes(8) + "+";
        String headFmt = "| %4s | %-" + wTitle + "s | %12s | %-" + wAuthor + "s | %6s |%n";
        String rowFmt = "| %4d | %-" + wTitle + "s | %12.2f | %-" + wAuthor + "s | %6d |%n";

        System.out.println(line);
        System.out.printf(headFmt, "ID", "Title", "Rental Price", "Author", "Volume");
        System.out.println(line);
        for (ComicBook b : list) {
            System.out.print(String.format(Locale.US, rowFmt, b.getId(), b.getTitle(),
                    b.getBookRentalPrice(), b.getAuthor(), b.getVolume()));
        }
        System.out.println(line);
    }
}
