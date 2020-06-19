//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package test.controllers;

import controllers.mainWindowController;
import java.io.IOException;
import javafx.event.ActionEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class mainWindowControllerTest {
    public static final String TEST_USER = "testUser1";
    public static final String TEST_PASSWORD = "testPassword1";
    private mainWindowController controller;
    ActionEvent event = new ActionEvent();

    public mainWindowControllerTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void handleRegisterTest() throws IOException {
        try {
            this.controller.handleRegister(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void handleLoginTest() throws IOException {
        try {
            this.controller.handleRegister(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }
}
