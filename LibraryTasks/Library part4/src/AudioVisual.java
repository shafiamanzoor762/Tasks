import java.util.Scanner;

public abstract class AudioVisual extends LibraryItem {
	
	protected int playingTime;
    

    public AudioVisual(int playingTime, String title, String itemCode, int cost, int timesBorrowed, boolean onLoan) {
        super(title,itemCode, cost, timesBorrowed, onLoan);
        
        this.playingTime=playingTime;
    }

    public int getplayingTime() {
        return playingTime;
    }

    @Override
    public void printDetails() {
        System.out.println(" playingTime is " + playingTime);
        super.printDetails();
    }

    public abstract AudioVisual readItemData(Scanner scanner);
}
