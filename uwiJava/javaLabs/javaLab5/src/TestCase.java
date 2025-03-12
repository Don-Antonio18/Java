import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class TestCase
{
    // instance variables - replace the example below with your own
    private int caseNo;
    private static double [] caseScores={0.5,1.0,1.0,1.0,0.5,0.5,0.5};
    /**
     * Constructor for objects of class TestCase
     */
    public TestCase(int caseNo)
    {
        this.caseNo = caseNo;
    }

    public int getCaseNo()
    {
        return caseNo;

    }

    public String getPersonInFile()
    {
        return "TestCase"+caseNo+".persons.txt";

    }

    public String getApprovalInFile()
    {
        return "TestCase"+caseNo+".approved.txt";

    }

    public String getVBatchInFile()
    {
        return "TestCase"+caseNo+".batches.txt";

    }

    public String getTestOutFile()
    {
        return "./cases/TestCase"+caseNo+".myOutput.txt";

    }

    public double score()
    {
        String testfile = "./cases/TestCase"+caseNo+".myOutput.txt";
        String valfile = "./cases/TestCase"+caseNo+".valOutput.txt";
        String tString="", vString=""; 
        try{
            Scanner tscan = new Scanner(new File(testfile));
            while (tscan.hasNext())
                tString +=tscan.nextLine();
        }
        catch(IOException ioe)
        {}
        try{
            Scanner vscan = new Scanner(new File(valfile));
            while (vscan.hasNext())
                vString +=vscan.nextLine();
        }
        catch(IOException ioe)
        {}

        double sc = caseScores[getCaseNo()]*similarity(tString, vString);
        return  sc; 

    }

    public static double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;
        if (s1.length() < s2.length()) { // longer should always have greater length
            longer = s2; shorter = s1;
        }
        int longerLength = longer.length();
        if (longerLength == 0) { return 1.0; /* both strings are zero length */ }
        /* // If you have Apache Commons Text, you can use it to calculate the edit distance:
        LevenshteinDistance levenshteinDistance = new LevenshteinDistance();
        return (longerLength - levenshteinDistance.apply(longer, shorter)) / (double) longerLength; */
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;

    }

    // Example implementation of the Levenshtein Edit Distance
    // See http://rosettacode.org/wiki/Levenshtein_distance#Java
    public static int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();

        int[] costs = new int[s2.length() + 1];
        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0)
                    costs[j] = j;
                else {
                    if (j > 0) {
                        int newValue = costs[j - 1];
                        if (s1.charAt(i - 1) != s2.charAt(j - 1))
                            newValue = Math.min(Math.min(newValue, lastValue),
                                costs[j]) + 1;
                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0)
                costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }

    public static void runCases(TCProgram tcp)
    {
        ArrayList<TestCase> cases = new ArrayList<TestCase>();
        for (int i=0; i< 7; i++)
        {
            TestCase tc = new TestCase(i);
            tcp.clearData();
            tcp.loadData(i);
            if(tcp.getAPlist().size()>0)
            {
                tcp.applyTCons();//vx.setFVlist(

                if (tcp.countTCons()>0)
                    tcp.applyRemaining();
            }
            //tc.writeOut(vx.getPlist(),vx.getAPlist(),vx.getFVlist(),
            //    vx.getInitApproved(), outWriter);
            cases.add(tc);
            try{
                PrintStream outStream = new PrintStream(new FileOutputStream(tc.getTestOutFile()));
                if (i==1)
                    tcp.printFindPerson(outStream);
                else {
                    if (i<6)
                        tcp.printData(outStream);
                    else
                        tcp.reportAllCons(outStream);
                }
                outStream.close();
            }
            catch(IOException ioe){} 
        }
        double sumScore =0;
        for (TestCase tc:cases)
        {   double tcScore = tc.score();
            sumScore +=tcScore;
            System.out.println( "Score on test "+tc.getCaseNo() +" is "+tcScore+"/"+caseScores[tc.getCaseNo()]+"; sum ="+sumScore);
        }

    }

}
