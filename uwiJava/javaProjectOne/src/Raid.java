import java.util.ArrayList;
public class Raid extends Operation {
    public static boolean canDeploy(int numCriminalsAdjusted) {
        Services service = Services.getService();
        return service.policeAvailable(numCriminalsAdjusted);
    }
    
    private int numOfArrests; // Part 1(1) record # of arrests

    public Raid(Community community) {
        super(community);
        ArrayList<Criminal> criminals = community.getCriminals();  
        // Part 1(2) SET # OF ARRESTS TO MATCH # OF CRIMINALS 
        this.numOfArrests = criminals.size();

        // Part 1(5)
        for (Criminal criminal : criminals) {
            criminal.arrest(); 
        }
    }

    // Part 1(3) New Constructor
    public Raid(Community community, int multiplier) {
        super(community);
        Services service = Services.getService();
        ArrayList<Criminal> criminals = community.getCriminals();

        // DEPLOY REQUIRED POLICE
        int requiredPolice = community.countCriminals() * multiplier;
        service.deployPolice(requiredPolice);

        // SET # OF ARRESTS TO MATCH # OF CRIMINALS 
        this.numOfArrests = criminals.size();

        // ARREST ALL CRIMINALS 
        for (Criminal criminal : criminals) {
            criminal.arrest();
        }
    }



    public int countArrests() {
       return numOfArrests; // Part 1(4)
    }

    public String toString() {
        String str = "Operation " + super.getCallSign() + " to be deployed as a Raid in " + cm.getName() +
                ".\nExpect " + countArrests() + " arrest(s).";

        return str;
    }

}
