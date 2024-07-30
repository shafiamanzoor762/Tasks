import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Library {
    private ArrayList<LibraryItem> bList;
    private ArrayList<LibraryUser> userList;

    public Library() {
        this.bList = new ArrayList<>();
        this.userList=new ArrayList<>();
    }
    public void storeData(LibraryItem b) {
    	bList.add(b);
    }
    
    public void storeUser(LibraryUser user) {
        // Check if the user already has a known ID, generate one if it's "unknown"
        if ("unknown".equals(user.getUserID())) {
            user = generateUserID(user);
        }
        userList.add(user);
    }
    private LibraryUser generateUserID(LibraryUser user) {
        // Generate a unique user ID
        // You can use a combination of a prefix and a random number for uniqueness
        // For simplicity, let's use "USR-" as a prefix
        user = new LibraryUser("USR-" + Math.abs(new Random().nextInt()), user.getSurname(), 
        		user.getFirstName(), user.getOtherInitials(), user.getTitle());
        return user;
    }

    public void printAllBooks() {
    	if(bList.isEmpty()) {
    		System.out.println("------------Not contains Items Detail---------");
    	}else {
    	System.out.println("------------All Items Detail:---------");
        for (LibraryItem bk : bList) {
            bk.printDetails();
        }
        }
    }
    public void printAllUsers() {
    	if(userList.isEmpty()) {
    		System.out.println("------------Not contains Users Detail---------");
    	}else {
    	System.out.println("------------All Users Detail:---------");
        for (LibraryUser user : userList) {
            user.printDetails();
        }
        }
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
