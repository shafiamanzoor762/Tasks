public class LibraryItem {
    private String bookTitle;
    private String bookItemCode;
    private int bookCost;
    private int borrowCount;
    private boolean isOnLoan;

    public LibraryItem(String bookTitle, String bookItemCode, int bookCost, int borrowCount, boolean isOnLoan) {
        this.bookTitle = bookTitle;
        this.bookItemCode = bookItemCode;
        this.bookCost = bookCost;
        this.borrowCount = borrowCount;
        this.isOnLoan = isOnLoan;
    }
    
    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookItemCode() {
        return bookItemCode;
    }

    public int getBookCost() {
        return bookCost;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    public boolean isOnLoan() {
        return isOnLoan;
    }

    public void printDetails() {
        System.out.println(bookTitle + " with item code " + bookItemCode + " has been borrowed " + borrowCount +
                " times." + "This item is " + (isOnLoan ? "currently on loan." : "available.") + " when new cost " + bookCost + " pence."+"\n``````````````````````");
    }
}
