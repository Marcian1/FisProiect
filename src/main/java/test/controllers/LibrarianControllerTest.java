//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package test.controllers;

import controllers.LibrarianController;
import java.io.IOException;
import javafx.event.ActionEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LibrarianControllerTest {
    LibrarianController controller;
    ActionEvent event = new ActionEvent();

    public LibrarianControllerTest() {
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getUnavailableTable() {
    }

    @Test
    public void getUnavailableColumn() {
    }

    @Test
    public void getIssuedTable() {
    }

    @Test
    public void getIssuedColumn() {
    }

    @Test
    public void getAvailableTable() {
    }

    @Test
    public void getAvailableColumn() {
    }

    @Test
    public void getInstance() {
    }

    @Test
    public void makeBookList() {
    }

    @Test
    public void goBackAction() throws IOException {
        try {
            this.controller.goBackAction(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void add() throws IOException {
        try {
            this.controller.add(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void handleIssueAction() {
        try {
            this.controller.handleIssueAction(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }

    @Test
    public void viewIssuesAction() {
        try {
            this.controller.viewIssuesAction(this.event);
            Assert.fail("Should have thrown an exception!");
        } catch (RuntimeException var2) {
            Assert.assertTrue(true);
        }

    }
}
