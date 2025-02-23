import java.util.ArrayList;
public class Raid extends Operation {
    public static boolean canDeploy(int numCriminalsAdjusted) {
        Services service = Services.getService();
        return service.policeAvailable(numCriminalsAdjusted);
    }
    private int numOfArrests; // Raid #1

    public Raid(Community community) {
        super(community);

    }

    public int countArrests() {
       return numOfArrests; // Raid #2

    }

    public int matchArrests(Community community, int multiplier) {
        this.numOfArrests = cm.getCriminals() * multiplier;
        return this.numOfArrests;
    }

    public String toString() {
        String str = "Operation " + super.getCallSign() + " to be deployed as a Raid in " + cm.getName() +
                ".\nExpect " + countArrests() + " arrest(s).";

        return str;
    }

}
