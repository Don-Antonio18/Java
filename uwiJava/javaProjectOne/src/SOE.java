import java.util.ArrayList;

public class SOE extends ZOSO {
    private int rehabNum; // PART 4(1)


    // PART 4(2) - renamed to avoid shadowing
    public static boolean canDeploy(int deploymentSize) {
        Services service = Services.getService();
        if (service.soldiersAvailable(deploymentSize) &&
        service.socialAvailable(deploymentSize) &&
            service.policeAvailable(2)) {
                return true;
        } else {
            return false;
        }
           

    }

         // PART 4(3)
    public SOE(Community community, int multiplier, int rehabRate) {
            super(community, multiplier);

            // get deployment service for social worker workers
            Services service = Services.getService();
            service.deploySocial(multiplier * community.countCriminals());
            service.deployPolice(1);

            // get rehabilitations
            this.rehabNum = (community.countCriminals() * rehabRate) / 100;

            ArrayList<Criminal> criminals = community.getCriminals();
            for (int i = 0; i < rehabNum && i < criminals.size(); i++) {
                // get criminal 
                Criminal criminal = criminals.get(i);
                // create new resident with criminal's name
                new Resident(criminal.getName(), community);
                // rehabilitate criminal
                criminal.rehabilitate();
                // remove criminal
                criminals.remove(criminal);
            }
    }
    
        
    
     

    // PART 4(4)
    public int countRehabs(){
        return rehabNum;
    }

    //PART 4(5)
    public String toString() {

        // IF NO REHABILITATIONS
        String str = "Operation " + super.getCallSign() + " to be deployed as a SOE in " + cm.getName() + ".\nExpect " + countArrests() + " arrest(s)";

        if (rehabNum > 0) {
            str += " and " + countRehabs() + " rehabilitation(s)";
        }
        
        str += ".";
        return str;
    }

}


