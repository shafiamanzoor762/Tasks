import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

public class LibraryReservation {
	

    private String reservationNo;
    private String itemCode;
    private String userID;
    private Date startDate;
    private int noOfDays;

    public LibraryReservation(
    		String reservationNo, 
    		String itemCode, 
    		String userID, 
    		String startDate, 
    		int noOfDays) {
    	
        this.reservationNo = reservationNo;
        this.itemCode = itemCode;
        this.userID = userID;
        this.startDate = DateUtil.convertStringToDate(startDate);
        this.noOfDays = noOfDays;
    }

    // Getters and Setters

	public LibraryReservation(
			LibraryUser user, 
			LibraryItem item, 
			String startDate2, 
			int noOfDays2) {	
		
        this.itemCode = item.getItemCode();
        this.userID = user.getFirstName();
        this.startDate = DateUtil.convertStringToDate(startDate2);
        this.noOfDays = noOfDays2;
	}

	public String getReservationNo() {
        return reservationNo;
    }
    public String getItemCode() {
        return itemCode;
    }
    public String getUserID() {
        return userID;
    }
    public Date getStartDate() {
        return startDate;
    }
    public int getNoOfDays() {
        return noOfDays;
    }
    
    public LibraryReservation readReservationData(Scanner scanner) {

        // Create and return LibraryReservation object
        return new LibraryReservation(
        		scanner.next(), 
        		scanner.next(), 
        		scanner.next(), 
        		scanner.next(), 
        		scanner.nextInt()
        		);
    }

    // Method to write reservation data to a PrintWriter
    public void writeReservationData(PrintWriter writer) {
        
        // Write data in comma-separated format
        writer.print("\n"+reservationNo + "," 
                         + itemCode + "," 
        		         + userID + "," 
                         + DateUtil.convertDateToShortString(startDate) + "," 
        		         + noOfDays);
    }


    public String toString() {
        return "Reservation No: " + reservationNo + 
        		", User ID: " + userID + 
        		", Item Code: " + itemCode;
    }

}

