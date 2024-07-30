import java.util.Scanner;

public class Book extends PrintedItem {
    private String author;
    private String ISBN;
    
    public Book(int noOfPages,String publisher,String author, String isbn, String title, String bookItemCode, int bookCost,
                int borrowCount, boolean isOnLoan) {
        super(noOfPages,publisher,title, bookItemCode, bookCost, borrowCount, isOnLoan);
        
        this.author = author;
        this.ISBN = isbn;
    }
    
    public PrintedItem readItemData(Scanner scanner) {
        // Read data specific to Book
        this.author = scanner.next();
        this.ISBN = scanner.next();
        //Read
        noOfPages=scanner.nextInt();
        publisher = scanner.next();
        title= scanner.next();
        itemCode = scanner.next();
        cost = scanner.nextInt();
        borrowCount = scanner.nextInt();
        isOnLoan = scanner.nextBoolean();

        return new Book(noOfPages, publisher,author, ISBN,title, itemCode, cost, borrowCount, isOnLoan);     
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Author name is " + author+" and ISBN number is " + ISBN);
    }
}
