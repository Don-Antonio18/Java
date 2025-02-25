import java.util.ArrayList;

public class SOE extends ZOSO {
    private int rehabNum; // PART 4(1)

    /*
     * rehabilitated number is the ( numCriminals * rehab rate ) / 100
     */

    // PART 4(2)
    public static boolean canDeploy(int deploymentSize) {
        Services service = Services.getService();
        return ( service.soldiersAvailable(deploymentSize) &&
                    service.socialAvailable(deploymentSize) &&
                        service.policeAvailable(2) 
        );
    }

    // PART 4(3)

    public SOE(Community community, int multipler, int rehabRate) {
        super(community, multipler);

        // get list of criminals in community
        ArrayList<Criminal> criminals = community.getCriminals(); 
        this.rehabNum = (int) (criminals.size() * rehabRate) / 100;


         for (int i = 0; i < rehabRate; i++) {
             Criminal criminal = criminals.get(i);
             Resident resident = new Resident(criminal.getName(), community);
                criminal.rehabilitate();
         }
    }

    // PART 4(4)
    public int countRehabs(){
        return rehabNum;
    }

    //PART 4(5)
    public String toString() {
        if (rehabNum < 1) 
        {
            String str = "Operation " + super.getCallSign() + "to be deployed as a SOE in " +
        cm.getName() + ".\nExpect " + countArrests() + "arrest(s) and " + 
        countRehabs() + "rehabilitations(s).";
        return str;
        } 
        else 
        {
            String str = "Operation " + super.getCallSign() + "to be deployed as a SOE in " +
        cm.getName() + ".\nExpect " + countArrests() + "arrest(s).";
        return str;
        }

        
        
    }

}


