import java.io.*;

public class Driver {
    // instance variables - replace the example below with your own
    public static void main(String[] args) throws IOException {
        int forceMultiplier, gangLimit, rehabRate;
        DoS dos = null;
        // BufferedReader bufferedReader = new
        // BufferedReader(InputStreamReader(System.in));
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/antoniokerr/antJava/Java/uwiJava/javaProjectOne/src/testData.txt"));
            int t = Integer.parseInt(bufferedReader.readLine().trim());
            for (int test = 0; test < t; test++) {

                String[] dosData = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                forceMultiplier = Integer.parseInt(dosData[0]);
                gangLimit = Integer.parseInt(dosData[1]);
                rehabRate = Integer.parseInt(dosData[2]);
                Community comm;
                Resident res;
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                int numPolice = Integer.parseInt(firstMultipleInput[0]);
                int numSoldiers = Integer.parseInt(firstMultipleInput[1]);
                int numEquip = Integer.parseInt(firstMultipleInput[2]);
                int numSocial = Integer.parseInt(firstMultipleInput[3]);
                int numSupplies = Integer.parseInt(firstMultipleInput[4]);

                dos = new DoS(forceMultiplier, gangLimit, rehabRate, numSoldiers, numEquip,
                        numPolice, numSocial, numSupplies);

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                for (int p = 0; p < n; p++) {
                    String[] secondMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(":");
                    String pname = secondMultipleInput[0];
                    String comname = secondMultipleInput[1];
                    comm = dos.getCommunity(comname);
                    res = comm.getResident(pname);
                    if (secondMultipleInput.length == 3) {
                        String crimname = secondMultipleInput[2];
                        res.recordReport(crimname);
                    }

                } // for anum

                // System.out.println(aplan.toString());

            }

            bufferedReader.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        dos.assessForOps();
        dos.publicPolicyReport();
        dos.classifiedInformationBrief();
    }
}
