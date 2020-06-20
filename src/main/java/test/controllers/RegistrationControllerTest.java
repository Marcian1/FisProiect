//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package test.controllers;

import controllers.RegistrationController;
import java.io.FileWriter;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import services.UserService;

public class RegistrationControllerTest extends ApplicationTest {
    public static final String TEST_USER = "testUser1";
    public static final String TEST_PASSWORD = "testPassword1";
    private RegistrationController controller;
    ActionEvent event = new ActionEvent();

    public RegistrationControllerTest() {
    }

    @BeforeClass
    public static void setupClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        FileWriter file = new FileWriter(System.getProperty("user.dir").toString() + "\\src\\main\\resources\\users.json");
        file.write("");
        UserService.loadUsersFromFile();
        this.controller = new RegistrationController();
        this.controller.usernameField = new TextField();
        this.controller.passwordField = new PasswordField();
        this.controller.role = new ChoiceBox();
        this.controller.registrationMessage = new Text();
        this.controller.Adress = new TextField();
        this.controller.Mail = new TextField();
        this.controller.PhoneNumber = new TextField();
        this.controller.FullName = new TextField();
        this.controller.passwordField.setText("testPassword1");
        this.controller.usernameField.setText("testUser1");
    }

    @Test
    public void initializeTest() {
        this.controller.initialize();
        Assert.assertEquals(this.controller.role.getItems().get(0).toString(), "Student");
        Assert.assertEquals(this.controller.role.getItems().get(1).toString(), "Librarian");
    }

    @Test
    public void handleRegisterActionTest() {
        this.controller.handleRegisterAction();
        Assert.assertEquals(1L, (long)UserService.getUsers().size());
    }

    @Test
    public void goBackTest() throws IOException {
        try {
            this.controller.goBack(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void testAddSameUserTwice() {
        this.controller.handleRegisterAction();
        this.controller.handleRegisterAction();
        Assert.assertEquals("An account with the username testUser1 already exists!", this.controller.registrationMessage.getText());
    }
}
