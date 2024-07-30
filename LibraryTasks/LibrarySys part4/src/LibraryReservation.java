import java.io.PrintWriter;
import java.util.Date;
import java.util.Scanner;

public class LibraryReservation {
	

    private String reservNo;//reservation number
    private String itmCod;//item code
    private String uID;//user ID
    private Date sDate;//start Date
    private int nDays;//number of Days

    public LibraryReservation(String reservNo, String itmCod, String uID, String sDate, int nDays) {
        this.reservNo = reservNo;
        this.itmCod = itmCod;
        this.uID = uID;
        this.sDate = DateUtil.convertStringToDate(sDate);
        this.nDays = nDays;
    }
    
	public LibraryReservation(LibraryUser uID, LibraryItem itmCod, String sDate2, int nDays2) {	
        this.itmCod = itmCod.getItemCode();
        this.uID = uID.getFirstName();
        this.sDate = DateUtil.convertStringToDate(sDate2);
        this.nDays = nDays2;
	}
	
	// Getters and Setters
	public String getReservNo() {
        return reservNo;
    }
	
    public String getItmCod() {
        return itmCod;
    }
    
    public String getUID() {
        return uID;
    }
    
    public Date getSDate() {
        return sDate;
    }
    
    public int getNDays() {
        return nDays;
    }
    
    public LibraryReservation readReservationData(Scanner sc) {
    	//to read reservation required data
        String reservNo = sc.next();
        String itmCod = sc.next();
        String uID = sc.next();
        String sDate = sc.next();
        int nDays = sc.nextInt();

        // LibraryReservation object
        return new LibraryReservation(reservNo, itmCod, uID, sDate, nDays);
    }

    // Write reservation data to a PrintWriter
    public void writeReservationData(PrintWriter wr) {
        
        // Data in comma-separated format
        wr.print("\n"+reservNo + "," + itmCod + "," + uID + "," + DateUtil.convertDateToShortString(sDate) + "," + nDays);
    }


    public String toString() {
        return "Reservation No: " + reservNo + ", User ID: " + uID + ", Item Code: " + itmCod;
    }

}

