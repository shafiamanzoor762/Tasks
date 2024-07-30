import java.util.Scanner;

public class DVD extends AudioVisual{
	
	protected String director;
	
    public DVD(String director,int playingTime,String bookTitle, 
    		String bookItemCode, int bookCost, int borrowCount, boolean isOnLoan) {
    	
        super(playingTime,bookTitle, bookItemCode, bookCost, borrowCount, isOnLoan);
        this.director=director;
        
    }
    public AudioVisual readItemData(Scanner scanner) {
        // Read data specific to DVD
        this.director = scanner.next();
        //Read other DVD-specific data
        playingTime=scanner.nextInt();
        Title= scanner.next();
        ItemCode = scanner.next();
        Cost = scanner.nextInt();
        borrowCount = scanner.nextInt();
        isOnLoan = scanner.nextBoolean();

        return new DVD(director,playingTime,Title, ItemCode, Cost, borrowCount, isOnLoan);     
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Director name is " + director+" and playing Time is " + playingTime+"\n``````````````````````");

    }

}
