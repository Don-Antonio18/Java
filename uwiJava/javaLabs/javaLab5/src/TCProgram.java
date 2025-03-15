
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class TCProgram {
    private ArrayList<Person> plist = new ArrayList<Person>();
    private ArrayList<ApprovedPerson> aplist = new ArrayList<ApprovedPerson>();
    private ArrayList<TransportCon> tclist = new ArrayList<TransportCon>();
    private ArrayList<FullyContractedPerson> fclist = new ArrayList<FullyContractedPerson>();
    private int initApproved;

    public void clearData() {
        plist.clear();
        aplist.clear();
        tclist.clear();
        fclist.clear();
        Person.resetId();
    }

    public ArrayList<Person> loadPersons(String pfile) {
        Scanner pscan = null;
        ArrayList<Person> plist = new ArrayList<Person>();
        try {
            pscan = new Scanner(new File(pfile));
            while (pscan.hasNext()) {
                String[] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0] + " " + nextLine[1];
                int age = Integer.parseInt(nextLine[2]);
                boolean publish = false;
                if (nextLine[3].equals("0"))
                    publish = false;
                else
                    publish = true;
                Person p = new Person(name, age, publish);
                plist.add(p);
            }

            pscan.close();
        } catch (IOException e) {
        }

        return plist;

    }

    /*
     * Read the (commented) section of loadApproved, and verify that if there exists
     * a
     * scanner object that is accessed using the name, then, on each iteration,
     * apscan will return a string. Verify also that if the returned string is a
     * space delimited list, then the first item is a number that is passed as an
     * argument to findPerson, and the result of findPerson used to create an
     * ApprovedPerson object. Note that apscan will indeed be used to access a file.
     * That file can contain multiple lines where, on each line, the first item
     * represents the ID number, and if there are any other items then each item
     * represents a safety requirement for the ApprovedPerson. Examples of the file
     * format to be read include cases/TestCase2.approved.txt and
     * cases/TestCase4.approved.txt.
     */
    public void loadApproved(String afile) {
        /*
         * @param null scanner
         * common Java practice for resources tht need to be closed properly.
         * Scanner needs to be initialized OUTSIDE try-catch block.
         * Initializing null is a placeholder until actual file is opened.
         */
        Scanner apscan = null;

        // ! Error Handling
        try {

            apscan = new Scanner(new File(afile)); //! 3(b) read file

            while (apscan.hasNext()) {
                String apLine = apscan.nextLine();
                String[] nextLine = apLine.split(" ");

                int id = Integer.parseInt(nextLine[0]);
                int foundpos = findPerson(plist, id);// .indexOf(id);//***
                if (foundpos >= 0) {
                    ApprovedPerson ap = new ApprovedPerson(plist.get(foundpos).getAge(),
                            plist.get(foundpos).getName(), plist.get(foundpos).getPublish(),
                            plist.get(foundpos).getId());
                    if (nextLine.length > 0)
                        for (int i = 1; i < nextLine.length; i++)
                            ap.addSafeReq(nextLine[i]);
                    aplist.add(ap);
                    plist.remove(foundpos);
                }
            } // try block
            this.initApproved = aplist.size();
            
            //! 3(b)
        } catch (IOException e) { 
            System.err.println("Error: Could not access file " + afile + ":" + e.getMessage());
        } 
        

    }
    //! 4(a)  

//     The scanner should read each line in the file until at the end of the
//     file.
 
//     Each line contains a name, a size and a preference rating. IF there are more
//     than three items in the file, the fourth item represents a list of disclaimers.
//     Examples of the file format can be found in cases/TestCase4.batches.txt and
//     cases/TestCase5.batches.txt. After each line is read loadTCBatches should create a
//     contract batch with the newly extracted data, and add it to an ArrayList. 

