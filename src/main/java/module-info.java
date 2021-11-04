module com.marcusmoura.ca1datastructures {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.marcusmoura.ca1datastructures to javafx.fxml;
    exports com.marcusmoura.ca1datastructures;
}