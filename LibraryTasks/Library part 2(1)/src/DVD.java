import java.util.Scanner;

public class DVD extends AudioVisual{
	
	protected String director;
	
    public DVD(
    		String director,
    		int playingTime,
    		String bookTitle, 
    		String bookItemCode, 
    		int bookCost, 
    		int borrowCount, 
    		boolean isOnLoan) {
    	
        super(
        		playingTime,
        		bookTitle, 
        		bookItemCode, 
        		bookCost, 
        		borrowCount, 
        		isOnLoan
        		);
        
        this.director=director;
        
    }
    public AudioVisual readItemData(Scanner scanner) {

        return new DVD(
        		scanner.next(),
        		scanner.nextInt(),
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
        System.out.println("Director name is " + director
        		          +" and playing Time is " + playingTime
        		          +"\n``````````````````````");

    }

}