//     ArrayList with all data encountered should be returned. If no disclaimers have
//     been found for an contract batch, use an empty string for the disclaimer. 
// !NOTE: As a design decision, you are instructed not to include exception handling inside of loadTCBatches. You are to throw exceptions from that method instead.



    public ArrayList<TransportCon> loadTCBatches(String tcfile) throws IOException, ArrayIndexOutOfBoundsException {
        ArrayList<TransportCon> tlist = new ArrayList<TransportCon>();
        Scanner tscan = null;
        tscan = new Scanner(new File(tcfile)); // open file w/scanner object
        
        while (tscan.hasNextLine()) {   // read each line in file
            String line = tscan.nextLine(); 
            String [] parts = line.split(line); // separate line by spaces.

            // each line should have 3 parts
            if (parts.length < 3) {
                tscan.close();
                throw new ArrayIndexOutOfBoundsException("Invalid number of items");
            }

            // get name, size and preference rating
            String name = parts[0];
            int size = Integer.parseInt(parts[1]);
            int preferenceRating = Integer.parseInt(parts[2]);

            // when there is fourth item
            String disclaimers = ""; //initialize empty string for stringbuilder
            if (parts.length > 3) { 
                StringBuilder sb = new StringBuilder();
                for (int i = 3; i < parts.length; i++) {
                    sb.append(parts[i]);
                    if (i < parts.length -1) { 
                        sb.append(" ");
                    }
                }
                disclaimers = sb.toString();
            }   
            TransportCon tc = new TransportCon(name, size, preferenceRating, disclaimers);
            tlist.add(tc);
        }

        tscan.close(); // close scanner
        return tlist;
        
    }

    public ArrayList<Person> getPlist() {

        return plist;
    }

    public ArrayList<ApprovedPerson> getAPlist() {
        return aplist;
    }

    public ArrayList<TransportCon> getTClist() {
        return tclist;
    }

    public ArrayList<FullyContractedPerson> getFClist() {
        return fclist;
    }

    // public void updateApproved(ArrayList<ApprovedPerson> aplist)
    public void updateApproved() {

        this.aplist = aplist;
    }

    public int getInitApproved() {
        return initApproved;
    }

    public void loadData(int caseNo) {
        plist = loadPersons(getPersonInFile(caseNo));
        loadApproved(getApprovalInFile(caseNo));
        try {
            tclist = loadTCBatches(getTBatchInFile(caseNo));
        } catch (IOException ioe) {
        }
    }

    public String getPersonInFile(int caseNo) {
        //return "./cases/TestCase" + caseNo + ".persons.txt";
        return "/Users/antoniokerr/antJava/Java/uwiJava/javaLabs/javaLab5/src/TestCase" + caseNo + ".approved.txt";

    }

    public String getApprovalInFile(int caseNo) {
        //return "./cases/TestCase" + caseNo + ".approved.txt";
        return "/Users/antoniokerr/antJava/Java/uwiJava/javaLabs/javaLab5/src/TestCase" + caseNo + ".approved.txt";

    }

    public String getTBatchInFile(int caseNo) {
        //return "./cases/TestCase" + caseNo + ".batches.txt";
        return "/Users/antoniokerr/antJava/Java/uwiJava/javaLabs/javaLab5/src/TestCase" + caseNo + ".approved.txt";

        
    }

    // ! Parameters are instances of a class of BasePerson
    public int findPerson(ArrayList<? extends BasePerson> p, int id) {
        int pos = -1; // 2(a)

        // 2(b): Search for ID in arraylist & return index
        for (int i = 0; i < p.size(); i++) {
            // check if ID of person == current ID
            if (p.get(i).getId() == id) {
                return i;
            }
        }
        return pos; // if ID is not found
    }

    public int countPersons() {
        return plist.size();
    }

    public int countApproved() {
        return aplist.size();
    }

    public int countCons() {
        return fclist.size();
    }

    public void printAllTBatches(PrintStream outStream, boolean header) {

        Collections.sort(tclist);
        if (header)
            outStream.println(TransportCon.getTCHeader());
        for (TransportCon tc : tclist)
            outStream.println(tc);
    }

    public void printAllPersons(PrintStream outStream, boolean header) {
        if (header)
            outStream.println(Person.getPHeader());
        for (Person p : plist)
            outStream.println(p);
    }

    public void printAllApproved(PrintStream outStream, boolean header) {
        Collections.sort(aplist);
        if (header)
            outStream.println(ApprovedPerson.getAPHeader());
        for (ApprovedPerson ap : aplist)
            outStream.println(ap);
    }

    public void printAllCons(PrintStream outStream, boolean header) {
        Collections.sort(fclist);
        if (header)
            outStream.println(FullyContractedPerson.getFCHeader());
        for (FullyContractedPerson fc : fclist)
            outStream.println(fc);
    }

    public void reportAllCons(PrintStream outStream) {
        String pub;
        Collections.sort(fclist);
        for (FullyContractedPerson fc : fclist) {
            pub = fc.publish();
            if (pub.length() > 0)
                outStream.println(pub);
        }
    }

    public void printFindPerson(PrintStream outStream) {

        for (BasePerson p : plist)
            outStream.println(findPerson(plist, p.getId()));
        for (BasePerson ap : aplist)
            outStream.println(findPerson(aplist, ap.getId()));

        for (BasePerson fc : fclist)
            outStream.println(findPerson(fclist, fc.getId()));

    }

    public int countTCons() {
        int sum = 0;
        int i = 0;
        // ArrayList<TransportCon> vlist
        boolean found = false;
        for (TransportCon tc : tclist)
            sum += tc.getBalance();

        return sum;

    }
    
