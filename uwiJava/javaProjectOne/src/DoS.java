import java.io.*;
import java.util.ArrayList;

public class DoS {
    private ArrayList<Community> communities;
    private ArrayList<Operation> ops;

    private Services activeService;
    private int forceMultiplier, gangLimit, rehabRate;

    public DoS(int forceMultiplier, int gangLimit, int rehabRate, int numSoldiers, int numEquipment, int numPolice,
            int numSocial, int numSupplies) {
        activeService = Services.getService(numSoldiers, numEquipment,
                numPolice, numSocial, numSupplies);
        communities = new ArrayList<Community>();
        ops = new ArrayList<Operation>();
        this.forceMultiplier = forceMultiplier;
        this.gangLimit = gangLimit;
        this.rehabRate = rehabRate;
    }

    public Services getService() {
        return activeService;
    }

    public int getForceMultiplier() {
        return forceMultiplier;
    }

    public int getGangLimit() {
        return gangLimit;
    }

    public void takeReport(String report) {

    }

    public String deploymentPlan() {
        return "";

    }

    public Community getCommunity(String cname) {
        Community retval = null;
        for (Community c : communities)
            if (c.getName().equals(cname))
                retval = c;
        if (retval == null) {
            retval = new Community(cname);
            communities.add(retval);
        }
        return retval;
    }
    
    // PART 5(1)
    public void assessForOps() {
        double emergencyRatio = 0.3;
        
        for (Community cm : communities) {
            int numCriminals = cm.countCriminals();
            int numResidents = cm.countResidents();
            int deploySize = numCriminals * forceMultiplier;

            if (numCriminals > 0) {
                // if below gang limit
                if (numCriminals < gangLimit) {
                    if (Raid.canDeploy(deploySize)) {
                        ops.add(new Raid(cm, forceMultiplier));
                    } else if (UnderCover.canDeploy()) {
                        ops.add(new UnderCover(cm));
                    } else if ((emergencyRatio * numResidents) > numCriminals){
                        if (ZOSO.canDeploy(deploySize)) {
                            ops.add(new ZOSO(cm, forceMultiplier));
                        } else if (UnderCover.canDeploy()) {
                            ops.add(new UnderCover(cm));
                        }

                    } else if ((emergencyRatio * numResidents) <= numCriminals){
                        if (SOE.canDeploy(deploySize)){
                            ops.add(new SOE(cm, forceMultiplier, rehabRate));
                        }
                        else if (UnderCover.canDeploy()) {
                            ops.add(new UnderCover(cm));
                        }  
                    }
                }
            }
        }
    }


    // PART 5(2)

    public void publicPolicyReport() {
        // Data for release to politicians.
        System.out.println("=================SUMMARY FOR POLICY MAKERS=============================");
        int totOps = 0;
        int totArrests = 0;
        int totRehabs = 0;
        for (Operation op : ops) {
            if (op instanceof Raid) {
                totArrests += ((Raid) op).countArrests();
                totOps++;
            } else if (op instanceof SOE) {
                totRehabs += ((SOE) op).countRehabs();
            } else if (op instanceof ZOSO && !(op instanceof SOE)) {
                totArrests += ((ZOSO) op).countArrests();
                totOps++;
            }
        }
        String str = "We expect " + totOps + " operation(s), yielding " + totArrests + " arrest(s)";

        if (totRehabs > 0) {
            str += " and " + totRehabs + " rehab(s)";
        }

        str += ".";
        System.out.println(str);
        System.out.println("");
    }

    public void classifiedInformationBrief() {
        System.out.println("=================CLASSIFIED INTERNAL OPERATION BRIEF=============================");
        // Security Officers Internal Brief
        for (int i = 0; i < ops.size(); i++) 
        {
            Operation op = ops.get(i);
            System.out.println(op.toString());

            
        }
    }

}
