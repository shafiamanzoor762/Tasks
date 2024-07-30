
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Library {
	
//commented all previous ArrayList operations
	
//    private ArrayList<LibraryItem> itemList;
//    private ArrayList<LibraryUser> uLst;
	
	private Map<String, LibraryItem> itmMap; // ArrayList changed to Map
    private Map<String, LibraryUser> custMap; // ArrayList changed to Map
    private Map<String, LibraryReservation> libReservMap;
    private Diary dir; // Added Diary instance

//    public Library() {
//        this.itemList = new ArrayList<>();
//        this.uLst=new ArrayList<>();
//    }
	
    public Library() {
        itmMap = new HashMap<>();
        custMap = new HashMap<>();
        libReservMap = new HashMap<>();
        dir = new Diary(); // Initialization of Diary instance
    }
    
//    public void addItem(LibraryItem item) {
//        itemList.add(item);
//    }
    
    public void addItem(LibraryItem libItem) {
        itmMap.put(libItem.getItemCode(), libItem); // Using put() for Map
    }
    
//    public void AddUser(LibraryUser user) {
//        // Check if the user already has a known ID, generate one if it's "unknown"
//        if ("unknown".equals(user.getUserID())) {
//            user = generateUserID(user);
//        }
//        uLst.add(user);
//    }

    public void AddUser(LibraryUser u) {
        // Check if the user already has a known ID, if it's "unknown", generate one
        if ("unknown".equals(u.getUserID())) {
            u = generateUserID(u);
        }
        custMap.put(u.getUserID(), u);
    }
    
    private LibraryUser generateUserID(LibraryUser usr) {
        // Generate a unique user ID
        // Can use a combination of a prefix and a random number for uniqueness
        // For simplicity, let's use "USR-" as a prefix
        usr = new LibraryUser("USR-" + Math.abs(new Random().nextInt()), usr.getSurname(), 
        		usr.getFirstName(), usr.getOtherInitials(), usr.getTitle());
        return usr;
    }
    
    // To tackle borrowing of items
    public void borrowItem(LibraryUser u, LibraryItem item, Date sDate, int nDays) {
        LibraryReservation reservation = new LibraryReservation(u, item, DateUtil.convertDateToShortString(sDate), nDays);
        dir.addReservation(reservation);
    }

    // Display reservation b/w specific dates
    public void printLibraryReservations(String sDate, String eDate) {
        dir.printEntries(DateUtil.convertStringToDate(sDate), DateUtil.convertStringToDate(eDate));
    }

//    public void printAllItems() {
//    	if(itemList.isEmpty()) {
//    		System.out.println("\t\t*********No Any Item Exist*********");
//    	} else {
//        	System.out.println("\t\t*********All Items Detail:*********");
//    		for (LibraryItem item : itemList) {
//    			item.printDetails();
//            }
//        }
//    }
//    
    public void printAllItems() {
        if (itmMap.isEmpty()) {
            System.out.println("\\t\\t*********No Any Item Exist*********");
        } else {
            System.out.println("\\t\\t*********All Items Detail:*********");
            for (LibraryItem bk : itmMap.values()) {
                bk.printDetails();
            }
        }
    }

//    public void printAllUsers() {
//    	if(uLst.isEmpty()) {
//    		System.out.println("\t\t*********No Any Users Exist*********");
//    	}else {
//    	System.out.println("\t\t*********All Users Detail:*********");
//        for (LibraryUser user : uLst) {
//            user.printDetails();
//        }
//        }
//    }
    
    public void printAllUsers() {
        if (custMap.isEmpty()) {
            System.out.println("\\t\\t*********No Any Users Exist*********");
        } else {
            System.out.println("\\t\\t*********All Users Detail:*********");
            for (LibraryUser user : custMap.values()) {
                user.printDetails();
            }
        }
    }
    
    public boolean makeLibraryReservation(String uID, String itmCod, String sDate, int nDays) {
        // Check if the item is available for the entire reservation period
        Date reservSDate = DateUtil.convertStringToDate(sDate);
        Date reservEDate = DateUtil.nextDate(reservSDate);

        // Check if the item is already reserved for any day within the reservation period
        for (Date dt = reservSDate; dt.compareTo(reservEDate) <= 0; dt = DateUtil.nextDate(dt)) {
            LibraryReservation[] reservs = dir.getReservations(dt);
            if (reservs != null) {
                for (LibraryReservation reserv : reservs) {
                    if (reserv.getItmCod().equals(itmCod)) {
                        // Item is already reserved
                        System.out.println("Item " + itmCod + " is already reserved for " + dt);
                        return false;
                    }
                }
            }
        }

        // Create LibraryReservation object
        LibraryReservation reserv= new LibraryReservation(generateReservationNo(), itmCod, uID, sDate, nDays);

        // Add reservation to the library and diary
        libReservMap.put(reserv.getReservNo(), reserv);
        dir.addReservation(reserv);

        // Reservation done successful
        return true;
    }

    private String generateReservationNo() {
        // To generate reservation number
        // Simply,Just return a random number as suggested
        return String.valueOf((int) (Math.random() * 1000000));
    }
    
    public void deleteLibraryReservation(String reservNo) {
        LibraryReservation reservation = libReservMap.get(reservNo);
        if (reservation != null) {
            // Remove reservation from library
        	libReservMap.remove(reservNo);
            // Remove reservation from diary
            dir.deleteReservation(reservation);
        } else {
            System.out.println("Reservation with reservation number " + reservNo + " not found.");
        }
    }

    public void printDiaryEntries(String sDate, String eDate) {
        // Convert start Date and end Date to Date objects
        Date start = DateUtil.convertStringToDate(sDate);
        Date end = DateUtil.convertStringToDate(eDate);

        // Print diary entries for the specified period
        dir.printEntries(start, end);
    }
    
    public void readItemData() {
        Frame pf = new Frame();
        FileDialog filedg = new FileDialog(pf, "Please Select item data file");
        filedg.setMode(FileDialog.LOAD);
        filedg.setVisible(true);
        
        //get selected file
        String selectedFile = filedg.getFile();
        if (selectedFile == null) {
            System.out.println("No file selected.");
            return;
        }

        try {
            Scanner sc = new Scanner(new File(filedg.getDirectory() + selectedFile));

            String typeOfData = "";

            while (sc.hasNextLine()) {
                String txtLine = sc.nextLine().trim();

                if (txtLine.startsWith("//") || txtLine.isEmpty()) {
                    continue;
                } else if (txtLine.startsWith("[Book data]")) {
                    typeOfData = "Book";
                } else if (txtLine.startsWith("[periodical data]")) {
                    typeOfData = "Periodical";
                } else if (txtLine.startsWith("[CD data]")) {
                    typeOfData = "CD";
                }else if (txtLine.startsWith("[DVD data]")) {
                    typeOfData = "DVD";
                }else if (txtLine.startsWith("[Library User Data]")) {
                    typeOfData = "Library User";
                }else if (txtLine.startsWith("[Reservation Data]")) {
                	typeOfData = "Reservation Data";
                }else {
                    Scanner lineSc = new Scanner(txtLine);
                    System.out.println("Processing line: " + txtLine);
                    lineSc.useDelimiter(",\\s*");

                    if ("Book".equals(typeOfData) && lineSc.hasNext()) {
                    	
                        Book book = new Book(0, null, null, null, null, null, 0, 0, false);
                        addItem(book.readItemData(lineSc));
                        
                    } if ("Periodical".equals(typeOfData) && lineSc.hasNext()) {
                    	
                        Periodical periodical = new Periodical(null, 0, null, null, null, 0, 0, false );
                        addItem(periodical.readItemData(lineSc));
                    }if ("CD".equals(typeOfData) && lineSc.hasNext()) {
                    	
                        CD cd= new CD(null, 0, 0, null, null, 0, 0, false );
                        addItem(cd.readItemData(lineSc));
                    }if ("DVD".equals(typeOfData) && lineSc.hasNext()) {
                    	
                        DVD dvd = new DVD(null, 0, null, null, 0, 0, false );
                        addItem(dvd.readItemData(lineSc));
                    }if ("Library User".equals(typeOfData) && lineSc.hasNext()) {
                    	
                    	LibraryUser lu= new LibraryUser(null, null, null, null, null );
                    	AddUser(lu.readUserData(lineSc));
                    }if ("Reservation Data".equals(typeOfData) && lineSc.hasNext()) {
                    	
                    	LibraryReservation librev= new LibraryReservation(null, null, null, "01-02-2024", 0);
                    	LibraryReservation lib=librev.readReservationData(lineSc);
//                    	makeLibraryReservation(lib.getUID(),lib.getItmCod(),lib.getSDate()+"",lib.getNDays());
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
    
    public void writeLibraryReservationData(String flName) {
        try {        	
            // Open PrintWriter with append mode if file exists
            PrintWriter wr = new PrintWriter(new FileWriter(flName, Files.exists(Paths.get(flName))));

            // Iterate over the reservations and call writeReservationData for each one
            for (LibraryReservation reservation : libReservMap.values()) {
                reservation.writeReservationData(wr);
            }

            wr.close(); // Closing writer
        } catch (IOException e) {
            System.err.println("Error writing reservation data to file: " + e.getMessage());
        }
    }
}
