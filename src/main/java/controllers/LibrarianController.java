package controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Book;
import services.JSONService;

import java.io.IOException;

public class LibrarianController {
    public Button addBookButton;

    @FXML
    private TableView Available;
    @FXML
    private TableColumn AvailableColumn;

    @FXML
    private TableView Unavailable;
    @FXML
    private TableColumn UnavailableColumn;

    @FXML
    private TableView Issued;
    @FXML
    private TableColumn IssuedColumn;

    @FXML
    private Button goBack;

    public TableView getUnavailableTable() {
        return Unavailable;
    }

    public TableColumn getUnavailableColumn() {
        return UnavailableColumn;
    }

    public TableView getIssuedTable() {
        return Issued;
    }

    public TableColumn getIssuedColumn() {
        return IssuedColumn;
    }

    public TableView getAvailableTable()
    {

        return Available;
    }

    public TableColumn getAvailableColumn()
    {

        return AvailableColumn;
    }

    private static LibrarianController instance;

    public LibrarianController()
    {
        instance = this;
    }

    public static LibrarianController getInstance()
    {
        return instance;
    }

    public static ObservableList<Book> makeBookList()
    {
        ObservableList<Book> names= FXCollections.observableArrayList();


        return names;
    }

    public void goBackAction(ActionEvent actionEvent) throws IOException {
        JSONService.getBooks().removeAll(JSONService.getBooks());

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainWindow.fxml"));
        Scene RegisterScene =  new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(RegisterScene);
        window.show();
    }

    public void add(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addWindow.fxml"));
        Scene AddBookScene =  new Scene(root);
        //Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        final Stage window = new Stage();
        window.setScene(AddBookScene);

        window.show();
    }

    public void handleIssueAction(ActionEvent actionEvent) {
        //Save the selection in selectedBook variable
        Book selectedBook = (Book) Available.getSelectionModel().getSelectedItem();

        //Delete it from the available table
        Available.getItems().remove(selectedBook);

        //Add the book to issued section
        Issued.getItems().add(selectedBook);

        //Mark the book as "Issued" and overwrite the books.json
        JSONService.getBooks().remove(selectedBook);

        selectedBook.setType("Issued");
        JSONService.writeBookInFile(selectedBook);
    }
}
