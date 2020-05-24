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
        JSONService.getIssues().removeAll(JSONService.getIssues());

        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("mainWindow.fxml"));
        Scene RegisterScene =  new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(RegisterScene);
        window.show();
    }


    public void viewMyBooks(ActionEvent actionEvent) {

        //Create the table where the student can see al the books he borrowed
        Stage viewBooksWindow = new Stage();
        TableView<Book> bookTable = new TableView<>();

        //Make the name column
        TableColumn<Book, String> bookColumn = new TableColumn<>("MY BOOKS");
        bookColumn.setMinWidth(300);
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        bookTable.getColumns().add(bookColumn);

        //Create the scene and add it to the stage
        StackPane layout = new StackPane(bookTable);
        Scene viewBookScene = new Scene(layout, 303, 162);
        viewBooksWindow.setScene(viewBookScene);

        //Load the books in the table
        for(Book b : JSONService.getBooks())
        {
            if(b.getBorrower().equals(JSONService.getBorrowerUsername())) bookTable.getItems().add(b);
        }

        //Display the stage on the screen
        viewBooksWindow.show();
    }

    public void handleBorrowButton(ActionEvent actionEvent) {

        if(JSONService.getBorrowingLimit() < 5)
        {
            ObservableList<Book> allBooks, selectedBook;
            Book aux;

            selectedBook = Available.getSelectionModel().getSelectedItems();
            allBooks = Available.getItems();

            aux = selectedBook.get(0);

            //Remove the selected book from the Available table
            allBooks.remove(aux);

            //Set the selected book as "unavailable" and set it's borrower
            JSONService.getBooks().remove(aux);
            aux.setType("Unavailable");
            aux.setBorrower(JSONService.getBorrowerUsername());

            //Add the book in the Unavailable table
            StudentController.getInstance().getUnavailableTable().getItems().add(aux);

            //Overwrite the books.JSON
            JSONService.writeBookInFile(aux);

            //Update the borrowing limit so the student would not be able to borrow more than 5 books
            JSONService.updateBorrowingLimit();
        }
        else
        {
            //If the maximum number of books that ca be borrowed was reached display an alert box on the screen
            Stage window = new Stage();
            window.setTitle("ERROR");
            window.initModality(Modality.APPLICATION_MODAL);

            Label errorLabel1 = new Label("Return a book in order to");
            Label errorLabel2 = new Label("borrow another.");
            errorLabel1.setFont(new Font(25));
            errorLabel2.setFont(new Font(25));

            VBox vBox = new VBox(errorLabel1, errorLabel2);
            vBox.setAlignment(Pos.CENTER);

            Scene errorScene = new Scene(vBox, 350, 180);

            window.setScene(errorScene);
            window.show();
        }

    }

    public void returnAction(ActionEvent actionEvent) {

        //Create the table where the student can see al the books he borrowed
        Stage viewBooksWindow = new Stage();
        TableView<Book> bookTable = new TableView<>();

        //Make the name column
        TableColumn<Book, String> bookColumn = new TableColumn<>("MY BOOKS");
        bookColumn.setMinWidth(300);
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        bookTable.getColumns().add(bookColumn);

        Button returnButton = new Button("RETURN");

        //Create the scene and add it to the stage
        VBox layout = new VBox(15, bookTable, returnButton);
        layout.setAlignment(Pos.CENTER);

        Scene viewBookScene = new Scene(layout, 303, 200);
        viewBooksWindow.setScene(viewBookScene);

        //Load the books in the table
        for(Book b : JSONService.getBooks())
        {
            if(b.getBorrower().equals(JSONService.getBorrowerUsername())) bookTable.getItems().add(b);
        }

        //Display the stage on the screen
        viewBooksWindow.show();

        returnButton.setOnAction(e -> {
            //Delete the selected book from the table with the borrowed books
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            bookTable.getItems().remove(selectedBook);

            //Remove the book from the JSON book list
            JSONService.getBooks().remove(selectedBook);

            //Move the book in the Student table from Unavailable to Available
            StudentController.getInstance().getUnavailableTable().getItems().remove(selectedBook);
            StudentController.getInstance().getAvailableTable().getItems().add(selectedBook);

            //Make the book available and reset it's borrower
            selectedBook.setBorrower("");
            selectedBook.setType("Available");

            //Overwrite the books.json
            JSONService.writeBookInFile(selectedBook);

            //Decrease the borrowing limit
            JSONService.decreaseBorrowingLimit();
        });
    }

    public void makeIssueRequest(ActionEvent actionEvent) {

        //Create the table where the student can see al the books he borrowed
        Stage viewBooksWindow = new Stage();
        TableView<Book> bookTable = new TableView<>();

        //Make the name column
        TableColumn<Book, String> bookColumn = new TableColumn<>("MY BOOKS");
        bookColumn.setMinWidth(300);
        bookColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        bookTable.getColumns().add(bookColumn);

        Label errorLabel = new Label();

        Button issueButton = new Button("ISSUE");

        //Create the scene and add it to the stage
        VBox layout = new VBox(15, bookTable,errorLabel , issueButton);
        layout.setAlignment(Pos.CENTER);

        Scene viewBookScene = new Scene(layout, 303, 200);
        viewBooksWindow.setScene(viewBookScene);

        //Load the books in the table
        for(Book b : JSONService.getBooks())
        {
            if(b.getBorrower().equals(JSONService.getBorrowerUsername())) bookTable.getItems().add(b);
        }

        //Display the stage on the screen
        viewBooksWindow.show();

        issueButton.setOnAction(e -> {
            //Delete the selected book from the table with the borrowed books
            Book selectedBook = bookTable.getSelectionModel().getSelectedItem();
            bookTable.getItems().remove(selectedBook);

            Unavailable.getItems().remove(selectedBook);

            //Remove the book from the JSONService book list
            JSONService.getBooks().remove(selectedBook);

            //Overwrite the books.json
            selectedBook.setType("unknown");
            selectedBook.setBorrower("");

            JSONService.writeBookInFile(selectedBook);

            //Send the issue request to issues.json
            JSONService.writeIssueInFile(selectedBook.getName());
        });
    }
}


