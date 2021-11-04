package com.marcusmoura.ca1datastructures.entities;

import com.marcusmoura.ca1datastructures.queuecustom.MyQueue;

public class Book {
    private String id;
    private String authorFirstName;
    private String authorLastName;
    private String bookTitle;
    private String genre;
    private Boolean isAvailable;
    private MyQueue<Client> waitingList = new MyQueue<Client>();

    public Book(String id, String authorFirstName, String authorLastName,
                String bookTitle, String genre) {
        this.id = id;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.bookTitle = bookTitle;
        this.genre = genre;
    }

    public Book() {
    }

    // getters and setters


    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public MyQueue<Client> getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(MyQueue<Client> waitingList) {
        this.waitingList = waitingList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                ", genre='" + genre + '\'' +
                ", isAvailable='" + isAvailable +
                '}';
    }
}
