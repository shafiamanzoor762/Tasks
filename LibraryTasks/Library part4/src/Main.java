
public class Main {
	public static void main(String[] args) {
        Library lib = new Library();
        lib.readBookData();        
        lib.printAllBooks();
        lib.printAllUsers();
        
        //just to test code
//        lib.makeLibraryReservation("userID1", "itemCode1", "01-03-2024", 3);
//        lib.makeLibraryReservation("userID2", "itemCode2", "05-03-2024", 2);
//        lib.printLibraryReservations("01-03-2024","01-03-2024");
//        lib.writeLibraryReservationData("reservationData");
    }
}
