
import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Library {

    private ArrayList<LibraryItem> itemList;
    private ArrayList<LibraryUser> uLst;

    public Library() {
        this.itemList = new ArrayList<>();
        this.uLst=new ArrayList<>();
    }

    public void addItem(LibraryItem item) {
        itemList.add(item);
    }
    public void AddUser(LibraryUser user) {
        // Check if the user already has a known ID, generate one if it's "unknown"
        if ("unknown".equals(user.getUserID())) {
            user = generateUserID(user);
        }
        uLst.add(user);
    }
    private LibraryUser generateUserID(LibraryUser user) {
        // Generate a unique user ID
        // You can use a combination of a prefix and a random number for uniqueness
        // For simplicity, let's use "USR-" as a prefix
        user = new LibraryUser("USR-" + Math.abs(new Random().nextInt()), user.getSurname(), 
        		user.getFirstName(), user.getOtherInitials(), user.getTitle());
        return user;
    }

    public void printAllItems() {
    	if(itemList.isEmpty()) {
    		System.out.println("\t\t*********No Any Item Exist*********");
    	} else {
        	System.out.println("\t\t*********All Items Detail:*********");
    		for (LibraryItem item : itemList) {
    			item.printDetails();
            }
        }
    }
    
    public void printAllUsers() {
    	if(uLst.isEmpty()) {
    		System.out.println("\t\t*********No Any Users Exist*********");
    	}else {
    	System.out.println("\t\t*********All Users Detail:*********");
        for (LibraryUser user : uLst) {
            user.printDetails();
        }
        }
    }
    
    public void readItemData() {
        Frame pf = new Frame();
        FileDialog filedg = new FileDialog(pf, "Please Select item data file");
        filedg.setMode(FileDialog.LOAD);
        filedg.setVisible(true);

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
}
