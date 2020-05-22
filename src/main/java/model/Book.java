package model;

public class Book {
    private String name;
    private String type;
    private String borrower;

    public Book(String name, String type, String borrower){

        this.name = name;
        this.type = type;
        this.borrower = borrower;
    }

    public Book(String name, String type){

        this.name = name;
        this.type = type;
        this.borrower = "";
    }

    public Book(){
        name = "";
        type = "";
        borrower = "";
    }

    public String getName() {
        return name;
    }
    public String getType() { return type; }
    public String getBorrower() { return borrower; }

    public void setType(String type) { this.type = type; }
    public void setBorrower(String borrower) { this.borrower = borrower; }
}
