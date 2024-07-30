import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Library {
    private ArrayList<LibraryItem> bList;

    public Library() {
        this.bList = new ArrayList<>();
    }
    public void storeBook(LibraryItem b) {
    	bList.add(b);
    }

    public void printAllBooks() {
        for (LibraryItem bk : bList) {
            bk.printDetails();
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

            while (sc.hasNextLine()) {
                String txtLine = sc.nextLine().trim(); // Remove leading/trailing spaces
                if (!txtLine.startsWith("//") && !txtLine.isEmpty()) {
                    // Process real data
                    System.out.println(txtLine+"\n____________________"); // Output the line to check

                    // Split the line into tokens
                    Scanner lineSc = new Scanner(txtLine);
                    lineSc.useDelimiter(",");

                    // Read data for each field
                    String title = lineSc.next();
                    String bookCode = lineSc.next();
                    int bookCost = lineSc.nextInt();
                    int borrowCount = lineSc.nextInt();
                    boolean isOnLoan = lineSc.nextBoolean();

                    // Create LibraryItem object and store it
                    LibraryItem bk = new LibraryItem(title, bookCode, bookCost, borrowCount, isOnLoan);
                    storeBook(bk);

                    // Close line scanner
                    lineSc.close();
                }
            }

            // Close main scanner
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Library lib= new Library();
        lib.readBookData();
        System.out.println("------------All Books Detail:---------");
        lib.printAllBooks();
    }
}
