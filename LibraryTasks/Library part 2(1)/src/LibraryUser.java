import java.io.PrintWriter;
import java.util.Scanner;

public class LibraryUser {
    private String userID;
    private String surname;
    private String firstName;
    private String otherInitials;
    private String title;

    public LibraryUser(
    		String userID, 
    		String surname, 
    		String firstName, 
    		String otherInitials, 
    		String title) {
    	
        this.userID = userID;
        this.surname = surname;
        this.firstName = firstName;
        this.otherInitials = otherInitials;
        this.title = title;
    }

    // Accessor methods for each field
    
    public String getUserID() {
        return userID;
    }
    public String getSurname() {
        return surname;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getOtherInitials() {
        return otherInitials;
    }
    public String getTitle() {
        return title;
    }

    public LibraryUser readUserData(Scanner scanner) {
    	
        return new LibraryUser(
        		scanner.next(), 
        		scanner.next(), 
        		scanner.next(), 
        		scanner.next(), 
        		scanner.next()
        		);
    }
    
    public void writeUserData(PrintWriter writer) {
        
        // Write data in comma-separated format
        writer.println(
        		  userID +","
                + surname + "," 
        		+ firstName + "," 
                + otherInitials + "," 
        		+ title
        		);
    }
    
    public void printDetails() {
        System.out.println("User ID: " + userID);
        System.out.println(
        		             "Name: " + surname 
        		           + "First Name " + firstName 
        		           + "Other Initials " + otherInitials 
        		           + "Title " + title
        		           );
    }
}
