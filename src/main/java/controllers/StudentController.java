package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Book;
import services.JSONService;

import java.io.IOException;


public class StudentController {

    private static StudentController instance;

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
    private  TableColumn IssuedColumn;

    //Getters for the 3 tabels
    public TableView getIssuedTable() { return Issued; }
    public TableColumn getIssuedColumn() { return IssuedColumn; }

    public TableView getAvailableTable()
    {
        return Available;
    }
    public TableColumn getAvailableColumn()
    {
        return AvailableColumn;
    }

    public TableView getUnavailableTable()
    {
        return Unavailable;
    }
    public TableColumn getUnavailableColumn()
    {
        return UnavailableColumn;
    }

    public StudentController()
    {
        instance = this;
    }

    public static StudentController getInstance()
    {
        return instance;
    }

    private ObservableList<Book> dataList = FXCollections.observableArrayList();

    public void goBackAction(ActionEvent actionEvent) throws IOException {
        JSONService.getBooks().removeAll(JSONService.getBooks());

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainWindow.fxml"));
        Scene RegisterScene =  new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(RegisterScene);
        window.show();
    }


}


