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

//
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

public class Library {
//    private ArrayList<LibraryItem> bList;
//    private ArrayList<LibraryUser> userList;
//
	
    private Map<String, LibraryItem> itemsMap; // Changed to Map
    private Map<String, LibraryUser> customerMap; // Changed to Map
    private Map<String, LibraryReservation> libraryReservationMap;
    private Diary diary; // Added Diary instance

	
//    public Library() {
//        this.bList = new ArrayList<>();
//        this.userList=new ArrayList<>();
//    }
    
    
    public Library() {
        itemsMap = new HashMap<>(); // Changed to HashMap
        customerMap = new HashMap<>(); // Changed to HashMap
        libraryReservationMap = new HashMap<>();
        diary = new Diary(); // Initialize Diary
    }
//  public void storeData(LibraryItem b) {
//	bList.add(b);
//}
//
//    public void storeUser(LibraryUser user) {
//        // Check if the user already has a known ID, generate one if it's "unknown"
//        if ("unknown".equals(user.getUserID())) {
//            user = generateUserID(user);
//        }
//        userList.add(user);
//    }


        public void storeData(LibraryItem libraryItem) {
            itemsMap.put(libraryItem.getItemCode(), libraryItem); // Using put() for Map
        }

        public void storeUser(LibraryUser user) {
            // Check if the user already has a known ID, generate one if it's "unknown"
            if ("unknown".equals(user.getUserID())) {
                user = generateUserID(user);
            }
            customerMap.put(user.getUserID(), user);
        }
        
        private LibraryUser generateUserID(LibraryUser user) {
            // Generate a unique user ID
            // For simplicity, I use "USR-" as a prefix
            user = new LibraryUser("USR-" + Math.abs(new Random().nextInt()), user.getSurname(), 
            		user.getFirstName(), user.getOtherInitials(), user.getTitle());
            return user;
        }

        // Method to handle borrowing of items by users
        public void borrowItem(LibraryUser user, LibraryItem item, Date startDate, int noOfDays) {
            LibraryReservation reservation = new LibraryReservation(user, item, DateUtil.convertDateToShortString(startDate), noOfDays);
            diary.addReservation(reservation);
        }

        // Method to print reservations between specified dates
        public void printLibraryReservations(String startDate, String endDate) {
            diary.printEntries(DateUtil.convertStringToDate(startDate), DateUtil.convertStringToDate(endDate));
        }

//    public void printAllBooks() {
//    	if(bList.isEmpty()) {
//    		System.out.println("------------Not contains Items Detail---------");
//    	}else {
//    	System.out.println("------------All Items Detail:---------");
//        for (LibraryItem bk : bList) {
//            bk.printDetails();
//        }
//        }
//    }
//    public void printAllUsers() {
//    	if(userList.isEmpty()) {
//    		System.out.println("------------Not contains Users Detail---------");
//    	}else {
//    	System.out.println("------------All Users Detail:---------");
//        for (LibraryUser user : userList) {
//            user.printDetails();
//        }
//        }
//    }
        
        public void printAllBooks() {
            if (itemsMap.isEmpty()) {
                System.out.println("------------Not contains Items Detail---------");
            } else {
                System.out.println("------------All Items Detail:---------");
                for (LibraryItem bk : itemsMap.values()) {
                    bk.printDetails();
                }
            }
        }

        public void printAllUsers() {
            if (customerMap.isEmpty()) {
                System.out.println("------------Not contains Users Detail---------");
            } else {
                System.out.println("------------All Users Detail:---------");
                for (LibraryUser user : customerMap.values()) {
                    user.printDetails();
                }
            }
        }

        public boolean makeLibraryReservation(String userID, String itemCode, String startDate, int noOfDays) {
            // Check if the item is available for the entire reservation period
            Date reservationStartDate = DateUtil.convertStringToDate(startDate);
            Date reservationEndDate = DateUtil.nextDate(reservationStartDate);

            // Check if the item is already reserved for any day within the reservation period
            for (Date date = reservationStartDate; date.compareTo(reservationEndDate) <= 0; date = DateUtil.nextDate(date)) {
                LibraryReservation[] reservations = diary.getReservations(date);
                if (reservations != null) {
                    for (LibraryReservation reservation : reservations) {
                        if (reservation.getItemCode().equals(itemCode)) {
                            // Item is already reserved for this period
                            System.out.println("Item " + itemCode + " is already reserved for " + date);
                            return false;
                        }
                    }
                }
            }

            // Generate reservation number
            String reservationNo = generateReservationNo();

            // Create LibraryReservation object
            LibraryReservation reservation = new LibraryReservation(reservationNo, itemCode, userID, startDate, noOfDays);

            // Add reservation to the library and diary
            libraryReservationMap.put(reservationNo, reservation);
            diary.addReservation(reservation);

            // Reservation successful
            return true;
        }

