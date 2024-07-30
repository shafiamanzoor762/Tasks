import java.util.Date;

public class TestClass {
    public static void main(String[] args) {
        
        // Convert two string dates to Date objects
        Date date1 = DateUtil.convertStringToDate("28-02-2024");
        Date date2 = DateUtil.convertStringToDate("06-03-2024");

        // Calculate the number of days between the two dates
        long daysBetween = DateUtil.daysBetween(date1, date2);

        // Print out the number of days between the two dates
        System.out.println("Number of days between the two dates: " + daysBetween);
    }
}
