import java.util.Scanner;

public class CD extends AudioVisual{
	
	protected String artist; 
	protected int noOfTrack;
	
    public CD(String artist,int noOfTracks,int playingTime,String bookTitle, 
    		String bookItemCode, int bookCost, int borrowCount, boolean isOnLoan) {
    	
        super(playingTime,bookTitle, bookItemCode, bookCost, borrowCount, isOnLoan);
        this.artist=artist;
        this.noOfTrack=noOfTracks;
        
    }
    public AudioVisual readItemData(Scanner scanner) {
        // Read data specific to CD
        this.artist = scanner.next();
        this.noOfTrack = scanner.nextInt();
        //Read other CD-specific data
        playTime=scanner.nextInt();
        title= scanner.next();
        itemCode = scanner.next();
        cost = scanner.nextInt();
        borrowCount = scanner.nextInt();
        isOnLoan = scanner.nextBoolean();

        return new CD(artist,noOfTrack,playTime,title, itemCode, cost, borrowCount, isOnLoan);     
    }

    @Override
    public void printDetails() {
        super.printDetails();
        System.out.println("Artist name is " + noOfTrack+" and playing Time is " + playTime);
    }
}
