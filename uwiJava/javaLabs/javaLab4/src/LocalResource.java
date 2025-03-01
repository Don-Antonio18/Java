package Java.uwiJava.javaLabs.javaLab4.src;

public class LocalResource extends Person {
    private String sector;
    private String date;

    @Override
    double getPay() {
    }

    @Override
    String getSector() {

    }

    @Override
    String getContact() {

    }
    
    // 3(a)
    public LocalResource(String date, String sector) {
        this.date = "dd/mm/yyyy";
        this.sector = sector;
    }

    // To pass test case 2, write class LocalResource which extends Person that includes public methods
    //  **LocalResource(String date, String sector)
    //     - Note how ExpatConsultant manages the date by splitting it into parts  as the 
    //     - Localresource needs to swap the day and month parts
    //  **getId():Integer - returns the ID of the current instance of LocalResource
    //  **getSector():String - returns the sector associated with the current instance of LocalResource
    //   getTRN():String-returns 100000000 and the id
    //   Do you need to store the id and the sector on the LocalResource?
    //   To calculate the next id, a helper method like getNextId as used in ExpatConsultant could be useful
    //   Note that LocalResource does not need to be instantiated - should you make it abstract?

}
