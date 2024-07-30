import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;
//import java.util.ArrayList;

public class Library {

    private Map<String, LibraryReservation> libraryReservationMap;
    private Map<String, LibraryItem> libraryitemsMap; // Changed to Map
    private Map<String, LibraryUser> librarycustomerMap; // Changed to Map
    private Diary diary; // Added Diary instance
    
//    private ArrayList<LibraryItem> bList;
//    private ArrayList<LibraryUser> userList;	
	
//    public Library() {
//        this.bList = new ArrayList<>();
//        this.userList=new ArrayList<>();
//    }
     
    public Library() {
        libraryitemsMap = new HashMap<>(); // Changed to HashMap
        librarycustomerMap = new HashMap<>();
        libraryReservationMap = new HashMap<>();
        diary = new Diary(); // Initialize
    }
    
//  public void storeData(LibraryItem b) {
//	bList.add(b);
//}
   
//    public void printAllBooks() {
//    	if(bList.isEmpty()) {
//    		System.out.println("------------No Data Items Found---------");
//    	}else {
//    	System.out.println("------------All Items Detail:---------");
//        for (LibraryItem bk : bList) {
//            bk.printDetails();
//        }
//        }
//    }
    
      public void storeData(LibraryItem libraryItem) {
    	  // Using put() for Map
        libraryitemsMap.put(libraryItem.getItemCode(), libraryItem);
      }
        
        public void printAllBooks() {
            if (libraryitemsMap.isEmpty()) {
                System.out.println("------------No Data Items Found---------");
            } else {
                System.out.println("------------All Items Detail:---------");
                for (LibraryItem bk : libraryitemsMap.values()) {
                    bk.printDetails();
                }
            }
        }       

        // Handle borrowing
        public void borrowItem(LibraryUser user, LibraryItem item, Date startDate, int noOfDays) {
        	
            LibraryReservation reservation = new LibraryReservation(user, item, 
            		DateUtil.convertDateToShortString(startDate), noOfDays);
            
            diary.addReservation(reservation);
        }


        private String generateReservationNo() {
        	
            // Logic to generate reservation number, Return a random number
            return String.valueOf((int) (Math.random() * 1000000));
        }
        
        public boolean makeLibraryReservation(String userID, String itemCode, String startDate, int noOfDays) {
        	
            for (Date date = DateUtil.convertStringToDate(startDate); 
            		date.compareTo(DateUtil.nextDate(DateUtil.convertStringToDate(startDate))) <= 0; 
            		date = DateUtil.nextDate(date)
            				) {                
                if (diary.getReservations(date) != null) {
                	
                    for (LibraryReservation reservation : diary.getReservations(date)) {
                    	
                        if (reservation.getItemCode().equals(itemCode)) {
                        	
                            // Already reserved
                            System.out.println("Item " + itemCode + " is already reserved for " + date);
                            return false;
                        }
                    }
                }
            }

            // Reservation number
            String reservationNo = generateReservationNo();

            // Add reservation
            diary.addReservation(new LibraryReservation(
            				reservationNo, itemCode, userID, startDate, noOfDays
            				)
            		);
            libraryReservationMap.put(reservationNo, 
            		new LibraryReservation(reservationNo, itemCode, userID, 
            		                    startDate, noOfDays
            		                    )
            		);            

            // Reservation done successful
            return true;
        }
        
        // Print reservations
        public void printLibraryReservations(String startDate, String endDate) {
        	
            diary.printEntries(
            		DateUtil.convertStringToDate(startDate), 
            		DateUtil.convertStringToDate(endDate)
            		);
        }
        
        public void printDiaryEntries(String startDate, String endDate) {      	
            
            // Print diary entries by converting startDate and endDate to Date objects
            diary.printEntries(
            		DateUtil.convertStringToDate(startDate),
            		DateUtil.convertStringToDate(endDate)
            		);
        }
        
     // Remove reservation
        public void deleteLibraryReservation(String reservationNo) {
            
            if (libraryReservationMap.get(reservationNo) != null) {
                 //From library
                libraryReservationMap.remove(reservationNo);
                // From diary
                diary.deleteReservation(libraryReservationMap.get(reservationNo));
            } else {
                System.out.println("Reservation with reservation number " 
                                    + reservationNo + 
                                    " not found.");
            }
        }
        
        private LibraryUser generateUserUniqueID(LibraryUser user) {
        	
            // Generate a unique user ID, I use "USR-" as a prefix
            return new LibraryUser(
            		"USR-" + Math.abs(new Random().nextInt()), 
            		user.getSurname(), 
            		user.getFirstName(), 
            		user.getOtherInitials(), 
            		user.getTitle()
            		);
        }
        
//      public void storeUser(LibraryUser user) {
//      // Check if the user already has a known ID, generate one if it's "unknown"
//      if ("unknown".equals(user.getUserID())) {
//          user = generateUserID(user);
//      }
//      userList.add(user);
//  }
        
//      public void printAllUsers() {
//    	if(userList.isEmpty()) {
//    		System.out.println("------------Not contains Users Detail---------");
//    	}else {
//    	System.out.println("------------All Users Detail:---------");
//        for (LibraryUser user : userList) {
//            user.printDetails();
//        }
//        }
//    }
        
