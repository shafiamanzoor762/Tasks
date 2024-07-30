
import java.util.Scanner;

public class Periodical extends PrintedItem {
	
    private String pubDate;

    public Periodical(String publicationDate,
    		          int noOfPages,
    		          String publisher, 
    		          String title, 
    		          String bookItemCode, 
    		          int bookCost, 
    		          int borrowCount, 
    		          boolean isOnLoan) {
    	
    	super(
    			noOfPages,
    			publisher,
    			title, 
    			bookItemCode, 
    			bookCost, 
    			borrowCount, 
    			isOnLoan
    			);
    	
        this.pubDate = publicationDate;
        
    }

    public PrintedItem readItemData(Scanner scanner) {

        return new Periodical( 
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
        System.out.println("Publication Date is " + pubDate+"\n``````````````````````");
    }
}
