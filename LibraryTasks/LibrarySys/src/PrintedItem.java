import java.util.Scanner;

public abstract class PrintedItem extends LibraryItem {
	protected int noOfPages;
	protected String publisher;
    

    public PrintedItem(int noOfPag, String publisher, String title, String itemCode, int cost, int timesBorrowed, boolean onLoan) {
        super(title,itemCode, cost, timesBorrowed, onLoan);
        
        this.noOfPages=noOfPag;
    	this.publisher=publisher;
    }

    public int getnoOfPag() {
        return noOfPages;
    }
    public String gettitle() {
        return publisher;
    }

    @Override
    public void printDetails() {
        System.out.println(" are " + noOfPages+" publisher is "+publisher);
        super.printDetails();
    }

    public abstract PrintedItem readItemData(Scanner scanner);
}