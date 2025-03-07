
public class Services {
    private int numSoldiers;
    private int numEquipment;
    private int numPolice;
    //PART 2(1-2)
    private int numSupplyKits;
    private int numSocial;


    private static Services theService;

    private Services(int numSoldiers, int numEquipment, int numPolice, int numSocial, int numSupplies) {
        this.numSoldiers = numSoldiers;
        this.numEquipment = numEquipment;
        this.numPolice = numPolice;
        this.numSocial = numSocial;


    }

    public static Services getService(int numSoldiers, int numEquipment, int numPolice,
            int numSocial, int numSupplies) {
        if (theService == null)
            theService = new Services(numSoldiers, numEquipment,
                    numPolice, numSocial, numSupplies);

        return theService;
    }

    public static Services getService() {
        return theService;
    }


    //PART 2(3)
    public boolean socialAvailable(int deploymentSize) {
        return ((numSocial >= deploymentSize) && (numSupplyKits >= deploymentSize));
    }

    public boolean policeAvailable(int req) {
        return numPolice > req; // change to >
    }

    public boolean soldiersAvailable(int req) {
        return ((numSoldiers >= req) &&
                (numEquipment >= req));
    }



    public void deployPolice(int numNeeded) {
        numPolice -= numNeeded;
    }

    public void deploySoldiers(int numNeeded) {
        numPolice -= 1;
        numSoldiers -= numNeeded;
        numEquipment -= numNeeded;
    }

    // PART 2(4)
    public void deploySocial(int numNeeded) {
        deployPolice(1);
        numSocial -= numNeeded;
        numSupplyKits -= numNeeded;
        
    }

}
