package Java.uwiJava.javaLabs.javaLab4.src;

public class LocalConsultant extends LocalResource implements Consultant {
    // LocalConsultant implements Citizen through LocalResource

    private double skillPrice;
    private double permitTax;

    public LocalConsultant(String dob, String sector, double skillPrice, double taxRate) {
        super(dob, sector);
        this.skillPrice = skillPrice;
        this.permitTax = taxRate * skillPrice;
    }

    @Override
    public double earnFromSkill() {
        return skillPrice;
    }

    @Override
    public String getContact() {
        return "LocalConsultant#" + getId();
    }

    @Override
    public double getPay() {
        return earnFromSkill() - permitTax;
    }

}
