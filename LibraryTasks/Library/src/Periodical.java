
import java.util.Scanner;

public class Periodical extends PrintedItem {
	
    private String pubDate;

    public Periodical(String publicationDate,int noOfPages,String publisher, String title, String bookItemCode, 
    		int bookCost, int borrowCount, boolean isOnLoan) {
    	
    	super(noOfPages,publisher,title, bookItemCode, bookCost, borrowCount, isOnLoan);
        this.pubDate = publicationDate;
        
    }

    public PrintedItem readItemData(Scanner scanner) {
        // Read data specific to Periodical
        this.pubDate = scanner.next();
     // Read other periodical-specific data
        noOfPag = scanner.nextInt();
        publisher = scanner.next();
        Title = scanner.next();
        ItemCode = scanner.next();
        Cost = scanner.nextInt();
        borrowCount = scanner.nextInt();
        isOnLoan = scanner.nextBoolean();

        return new Periodical( pubDate,noOfPag,publisher,Title, ItemCode, Cost, borrowCount, isOnLoan);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Publication Date is " + pubDate+"\n``````````````````````");
    }
}
