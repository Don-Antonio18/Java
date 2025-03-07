import java.util.ArrayList;

public class ZOSO extends Operation{
    protected int numOfArrests; // PART 3(1)
   

    // PART 3 (2)
    public static boolean canDeploy(int deploymentSize) {
        Services service = Services.getService();
        return service.soldiersAvailable(deploymentSize) && service.policeAvailable(1);
    }

    // method to arest ciminals
    private void arrestCriminals(Community community) {
        ArrayList<Criminal> criminals = community.getCriminals();  

        for (Criminal criminal : criminals) {
            criminal.arrest(); 
        }
    }

    // PART 3(3)
    public ZOSO(Community community, int multiplier) {
            super(community);
            Services service = Services.getService();

            // DEPLOY REQUIRED SOLDIERS
            int numNeeded = community.countCriminals() * multiplier;
            service.deploySoldiers(numNeeded);
            arrestCriminals(community);
            this.numOfArrests =  numNeeded;
    }

    

    // PART 3(4)
    public int countArrests() {
        return numOfArrests;
    }
    
    // PART 3(5)
    public String toString() {
        String str = "Operation " + super.getCallSign() + " to be deployed as a ZOSO in " + cm.getName() +
                ".\nExpect " + countArrests() + " arrest(s).";

        return str;
    }   
       
}
