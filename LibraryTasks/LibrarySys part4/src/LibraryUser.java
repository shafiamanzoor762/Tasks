import java.util.Scanner;

public class LibraryUser {
    private String uId;
    private String surName;
    private String fName;
    private String otherInit;
    private String title_;

    public LibraryUser(String userID, String surname, String firstName, String otherInitials, String title) {
        this.uId = userID;
        this.surName = surname;
        this.fName = firstName;
        this.otherInit = otherInitials;
        this.title_ = title;
    }

    // Accessor methods for each field

    public String getUserID() {
        return uId;
    }
    public String getSurname() {
        return surName;
    }
    public String getFirstName() {
        return fName;
    }
    public String getOtherInitials() {
        return otherInit;
    }
    public String getTitle() {
        return title_;
    }

    public LibraryUser readUserData(Scanner scanner) {
        String userID = scanner.next();
        String surname = scanner.next();
        String firstName = scanner.next();
        String otherInitials = scanner.next();
        String title = scanner.next();

        return new LibraryUser(userID, surname, firstName, otherInitials, title);
    }
    
    public void printDetails() {
        System.out.println("User ID: " + uId);
        System.out.println("Name: " + surName + " " + fName + " " + otherInit + " " + title_);
    }
}
