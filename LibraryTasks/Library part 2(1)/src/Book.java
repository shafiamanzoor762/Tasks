
import java.util.Scanner;

public class Book extends PrintedItem {
	
    private String author;
    private String isbn;
    
    public Book(
    		
    		String author, 
    		String isbn, 
    		int noOfPag,
    		String publisher,
    		String bookTitle, 
    		String bookItemCode, 
    		int bookCost, 
    		int borrowCount, 
    		boolean isOnLoan) {
    	
        super(
        		noOfPag,
        		publisher,
        		bookTitle, 
        		bookItemCode, 
        		bookCost, 
        		borrowCount, 
        		isOnLoan
        		);
        
        this.author = author;
        this.isbn = isbn;
    }
    
    public PrintedItem readItemData(Scanner scanner) {

        return new Book( 
        		
        		scanner.next(), 
        		scanner.next(),
        		scanner.nextInt(),
        		scanner.next(),
        		scanner.next(), 
        		scanner.next(), 
        		scanner.nextInt(), 
        		scanner.nextInt(), 
        		scanner.nextBoolean()
        		);     
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Author name is " + author
        		          +" and ISBN number is " + isbn+"\n``````````````````````");
    }
}
