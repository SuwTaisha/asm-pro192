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
public class ComicBook {

    private int id;
    private String title;
    private double bookRentalPrice;
    private String author;
    private int volume;

    public ComicBook(int id, String title, double bookRentalPrice, String author, int volume) {
        setId(id);
        setTitle(title);
        setBookRentalPrice(bookRentalPrice);
        setAuthor(author);
        setVolume(volume);
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0.");
        }
        this.id = id;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title must not be empty.");
        }
        this.title = title.trim();
    }

    public void setBookRentalPrice(double price) {
        if (price <= 0 || Double.isNaN(price) || Double.isInfinite(price)) {
            throw new IllegalArgumentException("Rental price must be greater than 0.");
        }
        this.bookRentalPrice = price;
    }

    public void setAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author must not be empty.");
        }
        this.author = author.trim();
    }

    public void setVolume(int volume) {
        if (volume <= 0) {
            throw new IllegalArgumentException("Volume must be greater than 0.");
        }
        this.volume = volume;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public double getBookRentalPrice() {
        return bookRentalPrice;
    }

    public String getAuthor() {
        return author;
    }

    public int getVolume() {
        return volume;
    }
    
}
