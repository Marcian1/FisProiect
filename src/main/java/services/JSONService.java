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

    public static ArrayList<Book> getBooks()
    {
        return books;
    }

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

}