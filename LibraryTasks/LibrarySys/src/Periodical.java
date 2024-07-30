
import java.util.Scanner;

public class Periodical extends PrintedItem {
    private String publicationDate;

    public Periodical(String publicationDate,int noOfPages,String publisher, String title, String bookItemCode, int bookCost, int borrowCount,
                      boolean isOnLoan) {
        super(noOfPages,publisher,title, bookItemCode, bookCost, borrowCount, isOnLoan);
        this.publicationDate = publicationDate;
        
    }

    public Periodical readItemData(Scanner scanner) {
        // Read data specific to Periodical
        this.publicationDate = scanner.next();
     // Read other periodical-specific data
        noOfPages = scanner.nextInt();
        publisher = scanner.next();
        title = scanner.next();
        itemCode = scanner.next();
        cost = scanner.nextInt();
        borrowCount = scanner.nextInt();
        isOnLoan = scanner.nextBoolean();

        return new Periodical( publicationDate,noOfPages,publisher,title, itemCode, cost, borrowCount, isOnLoan);
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("and its Publication Date is " + publicationDate);
    }
}
