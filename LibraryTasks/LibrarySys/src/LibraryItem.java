

public abstract class LibraryItem {
	
    protected String title;
    protected String itemCode;
    protected int cost;
    protected int borrowCount;
    protected boolean isOnLoan;

    public LibraryItem(String title, String itemCode, int cost, int borrowCount, boolean isOnLoan) {
    	
        this.title = title;
        this.itemCode = itemCode;
        this.cost = cost;
        this.borrowCount = borrowCount;
        this.isOnLoan = isOnLoan;
    }
    
    public String getTitle() {
        return title;
    }

    public String getItemCode() {
        return itemCode;
    }

    public int getCost() {
        return cost;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public boolean isOnLoan() {
        return isOnLoan;
    }

    public void printDetails() {
        System.out.println(" Number of Pages in "+title+ " with item code " + itemCode + " has been borrowed " + borrowCount +
                " times." + " This item is " + (isOnLoan ? "currently on loan." : "available.") + " when new cost " + cost + " pence.");
    }
}
