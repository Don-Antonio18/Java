package Java.uwiJava.javaLabs.javaLab4.src;

public abstract class LocalResource extends Person implements Citizen {
    private String sector;
    private String date;
    private int id;
    private static int nextId;

    // 3(a)
    public LocalResource(String date, String sector) {
        super();
        // Convert dd/mm/yyyy to mm/dd/yyyy
        String[] parts = date.split("/");
        this.date = "dd/mm/yyyy";

        // Swap day and month
        super.setDob(Integer.parseInt(parts[0]),
                Integer.parseInt(parts[1]),
                Integer.parseInt(parts[2]));

        this.sector = sector;
        this.id = getNextId();
    }

    public int getNextId() {
        int currentID = nextId;
        nextId++;
        return currentID;
    }

    // 3(b)
    public int getId() {
        return this.id;
    }

    @Override
    double getPay() {
        return 0.0;
    }

    // 3(c)
    @Override
    String getSector() {
        return this.sector;
    }

    @Override
    String getContact() {
        return "";
    }

    @Override
    public String getTRN() {
        return String.valueOf(100000000 + this.id);
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

}
