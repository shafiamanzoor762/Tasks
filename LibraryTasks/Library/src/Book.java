
import java.util.Scanner;

public class Book extends PrintedItem {
	
    private String author;
    private String isbn;
    
    public Book(String publisher,String author, String isbn, int noOfPag, String bookTitle, String bookItemCode, 
    		int bookCost, int borrowCount, boolean isOnLoan) {
    	
        super(noOfPag,publisher,bookTitle, bookItemCode, bookCost, borrowCount, isOnLoan);
        this.author = author;
        this.isbn = isbn;
    }
    
    public PrintedItem readItemData(Scanner scanner) {
        // Read data specific to Book
        this.author = scanner.next();
        this.isbn = scanner.next();
        //Read other book-specific data
        noOfPag=scanner.nextInt();
        publisher = scanner.next();
        Title= scanner.next();
        ItemCode = scanner.next();
        Cost = scanner.nextInt();
        borrowCount = scanner.nextInt();
        isOnLoan = scanner.nextBoolean();

        return new Book( publisher,author, isbn,noOfPag,Title, ItemCode, Cost, borrowCount, isOnLoan);     
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Author name is " + author+" and ISBN number is " + isbn+"\n``````````````````````");
    }
}
