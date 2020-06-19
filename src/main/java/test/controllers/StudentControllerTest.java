//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package test.controllers;

import controllers.StudentController;
import java.io.IOException;
import javafx.event.ActionEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StudentControllerTest {
    StudentController controller;
    ActionEvent event = new ActionEvent();

    public StudentControllerTest() {
    }

    @BeforeClass
    public static void setupClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getIssuedTableTest() {
    }

    @Test
    public void getIssuedColumn() {
    }

    @Test
    public void getAvailableTableTest() throws NoSuchFieldException, IllegalAccessException {
    }

    @Test
    public void getAvailableColumn() {
    }

    @Test
    public void getUnavailableTable() {
    }

    @Test
    public void getUnavailableColumn() {
    }

    @Test
    public void getInstance() {
    }

    @Test
    public void goBackTest() throws IOException {
        try {
            this.controller.goBackAction(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void viewMyBooks() {
        try {
            this.controller.viewMyBooks(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void handleBorrowButton() {
        try {
            this.controller.handleBorrowButton(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void returnAction() {
        try {
            this.controller.returnAction(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void makeIssueRequest() {
        try {
            this.controller.returnAction(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }
}
