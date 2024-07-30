import java.util.Date;

public class TestDateClass {
    public static void main(String[] args) {
        
        // Convert two string dates to Date objects
        Date dt1 = DateUtil.convertStringToDate("20-02-2024");
        Date dt2 = DateUtil.convertStringToDate("10-03-2024");

        // Calculate the number of days between the two dates
        long daysBtw = DateUtil.daysBetween(dt1, dt2);

        // Print out the number of days between the two dates
        System.out.println("Number of days between the two dates: " + daysBtw);
    }
}
