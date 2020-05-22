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

    public void viewIssuesAction(ActionEvent actionEvent) {
        TableView<String> issueTable = new TableView<>();

        TableColumn<String, String> issueColumn = new TableColumn<>("Issue requests sent by students");
        issueColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
        issueTable.getColumns().add(issueColumn);

        for(String s : JSONService.getIssues())
        {
            issueTable.getItems().add(s);
        }

        Button approveButton = new Button("Approve");
        Button dismissButton = new Button("Dismiss");

        HBox hBox = new HBox(18, approveButton, dismissButton);
        VBox layout = new VBox(15, issueTable, hBox);

        Scene scene = new Scene(layout, 400, 400);
        Stage newWindow = new Stage();
        newWindow.setScene(scene);

        newWindow.show();
        /*
        approveButton.setOnAction(e -> {
            String selectedIssue = issueTable.getSelectionModel().getSelectedItem();

            for(Book b : JSONService.getBooks())
            {
                if(b.getName().equals(selectedIssue))
                {
                    //if(b.getType().equals("Available")) Available.getItems().remove(b);
                    //else Unavailable.getItems().remove(b);

                    Issued.getItems().add(b);
                    issueTable.getItems().remove(selectedIssue);

                    b.setType("Issued");
                    JSONService.writeBookInFile();

                    JSONService.getIssues().remove(selectedIssue);
                    JSONService.writeIssueInFile();
                    break;
                }
            }
        });

        dismissButton.setOnAction(e -> {
            //Get the selected item from the issue request table
            String selectedIssue = issueTable.getSelectionModel().getSelectedItem();

            //Remove it from the table
            issueTable.getItems().remove(selectedIssue);

            //Mark the book as available and make it visible in the available table
            for(Book b : JSONService.getBooks())
            {
                if(b.getName().equals(selectedIssue))
                {
                    b.setType("Available");
                    Available.getItems().add(b);

                    JSONService.writeBookInFile();

                    break;
                }
            }

            //Remove this issue request from the file
            JSONService.getIssues().remove(selectedIssue);
            JSONService.writeIssueInFile();
        });*/
    }
}
