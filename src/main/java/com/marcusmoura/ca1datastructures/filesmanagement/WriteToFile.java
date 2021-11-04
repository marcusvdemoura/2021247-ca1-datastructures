package com.marcusmoura.ca1datastructures.filesmanagement;

import com.marcusmoura.ca1datastructures.entities.Book;
import com.marcusmoura.ca1datastructures.entities.Client;
import com.marcusmoura.ca1datastructures.importantvariables.Variables;

import java.io.*;

public class WriteToFile {

    public WriteToFile() {
    }

    public WriteToFile(String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName+".csv");
            myWriter.write(fileName+": \n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void writeBook(Book b) {
        String filePath = Variables.LIST_BOOKS;

        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));) {
            String data = b.getId()+","+b.getAuthorFirstName()+","+b.getAuthorLastName()+","+b.getBookTitle()+
                    ","+b.getGenre()+","+b.getAvailable();
            File file = new File(filePath);
            out.println(data);
        } catch(IOException e) {
        }
    }

    public void writeClient(Client c) {

        String filePath = Variables.LIST_CLIENTS;

        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));) {
            String data = c.getId()+","+c.getFirstName()+","+c.getLastName()+","+c.getEmail();
            File file = new File(filePath);
            out.println(data);
        } catch(IOException e) {
        }
    }

    public void writeBorrowed(String bookId, Integer clientId) {

        String filePath = Variables.LIST_BORROWED;

        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));) {
            // first book, then client
            String data =
                    bookId+","
                            +clientId;

            File file = new File(filePath);
            out.println(data);
        } catch(IOException e) {
        }
    }

    public void writeWaiting(String bookId, Integer clientId) {

        String filePath = Variables.LIST_WAITING;

        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true)));) {
            // first book, then client
            String data =
                    bookId+","
                            +clientId;

            File file = new File(filePath);
            out.println(data);
        } catch(IOException e) {
        }
    }
}
