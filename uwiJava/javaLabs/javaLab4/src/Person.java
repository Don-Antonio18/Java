package Java.uwiJava.javaLabs.javaLab4.src;
import java.text.NumberFormat;
abstract class Person implements Comparable<Person> {
    private int dobYear, dobMonth, dobDay;

    public Person() {
    };

    public Person(int d, int m, int y) {
        dobYear = y;
        dobMonth = m;
        dobDay = d;
    }

    public void setDob(int d, int m, int y) {
        dobYear = y;
        dobMonth = m;
        dobDay = d;
    }

    public int getDobYear() {
        return dobYear;
    }

    public int getDobMonth() {
        return dobMonth;
    }

    public int getDobDay() {
        return dobDay;
    }

    abstract double getPay();

    abstract String getSector();

    abstract String getContact();

    public String toString() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return "[" + getDobDay() + "]" + getContact() + " to be paid " + formatter.format(getPay()) + " in the "
                + getSector() + " sector";
    }

    public int compareTo(Person other) {

        return (this.getDobDay() > other.getDobDay() ? 1 : (this.getDobDay() < other.getDobDay() ? -1 : 0));
    }
}

// To pass test case 2, write class LocalResource which extends Person that
// includes public methods
// **LocalResource(String date, String sector)
// - Note how ExpatConsultant manages the date by splitting it into parts as the
// - Localresource needs to swap the day and month parts
// **getId():Integer - returns the ID of the current instance of LocalResource
// **getSector():String - returns the sector associated with the current
// instance of LocalResource
// getTRN():String-returns 100000000 and the id
// Do you need to store the id and the sector on the LocalResource?
// To calculate the next id, a helper method like getNextId as used in
// ExpatConsultant could be useful
// Note that LocalResource does not need to be instantiated - should you make it
// abstract?

// also to pass Test case 2, Update NineToFiver to properly extend LocalResource
// class
// NOTE NineToFiver is concrete!!!

// In method getContact() of NineToFiver, remove comments so that the method
// returns "Local Employee #"+and the id
