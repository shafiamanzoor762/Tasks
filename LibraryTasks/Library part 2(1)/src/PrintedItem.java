import java.util.Scanner;

public abstract class PrintedItem extends LibraryItem {
	protected int noOfPag;
	protected String publisher;
    

    public PrintedItem(int noOfPag, String publisher, String title, String itemCode, int cost, int timesBorrowed, 
    		boolean onLoan) {
        super(title,itemCode, cost, timesBorrowed, onLoan);
        
        this.noOfPag=noOfPag;
    	this.publisher=publisher;
    }

    public int getnoOfPag() {
        return noOfPag;
    }
    public String gettitle() {
        return publisher;
    }

    @Override
    public void printDetails() {
        System.out.println(" are " + noOfPag+" publisher is "+publisher);
        super.printDetails();
    }

    public abstract PrintedItem readItemData(Scanner scanner);
}