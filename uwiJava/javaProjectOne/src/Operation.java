import java.util.ArrayList;

public class Operation {
    // instance variables - replace the example below with your own
    private String callSign;
    private static int opTracker = 0;
    protected Community cm;

    public Operation(Community community) {
        cm = community;
        callSign = "DS" + opTracker++;
    }

    public String getCallSign() {
        return callSign;

    }

}
