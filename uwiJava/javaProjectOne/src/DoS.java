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

    public void assessForOps() {
        double emergencyRatio = 0.3;
        for (Community cm : communities)
        // cm.assessForOp();
        {
            // PART 5(1)
            int numCriminals = cm.countCriminals();
            if (numCriminals > 0) {
                // WHEN CRIMINALS LESS THAN GANG LIMIT
                if (numCriminals < gangLimit) {
                    if (Raid.canDeploy(numCriminals * forceMultiplier))
                        ops.add(new Raid(cm));
                    else if (UnderCover.canDeploy())
                        ops.add(new UnderCover(cm));

                } else {
                    // WHEN CRIMINALS < 30% OF Residents
                    if (numCriminals < (cm.countResidents() * emergencyRatio)) {

                        // IF RESOURCES TO IMPLEMENT ZOSO EXIST
                        if (ZOSO.canDeploy(numCriminals * forceMultiplier)) {
                            // DEPLOY ZOSO
                            ops.add(new ZOSO(cm, forceMultiplier));

                            // IF RECOURSES TO IMPLEMENT ZOSO DO NOT EXIST
                            // check if undercover op can be deployed
                        } else if (UnderCover.canDeploy()) {
                            ops.add(new UnderCover(cm));
                        }
                        // code does nothing
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
                totOps += 1;
            }

        }
        String str = "We expect " + totOps + " operation(s), yielding " + totArrests + " arrest(s)";
        if (totRehabs > 0)
            str += " and " + totRehabs + " rehab(s)";
        str += ".";
        System.out.println(str);
        System.out.println("");

    }

    public void classifiedInformationBrief() {
        System.out.println("=================CLASSIFIED INTERNAL OPERATION BRIEF=============================");
        // Security Officers Internal Brief
        for (int i = 0; i < ops.size(); i++) {
            Operation op = ops.get(i);
            if (op instanceof Raid)
                System.out.println((Raid) op);
            else if (op instanceof UnderCover)
                System.out.println((UnderCover) op);

        }
    }

}