        public void storeUser(LibraryUser user) {
        	
            if ("unknown".equals(user.getUserID())) {
                user = generateUserUniqueID(user);
            }
            librarycustomerMap.put(user.getUserID(), user);
        }
        
        public void printAllUsers() {
        	
            if (librarycustomerMap.isEmpty()) {
                System.out.println("------------Not contains Users Detail---------");
            } else {
                System.out.println("------------All Users Detail:---------");
                for (LibraryUser user : librarycustomerMap.values()) {
                    user.printDetails();
                }
            }
        }
        
      public void readBookData() {
    	  
        // FileDialog object with the parent frame
        FileDialog fldg= new FileDialog(new Frame(), "Please Select book data file");
        fldg.setMode(FileDialog.LOAD);
        fldg.setVisible(true);

        // Get the selected file
        String selFile = fldg.getFile();
        if (selFile == null) {
            System.out.println("No Any file selected.");
            return;
        }

        try {
            // Set up scanner to read selected file
            Scanner sc = new Scanner(new File(fldg.getDirectory() + selFile));

            String Datatype = "";

            while (sc.hasNextLine()) {
                String lineText = sc.nextLine().trim();

                if (lineText.startsWith("//") || lineText.isEmpty()) {
                    continue;
                }
                
                else if (lineText.startsWith("[Book data]")) {
                    Datatype = "Book";
                }
                
                else if (lineText.startsWith("[periodical data]")) {
                    Datatype = "Periodical";
                } 
                
                else if (lineText.startsWith("[CD data]")) {
                    Datatype = "CD";
                }
                
                else if (lineText.startsWith("[DVD data]")) {
                    Datatype = "DVD";
                }
                
                else if (lineText.startsWith("[Library User Data]")) {
                    Datatype = "Library User";
                }
                
                else if (lineText.startsWith("[Reservation Data]")) {
                    Datatype = "Reservation Data";
                }
                
                else {
                    Scanner lineSc = new Scanner(lineText);
                    lineSc.useDelimiter(",\\s*");

                    if ("Book".equals(Datatype) && lineSc.hasNext()) {                    	
                        Book book = new Book(null, null, 0, null, null, null, 0, 0, false);
                        storeData(book.readItemData(lineSc));
                        
                    } 
                    
                    if ("Periodical".equals(Datatype) && lineSc.hasNext()) {                    	
                        Periodical periodical = new Periodical(null, 0, null, null, null, 0, 0, false );
                        storeData(periodical.readItemData(lineSc));
                    }
                    
                    if ("CD".equals(Datatype) && lineSc.hasNext()) {                    	
                        CD cd= new CD(null, 0, 0, null, null, 0, 0, false );
                        storeData(cd.readItemData(lineSc));
                    }
                    
                    if ("DVD".equals(Datatype) && lineSc.hasNext()) {                    	
                        DVD dvd = new DVD(null, 0, null, null, 0, 0, false );
                        storeData(dvd.readItemData(lineSc));
                    }
                    
                    if ("Library User".equals(Datatype) && lineSc.hasNext()) {                    	
                    	LibraryUser lu= new LibraryUser(null, null, null, null, null );
                    	storeUser(lu.readUserData(lineSc));
                    }
                    
                    if ("Reservation Data".equals(Datatype) && lineSc.hasNext()) {                    	
                    	LibraryReservation lr= new LibraryReservation(null, null, null, "05-03-2024", 0);
                    	LibraryReservation l=lr.readReservationData(lineSc);
//                    	makeLibraryReservation(l.getUserID(),l.getItemCode(),l.getStartDate()+"",l.getNoOfDays());
                    }
                    
                    lineSc.close();
                }
            }
            sc.close(); //Close Scanner
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error reading data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void writeLibraryReservationData(String fileName) {
        try {
            // Open PrintWriter with append mode if file exists
            PrintWriter writer = new PrintWriter(
            		new FileWriter(fileName, 
            		Files.exists(Paths.get(fileName)
            				)
            		    )
            		);

            // Iterate over the reservations and call writeReservationData for each one
            for (LibraryReservation reservation : libraryReservationMap.values()) {
                reservation.writeReservationData(writer);
            }

            writer.close(); // Closing writer
        } catch (IOException e) {
            System.err.println("Error While writing reservation data: " + e.getMessage());
        }
    }
}
