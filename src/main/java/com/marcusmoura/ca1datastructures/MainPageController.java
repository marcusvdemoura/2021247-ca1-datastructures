package com.marcusmoura.ca1datastructures;

import com.marcusmoura.ca1datastructures.entities.Book;
import com.marcusmoura.ca1datastructures.entities.Client;
import com.marcusmoura.ca1datastructures.entities.Library;
import com.marcusmoura.ca1datastructures.filesmanagement.WriteToFile;
import com.marcusmoura.ca1datastructures.importantvariables.Variables;
import com.marcusmoura.ca1datastructures.tableviewPOJO.BooksMaster;
import com.marcusmoura.ca1datastructures.tableviewPOJO.ClientMaster;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    // annotations for book search tab
    @FXML
    private ChoiceBox<String> choiceBoxSearchBook;

    @FXML
    private TextField insertSearchBook;

    @FXML
    private Button searchBookBtn;


    @FXML
    private TableView<BooksMaster> booksView;

    @FXML
    private TableColumn<BooksMaster, String> col_bookid, col_authorname, col_title, col_genre;

    @FXML
    private TableColumn<BooksMaster, Boolean> col_available;

    private String[] choicesBook = {"All", "By author", "By title"};
    private String choiceBook;

    // annotations for client search tab

    @FXML
    private ChoiceBox<String> choiceBoxSearchClient;

    @FXML
    private TextField insertSearchClient;

    @FXML
    private Button searchClientBtn;

    @FXML
    private TableView<ClientMaster> clientView;

    @FXML
    private TableColumn<ClientMaster, Number> col_clientId;

    @FXML
    private TableColumn<ClientMaster, String> col_clientName, col_clientEmail;

    private String[] choicesClient = {"All", "By ID", "By Name"};
    private String choiceClient;


    // Borrow pane section

    @FXML
    private Pane borrowPane;

    @FXML
    private TextField insertClientId;

    @FXML
    private TextField insertBookName;

    @FXML
    private Button borrowBtn;

    // Return pane section

    @FXML
    private Pane returnPane;

    @FXML
    private TableView<BooksMaster> borrowedBooksTable;

    @FXML
    private TableColumn<BooksMaster, String> col_borrowedBookid, col_borrowedAuthorname,
            col_borrowedTitle, col_borrowedGenre;

    @FXML
    private TableColumn<BooksMaster, Boolean> col_borrowedAvailable;

    @FXML
    private Button returnBtn;
    @FXML
    private TextField insertReturnClientId;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // for search books tab

        getAllBooksToTable();

        choiceBoxSearchBook.getItems().addAll(choicesBook);


        choiceBoxSearchBook.setOnAction(actionEvent -> {
            choiceBook = choiceBoxSearchBook.getValue();
            System.out.println("The search book choice is: " + choiceBook);

            searchBookBtn.setOnAction(actionEvent1 -> {
                String textInputBook = insertSearchBook.getText();
                System.out.println(textInputBook);

                updateBookTableView(choiceBook, textInputBook);
                if (textInputBook.isEmpty() || choiceBook.contentEquals(choicesBook[0])) {
                    getAllBooksToTable();
                }


            });


        });

        // for clients search tab

        getAllClients();

        choiceBoxSearchClient.getItems().addAll(choicesClient);

        choiceBoxSearchClient.setOnAction(actionEvent -> {
            choiceClient = choiceBoxSearchClient.getValue();
            System.out.println("The search client choice is: " + choiceClient);

            searchClientBtn.setOnAction(actionEvent1 -> {
                String textInputClient = insertSearchClient.getText();
                System.out.println("Text input for client: " + textInputClient);
                updateClientTableView(choiceClient, textInputClient);

                if (choiceClient.contentEquals(choicesClient[0]) || textInputClient.isEmpty()) {
                    getAllClients();
                }

            });

        });

        // borrow section

        borrowPane.setStyle("-fx-background-color: #" + "DDDDDD");
        borrowPane.setBorder(new Border(new BorderStroke(Color.rgb(178, 177, 185), BorderStrokeStyle.SOLID,
                null, null)));


        borrowBtn.setOnAction(actionEvent -> {
            try {
                Integer clientID = Integer.parseInt(insertClientId.getText());
                String bookName = insertBookName.getText();
                borrowBooks(clientID, bookName);


            } catch (NumberFormatException err) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Please, insert a valid number to search");
                alert.show();
                System.out.println("Hey, this is NOT a number -> borrow session");
            }
        });


        // Return Pane

        returnPane.setStyle("-fx-background-color: #" + "DDDDDD");
        returnPane.setBorder(new Border(new BorderStroke(Color.rgb(178, 177, 185), BorderStrokeStyle.SOLID,
                null, null)));

        returnBtn.setOnAction(actionEvent -> {
            try{
                Integer clientId = Integer.parseInt(insertReturnClientId.getText());
                updateReturnBookTableView(clientId);


            }catch (NumberFormatException err) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Please, insert a valid number to search");
                alert.show();
                System.out.println("Hey, this is NOT a number -> return session");
            }
        });



    }

    private ObservableList<BooksMaster> getBooksToTable(String parameter, String argument) {
        ObservableList<BooksMaster> list = FXCollections.observableArrayList();

        if (parameter.contentEquals("By author")) {
            for (Book book : Variables.BOOK_LIST) {

                if (argument.equalsIgnoreCase(book.getAuthorFirstName() + " " + book.getAuthorLastName())) {
                    BooksMaster booksMaster = new BooksMaster();
                    booksMaster.setId(book.getId());
                    booksMaster.setAuthorName(book.getAuthorFirstName() + " " + book.getAuthorLastName());
                    booksMaster.setTitle(book.getBookTitle());
                    booksMaster.setGenre(book.getGenre());
                    booksMaster.setIsAvailable(book.getAvailable());
                    list.add(booksMaster);
                }


            }
        } else if (parameter.equalsIgnoreCase("By title")) {
            for (Book book : Variables.BOOK_LIST) {

                if (argument.contentEquals(book.getBookTitle())) {
                    BooksMaster booksMaster = new BooksMaster();
                    booksMaster.setId(book.getId());
                    booksMaster.setAuthorName(book.getAuthorFirstName() + " " + book.getAuthorLastName());
                    booksMaster.setTitle(book.getBookTitle());
                    booksMaster.setGenre(book.getGenre());
                    booksMaster.setIsAvailable(book.getAvailable());
                    list.add(booksMaster);
                }


            }
        } else {
            getAllBooksToTable();
        }

        return list;
    }

    private void getAllBooksToTable() {
        ObservableList<BooksMaster> booksMasterObservableList = FXCollections.observableArrayList();
        for (Book book : Variables.BOOK_LIST) {
            BooksMaster booksMaster = new BooksMaster();
            booksMaster.setId(book.getId());
            booksMaster.setAuthorName(book.getAuthorFirstName() + " " + book.getAuthorLastName());
            booksMaster.setTitle(book.getBookTitle());
            booksMaster.setGenre(book.getGenre());
            booksMaster.setIsAvailable(book.getAvailable());
            booksMasterObservableList.add(booksMaster);
        }

        col_bookid.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        col_authorname.setCellValueFactory(cellData -> cellData.getValue().authorNameProperty());
        col_title.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        col_genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        col_available.setCellValueFactory(cellData -> cellData.getValue().isAvailableProperty());

        booksView.getItems().setAll(booksMasterObservableList);
    }

    private void updateBookTableView(String parameter, String argument) {

        col_bookid.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        col_authorname.setCellValueFactory(cellData -> cellData.getValue().authorNameProperty());
        col_title.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        col_genre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        col_available.setCellValueFactory(cellData -> cellData.getValue().isAvailableProperty());


        booksView.getItems().setAll(getBooksToTable(parameter, argument));

    }


    // CLIENTS TAB FUNCTIONS


    public void getAllClients() {
        ObservableList<ClientMaster> clientMasterObservableList = FXCollections.observableArrayList();

        for (Client client : Variables.CLIENT_LIST) {
            ClientMaster clientMaster = new ClientMaster();
            clientMaster.setId(client.getId());
            clientMaster.setName(client.getFirstName() + " " + client.getLastName());
            clientMaster.setEmail(client.getEmail());
            clientMasterObservableList.add(clientMaster);
        }

        col_clientId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        col_clientName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        col_clientEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        clientView.getItems().setAll(clientMasterObservableList);
    }

    private ObservableList<ClientMaster> getClientsToTable(String parameter, String argument) {
        ObservableList<ClientMaster> clientMasterObservableList = FXCollections.observableArrayList();

        if (parameter.contentEquals("By Name")) {
            for (Client client : Variables.CLIENT_LIST) {

                if (argument.equalsIgnoreCase(client.getFirstName() + " " + client.getLastName())) {
                    ClientMaster clientMaster = new ClientMaster();
                    clientMaster.setId(client.getId());
                    clientMaster.setName(client.getFirstName() + " " + client.getLastName());
                    clientMaster.setEmail(client.getEmail());
                    clientMasterObservableList.add(clientMaster);
                }


            }
        } else if (parameter.equalsIgnoreCase("By ID")) {
            System.out.println("this is the id :" + argument);

            try {
                Integer x = Integer.parseInt(argument);
                for (Client client : Variables.CLIENT_LIST) {
                    if (Integer.parseInt(argument) == client.getId()) {
                        System.out.println(client.toString());
                        ClientMaster clientMaster = new ClientMaster();
                        clientMaster.setId(client.getId());
                        clientMaster.setName(client.getFirstName() + " " + client.getLastName());
                        clientMaster.setEmail(client.getEmail());
                        clientMasterObservableList.add(clientMaster);
                    }
                }


            } catch (NumberFormatException err) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Please, insert a valid number to search");
                alert.show();
                System.out.println("Hey, there's no number there");
            }
        } else {
            getAllClients();
        }

        return clientMasterObservableList;
    }

    public void updateClientTableView(String parameter, String argument) {

        col_clientId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        col_clientName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        col_clientEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());

        clientView.getItems().setAll(getClientsToTable(parameter, argument));
    }


    // RETURN METHODS

    private ObservableList<BooksMaster> getBorrowedBooksToTable(Integer argument) {
        ObservableList<BooksMaster> list = FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> entry : Variables.BOOKS_BORROWED.entrySet()) {
            if (entry.getValue() == argument) {
                for (Book book : Variables.BOOK_LIST) {
                    if (book.getId().contentEquals(entry.getKey())) {
                        BooksMaster booksMaster = new BooksMaster();
                        booksMaster.setId(book.getId());
                        booksMaster.setAuthorName(book.getAuthorFirstName() + " " + book.getAuthorLastName());
                        booksMaster.setTitle(book.getBookTitle());
                        booksMaster.setGenre(book.getGenre());
                        booksMaster.setIsAvailable(book.getAvailable());
                        list.add(booksMaster);
                    }
                }
            }
        }


        return list;


    }

    private void updateReturnBookTableView(Integer argument) {

        col_borrowedBookid.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        col_borrowedAuthorname.setCellValueFactory(cellData -> cellData.getValue().authorNameProperty());
        col_borrowedTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        col_borrowedGenre.setCellValueFactory(cellData -> cellData.getValue().genreProperty());
        col_borrowedAvailable.setCellValueFactory(cellData -> cellData.getValue().isAvailableProperty());


        borrowedBooksTable.getItems().setAll(getBorrowedBooksToTable(argument));

    }

    public void getBookBorrowedAndReturn(javafx.scene.input.MouseEvent mouseEvent){
        int indexBookBorrowed = -1;

        indexBookBorrowed = borrowedBooksTable.getSelectionModel().getSelectedIndex();
        if (indexBookBorrowed <= -1) {
            return;
        }

        String bookId = col_borrowedBookid.getCellData(indexBookBorrowed);
//        Integer clientId = Integer.parseInt(col_borrowedBookid.getCellData(indexBookBorrowed));

        for (Book b: Variables.BOOK_LIST){
            if(b.getId().equalsIgnoreCase(bookId)){
                b.setAvailable(true);
                System.out.println(b.getId() + " " + b.getBookTitle() + " RETURNED!");
                // alert

                if(!b.getWaitingList().isEmpty()){
                    Client frontInQueue = b.getWaitingList().getFront();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("The next client waiting for the book is: " + frontInQueue.getId());
                    alert.show();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("There's no client waiting for the book!");
                    alert.show();
                }


            }
        }

        Variables.BOOKS_BORROWED.remove(bookId, Integer.parseInt(insertReturnClientId.getText()));
        System.out.println("Removed from borrowed books list and available status changed");

        WriteToFile write = new WriteToFile("Books borrowed");

        for (Map.Entry<String, Integer> entry : Variables.BOOKS_BORROWED.entrySet()){

            write.writeBorrowed(entry.getKey(), entry.getValue());

        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("");
        alert.setHeaderText("Book returned!");
        alert.show();



        borrowedBooksTable.getItems().clear();



    }

    private void borrowBooks(Integer clientID, String bookName){

        Book book;

        for (Book b : Variables.BOOK_LIST) {
            if (b.getBookTitle().equalsIgnoreCase(bookName)) {
                book = b;

                // if book is available
                if (b.getAvailable()) {
                    // set the book availability to false
                    b.setAvailable(false);

                    System.out.println("The book " + b.getBookTitle() + " written by " +
                            b.getAuthorLastName() + "," + b.getAuthorFirstName() + " is available. Borrowed.");

                    // add the book borrowed to borrowed list
                    Variables.BOOKS_BORROWED.put(b.getId(), clientID);
                    // we update the files with the books borrowed
                    WriteToFile writeToFile = new WriteToFile();
                    writeToFile.writeBorrowed(b.getId(), clientID);

                    // give an alert to the user
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("");
                    alert.setHeaderText("Booked borrowed");
                    alert.show();


                } else {

                    // alert, giving the option to add the client to the queue
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Add to Waiting List");
                    alert.setHeaderText("Do you want to add this client to the book's waiting list?");
                    alert.setContentText("Are you ok with this?");

                    Optional<ButtonType> result = alert.showAndWait();


                    // if user wants to add the client to the list
                    if (result.get() == ButtonType.OK){
                        Client client = getClient(clientID);
                        if(client!=null){
                            // add the client to the books queue
                            book.getWaitingList().queueEnqueue(client);

                            // alert informing the user the client has been added to the books queue
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                            alert1.setTitle("");
                            alert1.setHeaderText("Client added to " + book.getBookTitle()+" waiting list");
                            alert1.show();



                        }

                    } else {
                        // ... user chose CANCEL or closed the dialog
                    }

                }
            }
        }
    }

    private Client getClient(Integer clientId){
        Client client = null;
        for (Client c: Variables.CLIENT_LIST){
            if (c.getId()==clientId){
                client = c;
            }
        }
        return client;
    }

}


