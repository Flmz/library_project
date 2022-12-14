package ru.denis.library.models;

import javax.validation.constraints.NotEmpty;

public class Book {
    private int id;

    @NotEmpty(message = "Имя книги не должно быть пустым")
    private String bookName;

    @NotEmpty(message = "У книги должен быть автор")
    private String bookAuthor;

    private int yearOfProduction;

    public Book() {

    }

    public Book(int id, String bookName, String bookAuthor, int yearOfProduction) {
        this.id = id;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.yearOfProduction = yearOfProduction;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}
