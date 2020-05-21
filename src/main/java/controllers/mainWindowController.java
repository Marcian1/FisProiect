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
import model.User;
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
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginFXML.fxml"));
                        Scene RegisterScene = new Scene(root);
                        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(RegisterScene);
                        window.show();


                        break;
                    }
                    if (UserNameL.getText().equals(user.getUsername())
                            && UserService.encodePassword(UserNameL.getText(), PasswordL.getText()).equals(user.getPassword())
                            && user.getRole().equals("Student")) {

                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("LoginFXML.fxml"));
                        Scene RegisterScene = new Scene(root);
                        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                        window.setScene(RegisterScene);
                        window.show();
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