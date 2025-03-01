package Java.uwiJava.javaLabs.javaLab4.src;

class ExpatConsultant extends Person implements Consultant, RegisteredExpat{ // for test case 0, ExpatConsultant needs
    // to also implement RegisteredExpat.
    // Remember to write methods required by RegisteredExpat
    // Once Code changes are done uncomment lines in SML
    // that allow test case to be passed
    // The work permit for an ExpatConsultant
    // has the format "WP00" + the id

    private double airfare, permitTax, skillPrice;
    private int id;
    static private int nextId;
    private String sector;

    public ExpatConsultant() {
    }

    public ExpatConsultant(String dob, String sector,
            double skillPrice, double taxRate, double airfare) {
        // dob is a string in the format mm/dd/yyyy
        super();
        String[] parts = dob.split("/");
        super.setDob(Integer.parseInt(parts[1]), Integer.parseInt(parts[0]), Integer.parseInt(parts[2]));
        this.skillPrice = skillPrice;
        this.permitTax = skillPrice * taxRate;
        this.airfare = airfare;
        this.sector = sector;
        this.id = getNextId();

    }

    // 2. Implement RegisteredExpat
    @Override
    public String getWorkPermit() {
        return "WP00" + id;
    }

    public String getSector() {
        return sector;
    }

    public String getContact() {
        return "Reg. Expatriate#" + id;
    }

    public int getNextId() {
        id = nextId;
        nextId++;
        return id;
    }

    public int getId() {
        return id;
    }

    public double getPay() {
        double earnings = earnFromSkill();
        return earnings - permitTax - airfare;

    }

    public double earnFromSkill() {
        return skillPrice;
    }

}
