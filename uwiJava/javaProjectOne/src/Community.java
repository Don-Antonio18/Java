import java.util.ArrayList;

public class Community {
    private String name;
    private ArrayList<Resident> residents;
    private ArrayList<Criminal> criminals;

    public Community(String name) {
        // initialise instance variables
        this.name = name;
        residents = new ArrayList<Resident>();
        criminals = new ArrayList<Criminal>();

    }

    public int countResidents() {
        return residents.size();
    }

    public int countCriminals() {
        return criminals.size();
    }

    public ArrayList<Criminal> getCriminals() {
        return criminals;
    }

    public Resident getResident(String name) {
        Resident returnVal = null;
        boolean found = false;
        for (int i = 0; (i < residents.size() && (!found)); i++) {
            if (residents.get(i).getName().equals(name))
                returnVal = residents.get(i);
        }
        if (found == false) {
            returnVal = new Resident(name, this);
            residents.add(returnVal);
        }
        return returnVal;
    }

    public Criminal getCriminal(String name) {
        Criminal retval = null;
        boolean found = false;
        for (int i = 0; (i < criminals.size() && (!found)); i++) {
            if (criminals.get(i).getName().equals(name))
                retval = criminals.get(i);
        }
        if (found == false) {
            retval = new Criminal(name);
            criminals.add(retval);
        }
        return retval;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        String str = "=======================\n";
        str += "======" + name + "(" + residents.size() + ")=========\n";
        str += "=======================\n";
        for (Resident r : residents)
            str += r + "\n";
        str += "----------------------\n";
        for (Criminal c : criminals)
            str += c + "\n";
        str += "----------------------\n";

        return str;
    }

}
