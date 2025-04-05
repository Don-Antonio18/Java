import java.util.ArrayList;
import java.util.Scanner;

public class InputsAndOutputTesting {    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);

        double cost = 0;
        try {
            cost = Double.parseDouble(scan.nextLine());
        } catch (NumberFormatException e) { //! if input is not double
            System.err.println("Please enter a valid double");
        }

        
        try {
            String abs = String.valueOf(Double.parseDouble(scan.nextLine()));
        } catch (NumberFormatException e) {
            System.err.println("Please enter a valid double");
        }

        int val = 0;
        try {
            val = Integer.parseInt(scan.nextLine());
        } catch (NumberFormatException e) {
            System.err.println("Please enter a valid integer");
        }

        ArrayList<Integer>isbn = new ArrayList<Integer>();   
        try {
            isbn.add(val);
            isbn.get(6);
            int p = (val + 50) / (isbn.get(0).intValue());
        } catch (IndexOutOfBoundsException | ArithmeticException e) {
            System.err.println("No such index exists in this arraylist");
        }

        scan.close();
    }
    
}
 