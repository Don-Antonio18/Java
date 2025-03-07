import java.util.ArrayList;

public class SOE extends ZOSO {
    private int rehabNum; // PART 4(1)
    private int numSocial;
    private int numSupplies;


    // PART 4(2) - renamed to avoid shadowing
    public static boolean canDeploy(int deploymentSize) {
        Services service = Services.getService();

        return (service.soldiersAvailable(deploymentSize) &&
        service.socialAvailable(deploymentSize) &&
            service.policeAvailable(2)) ;
    }
        
           


         // PART 4(3)
    public SOE(Community community, int multiplier, int rehabRate) {
            super(community, multiplier);

            Services service = Services.getService();
            int deploySize = community.countCriminals() * multiplier;
            service.deploySocial(deploySize);
            numSocial += deploySize;
            numSupplies += deploySize;
            service.deployPolice(1);

            // get rehabilitations
            rehabNum = (community.countCriminals() * rehabRate) / 100;

            ArrayList<Criminal> criminals = community.getCriminals();
            for (int i = 0; i < rehabNum && i < criminals.size(); i++) {
                // get criminal 
                Criminal criminal = criminals.get(i);
                // create new resident with criminal's name
                new Resident(criminal.getName(), community);
                // rehabilitate criminal
                criminal.rehabilitate();
                // remove criminal
                community.getCriminals().remove(criminal);
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
            str += " and " + rehabNum + " rehabilitation(s)";
        }
        
        str += ".";
        return str;
    }

}


