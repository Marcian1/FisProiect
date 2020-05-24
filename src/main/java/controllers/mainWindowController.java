package controllers;


import exceptions.UserNameOrPasswordAreIncorrect;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Book;
import model.User;
import services.JSONService;
import services.UserService;


import java.io.IOException;


public class mainWindowController {
    @FXML
    private TextField PasswordL;
    @FXML
    private TextField UserNameL;
    @FXML
    private Text loginMessage;

    public void handleRegister(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        Scene RegisterScene =  new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(RegisterScene);
        window.show();
    }

    public void handleLogin(ActionEvent actionEvent) {

            try {
                UserService.checkIfUserNameOrPasswordAreCorrect(UserNameL.getText(),PasswordL.getText());
                loginMessage.setText("Login has been successful!");
                for (User user : UserService.getUsers()) {
                    if (UserNameL.getText().equals(user.getUsername())
                            && UserService.encodePassword(UserNameL.getText(), PasswordL.getText()).equals(user.getPassword())
                            && user.getRole().equals("Librarian")) {
                        //Load the Librarian interface
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Librarian.fxml"));
                        Scene RegisterScene = new Scene(root);
                        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(RegisterScene);
                        window.show();

                        //Load issue requests from issues.json
                        JSONService.readIssueFromFile();

                        //Load the books from the books.JSON file and put them in the corresponding table
                        JSONService.readBookFromFile();
                        LibrarianController.getInstance().getAvailableColumn().setCellValueFactory(new PropertyValueFactory<>("name"));
                        LibrarianController.getInstance().getUnavailableColumn().setCellValueFactory(new PropertyValueFactory<>("name"));
                        LibrarianController.getInstance().getIssuedColumn().setCellValueFactory(new PropertyValueFactory<>("name"));

                        for(Book b : JSONService.getBooks()) {
                            if (b.getType().equals("Available"))
                                LibrarianController.getInstance().getAvailableTable().getItems().add(b);
                            if (b.getType().equals("Unavailable"))
                                LibrarianController.getInstance().getUnavailableTable().getItems().add(b);
                            if (b.getType().equals("Issued"))
                                LibrarianController.getInstance().getIssuedTable().getItems().add(b);
                        }

                        break;
                    }
                    if (UserNameL.getText().equals(user.getUsername())
                            && UserService.encodePassword(UserNameL.getText(), PasswordL.getText()).equals(user.getPassword())
                            && user.getRole().equals("Student")) {

                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Student.fxml"));
                        Scene RegisterScene = new Scene(root);
                        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(RegisterScene);
                        window.show();

                        //Load the books from the books.JSON file and put them in the corresponding table
                        JSONService.readBookFromFile();
                        StudentController.getInstance().getAvailableColumn().setCellValueFactory(new PropertyValueFactory<>("name"));
                        StudentController.getInstance().getUnavailableColumn().setCellValueFactory(new PropertyValueFactory<>("name"));
                        StudentController.getInstance().getIssuedColumn().setCellValueFactory(new PropertyValueFactory<>("name"));

                        for(Book b : JSONService.getBooks()) {
                            if (b.getType().equals("Available"))
                                StudentController.getInstance().getAvailableTable().getItems().add(b);
                            if (b.getType().equals("Unavailable"))
                                StudentController.getInstance().getUnavailableTable().getItems().add(b);
                            if (b.getType().equals("Issued"))
                                StudentController.getInstance().getIssuedTable().getItems().add(b);
                        }

                        //Save Student's username in JSONService specific variable in case he choose to borrow a book
                        JSONService.setBorrowerUsername(UserNameL.getText());

                        //Go through all the books that this student borrowed and save the number
                        int count = 0;
                        for(Book b : JSONService.getBooks())
                        {
                            if(b.getBorrower().equals(UserNameL.getText())) count++;
                        }

                        JSONService.setBorrowingLimit(count);

                        break;
                    }
                }
            }catch (UserNameOrPasswordAreIncorrect  e) {
                loginMessage.setText(e.getMessage());
            }catch (IOException e) {
                e.printStackTrace();
            }

        }
    }