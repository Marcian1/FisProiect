package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Book;
import services.JSONService;

import java.io.IOException;

//import static controllers.LibrarianController.Available;
//import static controllers.LibrarianController.AvailableColumn;

public class addDialogController {
    public Button addButton;

    @FXML
    private TextField bookName;

    public void add(ActionEvent actionEvent)
    {
        LibrarianController.getInstance().getAvailableColumn().setCellValueFactory(new PropertyValueFactory<>("name"));

        JSONService.writeBookInFile(new Book(bookName.getText(), "Available"));

        LibrarianController.getInstance().getAvailableTable().getItems().add(new Book(bookName.getText(), "Available"));

    }

    public void back(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("addWindow.fxml"));
        Scene RegisterScene =  new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(RegisterScene);
        window.close();
    }
}
