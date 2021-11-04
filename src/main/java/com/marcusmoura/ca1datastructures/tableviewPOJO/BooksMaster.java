package com.marcusmoura.ca1datastructures.tableviewPOJO;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class BooksMaster {

    private final SimpleStringProperty id = new SimpleStringProperty();
    private final SimpleStringProperty authorName = new SimpleStringProperty();
    private final SimpleStringProperty title = new SimpleStringProperty();
    private final SimpleStringProperty genre = new SimpleStringProperty();
    private final SimpleBooleanProperty isAvailable = new SimpleBooleanProperty();

    public BooksMaster() {
    }



    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getAuthorName() {
        return authorName.get();
    }

    public SimpleStringProperty authorNameProperty() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName.set(authorName);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getGenre() {
        return genre.get();
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

    public boolean isIsAvailable() {
        return isAvailable.get();
    }

    public SimpleBooleanProperty isAvailableProperty() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable.set(isAvailable);
    }

    @Override
    public String toString() {
        return "BooksMaster{" +
                "id=" + id +
                ", authorName=" + authorName +
                ", title=" + title +
                ", genre=" + genre +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
