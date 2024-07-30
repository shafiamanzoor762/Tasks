import java.util.Scanner;

public class DVD extends AudioVisual{
	
	protected String director_;
	
    public DVD(String director,int playingTime,String bookTitle, 
    		String bookItemCode, int bookCost, int borrowCount, boolean isOnLoan) {
    	
        super(playingTime,bookTitle, bookItemCode, bookCost, borrowCount, isOnLoan);
        this.director_=director;
        
    }
    public AudioVisual readItemData(Scanner scanner) {
        // Read data specific to DVD
        this.director_ = scanner.next();
        //Read other DVD-specific data
        playTime=scanner.nextInt();
        title= scanner.next();
        itemCode = scanner.next();
        cost = scanner.nextInt();
        borrowCount = scanner.nextInt();
        isOnLoan = scanner.nextBoolean();

        return new DVD(director_,playTime,title, itemCode, cost, borrowCount, isOnLoan);     
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Director name is " + director_+" and playing Time is " + playTime);

    }

}
