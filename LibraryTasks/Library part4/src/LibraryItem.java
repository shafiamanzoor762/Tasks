
public class LibraryItem {
	
	
    protected String Title;
    protected String ItemCode;
    protected int Cost;
    protected int borrowCount;
    protected boolean isOnLoan;

    public LibraryItem(String title, String ItemCode, int Cost, int borrowCount, boolean isOnLoan) {
        
    	this.Title=title;
        this.ItemCode = ItemCode;
        this.Cost = Cost;
        this.borrowCount = borrowCount;
        this.isOnLoan = isOnLoan;
    }
    
    public String getBookTitle() {
        return Title;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public int getBookCost() {
        return Cost;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public boolean isOnLoan() {
        return isOnLoan;
    }

    public void printDetails() {
        System.out.println(" Number of Pages in "+Title+ " with item code " + ItemCode + " has been borrowed " + borrowCount +
                " times." + "This item is " + (isOnLoan ? "currently on loan." : "available.") + " when new cost " 
        		+ Cost + " pence.");
    }
}
