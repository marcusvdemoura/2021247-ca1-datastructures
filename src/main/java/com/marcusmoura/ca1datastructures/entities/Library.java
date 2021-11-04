package com.marcusmoura.ca1datastructures.entities;

import com.marcusmoura.ca1datastructures.filesmanagement.ReadFromFile;
import com.marcusmoura.ca1datastructures.filesmanagement.WriteToFile;
import com.marcusmoura.ca1datastructures.importantvariables.Variables;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private String libraryName;
    private static ReadFromFile rf = new ReadFromFile();
    private WriteToFile write = new WriteToFile();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Library(String libraryName) {
        this.libraryName = libraryName;
        System.out.println("Library created");
    }

    // getters and setters
    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }


    public static void fetchDataFromFiles(){

        Variables.BOOKS_BORROWED.clear();
        Variables.BOOK_LIST.clear();
        Variables.CLIENT_LIST.clear();
        Variables.FULL_WAITING_LIST.clear();

        for (String[] x: rf.getLines(Variables.LIST_CLIENTS)){
            Variables.CLIENT_LIST.add(new Client(Integer.parseInt(x[0]), x[1],x[2],x[3]));
        }
        rf.getLines().clear();

        for (String[] x: rf.getLines(Variables.LIST_BOOKS)){
            Book book = new Book(x[0], x[1],x[2],x[3], x[4]);
            book.setAvailable(true);
            Variables.BOOK_LIST.add(book);
        }
        rf.getLines().clear();

        for(String[] x: rf.getLines(Variables.LIST_BORROWED)){

            Variables.BOOKS_BORROWED.put(
                    x[0], Integer.parseInt(x[1])
            );

        }
        rf.getLines().clear();

        for (Map.Entry<String, Integer> entry : Variables.BOOKS_BORROWED.entrySet()){
            for(Book b: Variables.BOOK_LIST){
                if (b.getId().contentEquals(entry.getKey())){
                    b.setAvailable(false);
                }
            }
        }

        // GETTING DATA FROM LIST

        for (String[] x: rf.getLines(Variables.LIST_WAITING)){

            Book book=null;
            Client client=null;

            //get client and book according to data in the list
            for (Client c :
                    Variables.CLIENT_LIST) {
                if (c.getId()==Integer.parseInt(x[1])){
                    client=c;
                }
            }
            for(Book b: Variables.BOOK_LIST){
                if(b.getId().contentEquals(x[0])){
                    book=b;
                }
            }


            if(book==null){
                System.out.println("Nothing to be done. Book is null");
            } else if (client==null){
                System.out.println("Nothing to be done. Client is null");
            } else if(book!=null && client != null) {

                String a[] = {book.getId(), client.getId().toString()};
                Variables.FULL_WAITING_LIST.add(a);

                book.getWaitingList().queueEnqueue(client);
            } else{
                System.out.println("Something is wrong here!");
            }
        }

    }

    public void borrowBooks(Book b, Client c){

        if (b.getAvailable()){
            write.writeBorrowed(b.getId(),c.getId());
            Variables.BOOKS_BORROWED.put(b.getId(), c.getId());
        } else{
            System.out.println("Sorry, book unavailable");

        }

    }

    public void putClientInWaitingList(Book b, Client c){

        b.getWaitingList().queueEnqueue(c);
        write.writeWaiting(b.getId(), c.getId());

    }



    public void returnBooks(String bookId){

        // get the book

        Book b = null;
        for(Book x: Variables.BOOK_LIST){
            if (x.getId().contentEquals(bookId)){
                b=x;
            }
        }
        //remove from the Map in which we list the books borrowed.
        Variables.BOOKS_BORROWED.remove(bookId);
        System.out.println("book returned");

        // we re-write the file with the books that remain borrowed
        WriteToFile writeToFile = new WriteToFile("Books borrowed");

        for(Map.Entry<String, Integer> entry : Variables.BOOKS_BORROWED.entrySet()){
            write.writeBorrowed(entry.getKey(), entry.getValue());
        }

        // We display to the user which reader is the next one in the waiting list

        if(b.getWaitingList().isEmpty()){
            System.out.println("Hey, the waiting list is empty!");
        } else {
            b.getWaitingList().queueFront();
        }

    }


    public Book findBook(String title){
        for (Book x: Variables.BOOK_LIST){
            if (x.getBookTitle().contentEquals(title)){
                return x;
            }
        }
        return null;
    }


    public Client findClient(Integer clientId){
        for (Client c: Variables.CLIENT_LIST){
            if(c.getId()==clientId){
                return c;
            }
        }
        return null;
    }
}