/* 
    class SectorPayOrder implements Comparator<Person> {
    @Override
    public int compare(Person p1, Person p2) {
        // sort by sector
        int sectorCompare = p1.getSector().compareTo(p2.getSector());

        // if sectors equal, sort by pay ratwe
        if (sectorCompare == 0) {
            return Double.compare(p1.getPay(), p2.getPay());
        }

        return sectorCompare;
    }
} */
    // i. Contracts are applied in order of preference (highest first)
    // ii. Contracts are given to approved persons in order of age(oldest first)
    // (you can read to verify logic that expects tclist to be sorted by preference,
    // and aplist to be sorted by age. fclist should be sorted in alphabetical order of
    // name for consistent reporting.)

    public void applyTCons() {
        if (aplist.size() > 0) {
            // Collections.sort(aplist);
            // Collections.sort(tclist);
            Collections.sort(tclist, Collections.reverseOrder()); //! 5(b)(i) descending sort, highest first
            for (TransportCon tc : tclist) {
                int apos = aplist.size() - 1; // start from end of list (oldest first)
                while ((apos >= 0) && (tc.getBalance() > 0)) {
                    ApprovedPerson ap = aplist.get(apos);
                    // Person p = (Person)plist.get(findPerson(plist,ap.getId()));
                    if (!(tc.discImpact(ap.getSafeReqs()))) { // no violations
                        tc.reduceBalance(); // reduce spots available in contract

                        // create new contracted person
                        FullyContractedPerson fc = new FullyContractedPerson(
                                ap.getAge(), 
                                ap.getSimpleName(), 
                                ap.getPublish(), 
                                ap.getId(), 
                                tc.getName());

                        fclist.add(fc); // add to fully contracted list.
                        aplist.remove(apos); // remove from approved list.

                    }
                    apos--;
                }

            }
        }

    }

    // i. Contracts are applied in order of preference (highest first)
    // ii. Contracts are given to approved persons in order of age(oldest first)
    // (you can read to verify logic that expects tclist to be sorted by preference,
    // and aplist to be sorted by age. fclist should be sorted in alphabetical order of
    // name for consistent reporting.)
    public void applyRemaining() {
        // Collections.sort(tclist);
        Collections.sort(tclist, Collections.reverseOrder()); //! 5(b)(i) descending sort, highest first
        Collections.sort(plist);
        for (TransportCon tc : tclist) {
            int pos = plist.size() - 1; // start from oldest first
            while ((pos >= 0) && (tc.getBalance() > 0)) {
                Person p = (Person) plist.get(pos);
                // if (findPerson(fvlist, p.getId())>=0 )//pos id not in fully vaccinated
                FullyContractedPerson fc = new FullyContractedPerson(
                        p.getAge(), 
                        p.getName(),
                        p.getPublish(), 
                        p.getId(), 
                        tc.getName());

                fclist.add(fc); // add to fully contracted list
                tc.reduceBalance(); 
                plist.remove(pos);

                pos--;
            }

        }

    }

    public void publishData() {
        int numPersons = plist.size();
        //initApproved;
        int approvedRemaining = aplist.size();
        // ArrayList<TransportCon> vblist, ArrayList<FullyContractedPerson> fvlist
        String header = "<html>";
        header += "<head><meta http-equiv='refresh' content='30'></head>";
        header += "<hr>";
        header += "<p><font face =Arial size=2>Total Applicants:" + numPersons + "</font></p>";
        header += "<p><font face =Arial size=2>Initially Approved:" + initApproved + "</font></p>";
        header += "<p><font face =Arial size=2>Total Administered:" + fclist.size() + "</font></p>";
        header += "<p><font face =Arial size=2>Postponed:" + approvedRemaining + "</font></p><hr>";

        String tcdata = "<table border = 0><tr><td>Name</td><td>Size</td><td>Balance</td></tr>";
        Collections.sort(tclist);
        for (TransportCon tc : tclist)
            tcdata += "<tr><td>" + tc.getName() + "</td><td>" + tc.getSize() + "</td><td>" + tc.getBalance()
                    + "</td></tr>";
        tcdata += "</table>";

        String personalPublish = "";
        String pub;
        Collections.sort(fclist); //! sort fclist in alphabetical order
        for (FullyContractedPerson fc : fclist) {
            pub = fc.publish();
            if (pub.length() > 0)
                personalPublish += fc.publish();
        }
        String footer = "<hr></html>";

        String pubFile = header + tcdata + personalPublish + footer;
        PrintWriter outwriter = null;
        try {

            outwriter = new PrintWriter(new FileOutputStream("publish.html"));
            outwriter.write(pubFile);
            outwriter.close();
            String userdir = System.getProperty("user.dir");
            System.out.println("Report written to " + userdir + "\\publish.html");
        } catch (IOException ioe) {
        }

    }

    public void showData(PrintStream outStream) {
        boolean header = true;
        outStream.print("=========" + countTCons());
        outStream.println(" CONTRACT BATCH(ES)=======");
        printAllTBatches(outStream, header);
        outStream.print("=========" + countPersons());
        outStream.println(" PERSON(S)=======");
        printAllPersons(outStream, header);
        outStream.print("=========" + countApproved());
        outStream.println(" APPROVED PERSON(S) OUTSTANDING======");
        printAllApproved(outStream, header);
        outStream.print("=========" + countTCons());
        outStream.println(" FULLY CONTRACTED PERSON(S)=======");
        printAllCons(outStream, header);
    }

    public void printData(PrintStream outStream) {
        boolean header = false;
        // outStream.print("========="+ countTCons());
        // outStream.println(" CONTRACT BATCH(ES)=======");
        printAllTBatches(outStream, header);
        // outStream.print("========="+ countPersons());
        // outStream.println(" PERSON(S)=======");
        printAllPersons(outStream, header);
        // outStream.print("========="+ countApproved());
        // outStream.println(" APPROVED PERSON(S) OUTSTANDING======");
        printAllApproved(outStream, header);
        // outStream.print("========="+countTCons());
        // outStream.println(" FULLY CONTRACTED PERSON(S)=======");
        printAllCons(outStream, header);
    }

}
