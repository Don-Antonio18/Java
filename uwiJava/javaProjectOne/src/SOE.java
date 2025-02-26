import java.util.ArrayList;

public class SOE extends ZOSO {
    private int rehabNum; // PART 4(1)

         // PART 4(3)
    public SOE(Community community, int multiplier, int rehabRate) {
            super(community, multiplier);
            this.rehabNum = (rehabRate * community.countCriminals()) / 100;

            // get deployment service for social worker workers
            Services service = Services.getService();
            service.deploySocial(multiplier * community.countCriminals());

            // get rehabilitations
            this.rehabNum = (int) (community.countCriminals() * rehabRate) / 100;

            for (int i = 0; i < rehabNum && i < community.countCriminals(); i++) {
                // get criminal 
                Criminal criminal = community.getCriminals().get(i);
                // create new resident with criminal's name
                new Resident(criminal.getName(), community);
                // rehabilitate criminal
                criminal.rehabilitate();

    

            }
    }
    
        
    

    // PART 4(2) - renamed to avoid shadowing
    public static boolean canDeploSOE(int deploymentSize) {
        Services service = Services.getService();
        return ( service.soldiersAvailable(deploymentSize) &&
                    service.socialAvailable(deploymentSize) &&
                        service.policeAvailable(2) 
                );
    }
     

    // PART 4(4)
    public int countRehabs(){
        return rehabNum;
    }

    //PART 4(5)
    public String toString() {

        // IF NO REHABILITATIONS
        String str = "Operation " + super.getCallSign() + "to be deployed as a SOE in " +
        cm.getName() + ".\nExpect " + countArrests() + "arrest(s)";

        // IF THERE ARE REHABILITATIONS
        if (rehabNum > 0) {
            str += " and " + countRehabs() + "rehabilitations(s)";
        }

        str += ".";
        return str;
    }

}


