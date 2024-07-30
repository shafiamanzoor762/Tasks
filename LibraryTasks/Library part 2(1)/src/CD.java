import java.util.Scanner;

public class CD extends AudioVisual{
	
	protected String artist; 
	protected int noOfTracks;
	
    public CD(String artist,int noOfTracks,int playingTime,String bookTitle, 
    		String bookItemCode, int bookCost, int borrowCount, boolean isOnLoan) {
    	
        super(
        		playingTime,
        		bookTitle, 
        		bookItemCode, 
        		bookCost, 
        		borrowCount, 
        		isOnLoan
        		);
        this.artist=artist;
        this.noOfTracks=noOfTracks;
        
    }
    public AudioVisual readItemData(Scanner scanner) {

        return new CD(
        		scanner.next(),
        		scanner.nextInt(),
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
        System.out.println("Artist name is " + noOfTracks+" and playing Time is " + playingTime+"\n``````````````````````");
    }
}
