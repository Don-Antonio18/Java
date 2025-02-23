
public class Resident {
    protected String name;
    private Community comm;

    public Resident(String name, Community comm) {
        this.name = name;
        this.comm = comm;
    }

    public String getName() {
        return name;
    }

    private String getCommunity() {
        return name;
    }

    public void recordReport(String criminalName) {
        comm.getCriminal(criminalName);
    }

    public String toString() {
        return name;

    }

}
