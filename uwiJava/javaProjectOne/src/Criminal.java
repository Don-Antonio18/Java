
public class Criminal {
    boolean arrested;
    boolean rehabilitated;
    String name;

    public Criminal(String name) {
        arrested = false;
        rehabilitated = false;
        this.name = name;
    }

    public void arrest() {
        arrested = true;
    }

    public String getName() {

        return name;
    }

    public void rehabilitate() {
        rehabilitated = true;
    }

    public String toString() {
        return name;

    }

}
