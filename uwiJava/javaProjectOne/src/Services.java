
public class Services {
    private int numSoldiers;
    private int numEquipment;
    private int numPolice;
    //PART 2(1-2)
    private int numSocialWorkers;
    private int numSupplyKits;


    private static Services theService;

    private Services(int numSoldiers, int numEquipment, int numPolice,
            int numSocial, int numSupplies) {
        this.numSoldiers = numSoldiers;
        this.numEquipment = numEquipment;
        this.numPolice = numPolice;

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

    public boolean policeAvailable(int req) {
        return numPolice >= req;
    }

    public boolean soldiersAvailable(int req) {
        return ((numSoldiers >= req) &&
                (numEquipment >= req));
    }

    //PART 2(3)
    public boolean socialAvailable(int deploymentSize) {
        return (numSocialWorkers > deploymentSize) && (numSupplyKits > deploymentSize);
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
    public void deploySocial(int deploymentSize) {
        numSocialWorkers -= deploymentSize;
        numSupplyKits -= deploymentSize;
        numPolice --;
    }

}
