package Java.uwiJava.javaLabs.javaLab4.src;

class NineToFiver extends LocalResource {

    private int starthour, endhour;
    private int numWorkDays;
    private double hourlyRate;
    private double taxRate;

    public NineToFiver(String dob, String sector,
            int starthour, int endhour, int numWorkDays,
            double hourlyRate, double taxRate) {
        super(dob, sector);
        this.starthour = starthour;
        this.endhour = endhour;
        this.numWorkDays = numWorkDays;
        this.hourlyRate = hourlyRate;
        this.taxRate = taxRate; // added missing assignment

    }

    @Override
    public double getPay() {
        return (1 - taxRate) * hourlyRate * (endhour - starthour) * numWorkDays;
    }

    @Override
    public String getContact() {
        // UNCOMMENT BELOW AFTER IMPLEMENTING LOCALRESOURCE
        return "Local Employee #" + getId();

    }
}
