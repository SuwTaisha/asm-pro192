/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comicbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
}
