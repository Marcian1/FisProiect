package services;

import model.Book;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;

public class JSONService
{
    private static ArrayList<Book> books = new ArrayList<Book>();
    private static ArrayList<String> booksToBeIssued = new ArrayList<>();
    public static ArrayList<String> getIssues() { return booksToBeIssued; }

    private static String borrowerUsername;
    private static int borrowingLimit;

    public static String getBorrowerUsername() { return borrowerUsername; }
    public static int getBorrowingLimit() { return borrowingLimit; }

    public static ArrayList<Book> getBooks()
    {
        return books;
    }

    public static void setBorrowerUsername(String borrower)
    {
        borrowerUsername = borrower;
    }
    public static void setBorrowingLimit(int limit) { borrowingLimit = limit; }

    public static void updateBorrowingLimit() { borrowingLimit++; }
    public static void decreaseBorrowingLimit() { borrowingLimit--; }

    public static void writeBookInFile(Book newBook)
    {
        JSONArray bookList = new JSONArray();

        books.add(newBook);

        for(Book b : books)
        {
            //Create the JSON book objet
            JSONObject bookDetails = new JSONObject();
            bookDetails.put("name", b.getName());
            bookDetails.put("type", b.getType());
            bookDetails.put("borrower", b.getBorrower());

            JSONObject bookObject = new JSONObject();
            bookObject.put("book", bookDetails);

            //Add the JSON book object in a JSON Array
            bookList.add(bookObject);
        }

        try(FileWriter file = new FileWriter(System.getProperty("user.dir").toString()+"\\src\\main\\resources\\books.json") )
        {
            file.write(bookList.toJSONString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void writeBookInFile()
    {
        JSONArray bookList = new JSONArray();

        for(Book b : books)
        {
            //Create the JSON book objet
            JSONObject bookDetails = new JSONObject();
            bookDetails.put("name", b.getName());
            bookDetails.put("type", b.getType());
            bookDetails.put("borrower", b.getBorrower());

            JSONObject bookObject = new JSONObject();
            bookObject.put("book", bookDetails);

            //Add the JSON book object in a JSON Array
            bookList.add(bookObject);
        }

        try(FileWriter file = new FileWriter(System.getProperty("user.dir").toString()+"\\src\\main\\resources\\books.json"))
        {
            file.write(bookList.toJSONString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void readBookFromFile()
    {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(System.getProperty("user.dir").toString()+"\\src\\main\\resources\\books.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray bookList = (JSONArray) obj;

            //Iterate over book array
            bookList.forEach( b -> books.add(parseBookObject( (JSONObject) b ) ) );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (EOFException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Book parseBookObject(JSONObject book)
    {
        //Get book object within list
        JSONObject bookObject = (JSONObject) book.get("book");

        //Get book's name, type and borrower
        String Name = (String) bookObject.get("name");
        String Type = (String) bookObject.get("type");
        String Borrower = (String) bookObject.get("borrower");

        Book temp = new Book(Name, Type, Borrower);

        //System.out.println(Name);

        return temp;
    }
    public static void writeIssueInFile()
    {
        JSONArray issueList = new JSONArray();

        for(String s : booksToBeIssued)
        {
            //Create the JSON book objet
            JSONObject issueRequestDetails = new JSONObject();
            issueRequestDetails.put("name", s);

            JSONObject issueRequestObject = new JSONObject();
            issueRequestObject.put("issue", issueRequestDetails);

            //Add the JSON book object in a JSON Array
            issueList.add(issueRequestObject);
        }

        try(FileWriter file = new FileWriter(System.getProperty("user.dir").toString()+"\\src\\main\\resources\\books.json"))
        {
            file.write(issueList.toJSONString());
            file.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
