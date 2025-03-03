
import java.util.Scanner;
public class Person {
    private String firstName;
    private String lastName;
    private int yearsCertified;
    private double netWorth;

    public Person() { // Constructor 1
        this.lastName = "UNKNOWN";
        this.firstName = "UNKNOWN";

    }

    public Person(String fname, String lname, int ycert, double netWorth) {
        this.firstName = fname;
        this.lastName = lname;
        this.yearsCertified = ycert;
        this.netWorth = netWorth;
        netWorth = 0.0;
        //insert code to accept values for, and set instance variables
    }


    private double getHourlyPayRate()
    {
         // return (yearsCertified ^ 3) * (square root of years certified)
         int pow = 3;
         return(Math.pow(yearsCertified, pow)) * (Math.sqrt(yearsCertified));
    }

    public String toString(){
        // returns character at index 0, AKA initial
        return "Name " + firstName.charAt(0) + ". " + lastName + "\n" + "Net Worth: " + 
        netWorth;
    }
    public double getPay(int numHours){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of hours worked: ");
        numHours = scanner.nextInt();
        double pay = (double) numHours * getHourlyPayRate();
        scanner.close();
        return pay;
    }

    public void updateWorthFromWork (int numHours) {
        // calculate pay
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of hours worked: ");
        numHours = scanner.nextInt();
        double pay = (double) numHours * getHourlyPayRate();
        // update networth
        netWorth += pay;
        scanner.close();
    }
}


