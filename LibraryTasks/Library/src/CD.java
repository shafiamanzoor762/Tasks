import java.util.Scanner;

public class CD extends AudioVisual{
	
	protected String artist; 
	protected int noOfTracks;
	
    public CD(String artist,int noOfTracks,int playingTime,String bookTitle, 
    		String bookItemCode, int bookCost, int borrowCount, boolean isOnLoan) {
    	
        super(playingTime,bookTitle, bookItemCode, bookCost, borrowCount, isOnLoan);
        this.artist=artist;
        this.noOfTracks=noOfTracks;
        
    }
    public AudioVisual readItemData(Scanner scanner) {
        // Read data specific to CD
        this.artist = scanner.next();
        this.noOfTracks = scanner.nextInt();
        //Read other CD-specific data
        playingTime=scanner.nextInt();
        Title= scanner.next();
        ItemCode = scanner.next();
        Cost = scanner.nextInt();
        borrowCount = scanner.nextInt();
        isOnLoan = scanner.nextBoolean();

        return new CD(artist,noOfTracks,playingTime,Title, ItemCode, Cost, borrowCount, isOnLoan);     
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Artist name is " + noOfTracks+" and playing Time is " + playingTime+"\n``````````````````````");
    }
}
