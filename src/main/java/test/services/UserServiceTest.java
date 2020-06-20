//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package test.services;

import exceptions.UsernameAlreadyExistsException;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import services.UserService;

public class UserServiceTest {
    public UserServiceTest() {
    }

    @BeforeClass
    public static void setupClass() throws IOException {
        FileWriter file = new FileWriter(System.getProperty("user.dir").toString() + "\\src\\main\\resources\\users.json");
        file.write("");
    }

    @Before
    public void setUp() throws IOException {
        FileWriter file = new FileWriter(System.getProperty("user.dir").toString() + "\\src\\main\\resources\\users.json");
        file.write("");
    }

    @Test
    public void loadUsersFromFile() throws IOException {
        FileWriter file = new FileWriter(System.getProperty("user.dir").toString() + "\\src\\main\\resources\\users.json");
        file.write("");
        UserService.loadUsersFromFile();
        Assert.assertNotNull(UserService.getUsers());
        Assert.assertEquals(1, (long)UserService.getUsers().size());
    }

    @Test
    public void testAddOneUser() throws Exception {
        FileWriter file = new FileWriter(System.getProperty("user.dir").toString() + "\\src\\main\\resources\\users.json");
        file.write("");
        UserService.loadUsersFromFile();
        UserService.addUser("test", "testPass", "Student", "", "", "", "1");
        Assert.assertNotNull(UserService.getUsers());
        Assert.assertEquals(2, (long)UserService.getUsers().size());
    }



    @Test
    public void addUser() throws IOException {
    }

    @Test(expected = UsernameAlreadyExistsException.class
    )
    public void testAddUserAlreadyExists() throws Exception {
        UserService.loadUsersFromFile();
        UserService.addUser("test2", "testPass2", "Student", "", "", "", "123");
        Assert.assertNotNull(UserService.getUsers());
        UserService.checkUserDoesNotAlreadyExist("test2");

    }

    @Test
    public void testPasswordEncoding() {
        Assert.assertNotEquals("testPass1", UserService.encodePassword("username1", "testPass1"));
    }

    @Test
    public void encodePassword() {
    }

    @Test
    public void getUsers() {
    }

    @Test
    public void checkIfUserNameOrPasswordAreCorrect() {
    }
}
