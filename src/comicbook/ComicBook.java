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
        this.id = id;
        this.title = title;
        this.bookRentalPrice = bookRentalPrice;
        this.author = author;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return "ComicBook{" + "id=" + id + ", title=" + title + ", bookRentalPrice=" + bookRentalPrice + ", author=" + author + ", volume=" + volume + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getBookRentalPrice() {
        return bookRentalPrice;
    }

    public void setBookRentalPrice(double bookRentalPrice) {
        this.bookRentalPrice = bookRentalPrice;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

}
