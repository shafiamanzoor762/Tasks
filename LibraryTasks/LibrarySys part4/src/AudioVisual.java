import java.util.Scanner;

public abstract class AudioVisual extends LibraryItem {
	
	protected int playTime;
    

    public AudioVisual(int playingTime, String title, String itemCode, int cost, int timesBorrowed, boolean onLoan) {
        super(title,itemCode, cost, timesBorrowed, onLoan);
        
        this.playTime=playingTime;
    }

    public int getplayingTime() {
        return playTime;
    }

    @Override
    public void printDetails() {
        System.out.println(" playingTime is " + playTime);
        super.printDetails();
    }

    public abstract AudioVisual readItemData(Scanner scanner);
}
