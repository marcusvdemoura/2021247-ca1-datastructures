package com.marcusmoura.ca1datastructures.importantvariables;

import com.marcusmoura.ca1datastructures.entities.Book;
import com.marcusmoura.ca1datastructures.entities.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Variables {

    public static final String LIST_BOOKS =
            "books.csv";
    public static final String LIST_CLIENTS =
            "clients.csv";
    public static final String LIST_BORROWED =
            "Books borrowed.csv";
    public static final String LIST_WAITING =
            "waiting list.csv";


    public static final List<Client> CLIENT_LIST = new ArrayList<>();
    public static final List<Book> BOOK_LIST = new ArrayList<>();
    public static final Map<String, Integer> BOOKS_BORROWED = new HashMap<>();
}