        private String generateReservationNo() {
            // Logic to generate reservation number
            // Example: padding with zeros
            // For simplicity, let's just return a random number as suggested
            return String.valueOf((int) (Math.random() * 1000000));
        }
        
        public void deleteLibraryReservation(String reservationNo) {
            LibraryReservation reservation = libraryReservationMap.get(reservationNo);
            if (reservation != null) {
                // Remove reservation from library
                libraryReservationMap.remove(reservationNo);
                // Remove reservation from diary
                diary.deleteReservation(reservation);
            } else {
                System.out.println("Reservation with reservation number " + reservationNo + " not found.");
            }
        }

        public void printDiaryEntries(String startDate, String endDate) {
            // Convert startDate and endDate to Date objects
            Date start = DateUtil.convertStringToDate(startDate);
            Date end = DateUtil.convertStringToDate(endDate);

            // Print diary entries for the specified period
            diary.printEntries(start, end);
        }
        
      public void readBookData() {
        // Parent frame for the file dialog
        Frame pf = new Frame();

        // FileDialog object with the parent frame
        FileDialog filedg= new FileDialog(pf, "Please Select book data file");
        filedg.setMode(FileDialog.LOAD);
        filedg.setVisible(true);

        // Get the selected file
        String selectedFile = filedg.getFile();
        if (selectedFile == null) {
            System.out.println("No Any file selected.");
            return;
        }

        try {
            // Set up scanner to read selected file
            Scanner sc = new Scanner(new File(filedg.getDirectory() + selectedFile));

            String Datatype = "";

            while (sc.hasNextLine()) {
                String lineText = sc.nextLine().trim();

                if (lineText.startsWith("//") || lineText.isEmpty()) {
                    continue;
                } else if (lineText.startsWith("[Book data]")) {
                    Datatype = "Book";
                } else if (lineText.startsWith("[periodical data]")) {
                    Datatype = "Periodical";
                } else if (lineText.startsWith("[CD data]")) {
                    Datatype = "CD";
                }else if (lineText.startsWith("[DVD data]")) {
                    Datatype = "DVD";
                }else if (lineText.startsWith("[Library User Data]")) {
                    Datatype = "Library User";
                }else if (lineText.startsWith("[Reservation Data]")) {
                    Datatype = "Reservation Data";
                }else {
                    Scanner lineSc = new Scanner(lineText);
                    System.out.println("Processing line: " + lineText);
                    lineSc.useDelimiter(",\\s*");

                    if ("Book".equals(Datatype) && lineSc.hasNext()) {
                    	
                        Book book = new Book(null, null, null, 0, null, null, 0, 0, false);
                        storeData(book.readItemData(lineSc));
                        
                    } if ("Periodical".equals(Datatype) && lineSc.hasNext()) {
                    	
                        Periodical periodical = new Periodical(null, 0, null, null, null, 0, 0, false );
                        storeData(periodical.readItemData(lineSc));
                    }if ("CD".equals(Datatype) && lineSc.hasNext()) {
                    	
                        CD cd= new CD(null, 0, 0, null, null, 0, 0, false );
                        storeData(cd.readItemData(lineSc));
                    }if ("DVD".equals(Datatype) && lineSc.hasNext()) {
                    	
                        DVD dvd = new DVD(null, 0, null, null, 0, 0, false );
                        storeData(dvd.readItemData(lineSc));
                    }if ("Library User".equals(Datatype) && lineSc.hasNext()) {
                    	
                    	LibraryUser lu= new LibraryUser(null, null, null, null, null );
                    	storeUser(lu.readUserData(lineSc));
                    }if ("Reservation Data".equals(Datatype) && lineSc.hasNext()) {
                    	
                    	LibraryReservation lr= new LibraryReservation(null, null, null, "05-03-2024", 0);
                    	LibraryReservation l=lr.readReservationData(lineSc);
//                    	makeLibraryReservation(l.getUserID(),l.getItemCode(),l.getStartDate()+"",l.getNoOfDays());
                    }
                    
                    lineSc.close();
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error reading data: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public void writeLibraryReservationData(String fileName) {
        try {
            // Check if the file exists
            boolean fileExists = Files.exists(Paths.get(fileName));

            // Open PrintWriter with append mode if file exists
            PrintWriter writer = new PrintWriter(new FileWriter(fileName, fileExists));

            // Iterate over the reservations and call writeReservationData for each one
            for (LibraryReservation reservation : libraryReservationMap.values()) {
                reservation.writeReservationData(writer);
            }

            writer.close(); // Close the writer after writing data
        } catch (IOException e) {
            System.err.println("Error writing reservation data to file: " + e.getMessage());
        }
    }

}
