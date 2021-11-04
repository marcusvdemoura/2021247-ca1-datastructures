package com.marcusmoura.ca1datastructures;

import com.marcusmoura.ca1datastructures.entities.Library;
import com.marcusmoura.ca1datastructures.importantvariables.Variables;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RunApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RunApplication.class.getResource("MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Marcus Moura Library!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        Library library = new Library("Marcus Moura Library");
        library.fetchDataFromFiles();
        System.out.println("the size: " + Variables.FULL_WAITING_LIST.size());
        launch();
    }
}