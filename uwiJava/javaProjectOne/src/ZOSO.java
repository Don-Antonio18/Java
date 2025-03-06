import java.util.ArrayList;

public class ZOSO extends Operation{
    private int numOfArrests; // PART 3(1)


    // PART 3 (2)
    public static boolean canDeploy(int deploymentSize) {
        Services service = Services.getService();
        return service.soldiersAvailable(deploymentSize) && service.policeAvailable(1);
    }

    // PART 3(3)
    public ZOSO(Community community, int multipler) {
            super(community);
            Services service = Services.getService();

            // DEPLOY REQUIRED SOLDIERS
            int requiredSoldiers = community.countCriminals() * multipler;
            service.deploySoldiers(requiredSoldiers);
            service.deployPolice(1);

            // SET # OF ARRESTS TO MATCH # OF CRIMINALS
            this.numOfArrests = community.countCriminals();

            // ARREST ALL CRIMINALS
            for (Criminal criminal: community.getCriminals()) {
                criminal.arrest();
            }
        }

    // PART 3(4)
    public int countArrests() {
        return numOfArrests;
    }
    
    // PART 3(5)
    public String toString() {
        String str = "Operation " + getCallSign() + " to be deployed as a ZOSO in " + cm.getName() +
                ".\nExpect " + countArrests() + " arrest(s).";

        return str;
    }   
       
}
