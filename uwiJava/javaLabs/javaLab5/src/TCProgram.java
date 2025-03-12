
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class TCProgram { 
    private ArrayList<Person> plist = new ArrayList<Person>();
    private ArrayList<ApprovedPerson> aplist = new ArrayList<ApprovedPerson>();
    private ArrayList<TransportCon> tclist = new ArrayList<TransportCon>();
    private ArrayList<FullyContractedPerson> fclist = new ArrayList<FullyContractedPerson>();
    private int initApproved;
    
    public void clearData()
    {
        plist.clear();
        aplist.clear();
        tclist.clear();
        fclist.clear();
        Person.resetId();
    }

    public ArrayList<Person> loadPersons(String pfile)
    {
        Scanner pscan = null;
        ArrayList<Person> plist = new ArrayList<Person>();
        try
        {
            pscan  = new Scanner(new File(pfile));
            while(pscan.hasNext())
            {
                String [] nextLine = pscan.nextLine().split(" ");
                String name = nextLine[0]+ " " + nextLine[1];
                int age = Integer.parseInt(nextLine[2]);
                boolean publish = false;
                if (nextLine[3].equals("0"))
                    publish = false;
                else
                    publish =true;
                Person p = new Person(name, age, publish);
                plist.add(p);
            }

            pscan.close();
        }
        catch(IOException e)
        {}

        return plist;

    }

    public void loadApproved(String afile)
    {
        Scanner apscan = null;
         
        /*
        while (apscan.hasNext())
            {
                String apLine = apscan.nextLine();
                String [] nextLine = apLine.split(" ");

                int id = Integer.parseInt(nextLine[0]);
                int foundpos = findPerson(plist, id);//.indexOf(id);//***
                if (foundpos >=0)
                {
                    ApprovedPerson ap = new ApprovedPerson(plist.get(foundpos).getAge(),
                            plist.get(foundpos).getName(), plist.get(foundpos).getPublish(),
                            plist.get(foundpos).getId());
                    if (nextLine.length>0)
                        for (int i=1;i<nextLine.length;i++)
                            ap.addSafeReq(nextLine[i]);
                    aplist.add(ap);
                    plist.remove(foundpos);
                }
            }
         
          */
            this.initApproved = aplist.size();

    }

    public ArrayList<TransportCon> loadTCBatches (String tcfile) throws IOException, ArrayIndexOutOfBoundsException    {
        Scanner tscan = null;
        ArrayList<TransportCon> tlist = new ArrayList<TransportCon>();

        return tlist;

    }

    public ArrayList<Person> getPlist()
    {
        return plist;
    }

    public ArrayList<ApprovedPerson> getAPlist()
    {
        return aplist;
    }

    public ArrayList<TransportCon> getTClist()
    {
        return tclist;
    }

    public ArrayList<FullyContractedPerson> getFClist()
    {
        return fclist;
    }

    //public void updateApproved(ArrayList<ApprovedPerson> aplist)
    public void updateApproved()
    {

        this.aplist=aplist;   
    }

    public int getInitApproved()
    {
        return initApproved;
    }

    public void loadData(int caseNo)
    {
        plist =  loadPersons(getPersonInFile(caseNo));
        loadApproved(getApprovalInFile(caseNo));
        try
        {
            tclist =  loadTCBatches(getTBatchInFile(caseNo));
        }
        catch (IOException ioe){}
    }
    
      public String getPersonInFile(int caseNo)
    {
        return "./cases/TestCase"+caseNo+".persons.txt";

    }

    public String getApprovalInFile(int caseNo)
    {
        return "./cases/TestCase"+caseNo+".approved.txt";

    }

    public String getTBatchInFile(int caseNo)
    {
        return "./cases/TestCase"+caseNo+".batches.txt";

    }


    public int findPerson(ArrayList<? extends BasePerson> p, int id)
    {
        int pos = -1;
        
        return  pos;

    }

    public int countPersons()
    {
        return plist.size();
    }

    public int countApproved()
    {
        return aplist.size();
    }

    public int countCons()
    {
        return fclist.size();
    }

    public void printAllTBatches(PrintStream outStream, boolean header)
    {
                
        Collections.sort(tclist);
        if (header)
            outStream.println(TransportCon.getTCHeader());
        for (TransportCon tc:tclist)
            outStream.println(tc);
    }

    public void printAllPersons(PrintStream outStream, boolean header)
    {
        if (header)
            outStream.println(Person.getPHeader());
        for (Person p:plist)
            outStream.println(p);
    }

    public void printAllApproved(PrintStream outStream, boolean header)
    {
        Collections.sort(aplist);
        if (header)
            outStream.println(ApprovedPerson.getAPHeader());
        for (ApprovedPerson ap:aplist)
            outStream.println(ap);
    }

    public void printAllCons(PrintStream outStream, boolean header)
    {
        Collections.sort(fclist);
        if (header)
            outStream.println(FullyContractedPerson.getFCHeader());
        for (FullyContractedPerson fc:fclist)
            outStream.println(fc);
    }

    public void reportAllCons(PrintStream outStream)
    {
        String pub;
        Collections.sort(fclist);
        for (FullyContractedPerson fc:fclist)
        {
            pub = fc.publish();
            if (pub.length()>0)
               outStream.println(pub);
        }
    }

    public void printFindPerson(PrintStream outStream)
    {

        for (BasePerson p:plist)
            outStream.println(findPerson(plist,p.getId()));
        for (BasePerson ap:aplist)
            outStream.println(findPerson(aplist,ap.getId()));

        for (BasePerson fc:fclist)
            outStream.println(findPerson(fclist,fc.getId()));

    }

    public int countTCons()
    {
        int sum=0;
        int i=0;
        //ArrayList<TransportCon> vlist
        boolean found = false;
        for (TransportCon tc:tclist)
            sum+=tc.getBalance();

        return sum;

    }    

    public void applyTCons()
    {
        if (aplist.size()>0)
        {
            //Collections.sort(aplist);
            //Collections.sort(tclist);
            for (TransportCon tc:tclist)
            {
                int apos =aplist.size()-1;
                while ((apos>=0)&&(tc.getBalance()>0))
                {
                    ApprovedPerson ap = aplist.get(apos);
                    //Person p = (Person)plist.get(findPerson(plist,ap.getId()));
                    if (!(tc.discImpact(ap.getSafeReqs())))
                    {
                        tc.reduceBalance();

                        FullyContractedPerson fc = new FullyContractedPerson(
                                ap.getAge(),ap.getSimpleName(),ap.getPublish(), ap.getId(),tc.getName());
                        fclist.add(fc);
                        aplist.remove(apos);

                    }
                    apos--;
                }

            }
        }

    }

    public void applyRemaining()
    {
        //Collections.sort(tclist);
        Collections.sort(plist);
        for (TransportCon tc:tclist)
        {
            int pos = plist.size()-1;
            while ((pos >=0)&&(tc.getBalance()>0))
            {
                Person p = (Person)plist.get(pos);
                //if (findPerson(fvlist, p.getId())>=0 )//pos id not in fully vaccinated
                FullyContractedPerson fc = new FullyContractedPerson(
                        p.getAge(),p.getName(),p.getPublish(), p.getId(),tc.getName());
                fclist.add(fc);
                tc.reduceBalance();
                plist.remove(pos);

                pos--;
            }

        } 

    }

    public void publishData()
    {
        int numPersons = plist.size();
        //int initApproved 
        int approvedRemaining = aplist.size();
        //ArrayList<TransportCon> vblist, ArrayList<FullyContractedPerson> fvlist
        String header = "<html>";
        header+= "<head><meta http-equiv='refresh' content='30'></head>";
        header+= "<hr>"; 
        header+= "<p><font face =Arial size=2>Total Applicants:"+numPersons+"</font></p>"; 
        header+= "<p><font face =Arial size=2>Initially Approved:"+initApproved+"</font></p>"; 
        header+= "<p><font face =Arial size=2>Total Administered:"+fclist.size()+"</font></p>";    
        header+= "<p><font face =Arial size=2>Postponed:"+approvedRemaining+"</font></p><hr>"; 

        String tcdata = "<table border = 0><tr><td>Name</td><td>Size</td><td>Balance</td></tr>";
        Collections.sort(tclist);
        for (TransportCon tc:tclist)
            tcdata+= "<tr><td>"+tc.getName()+"</td><td>"+tc.getSize()+"</td><td>"+ tc.getBalance()+"</td></tr>";
        tcdata+="</table>";

        String personalPublish ="";
        String pub;
        Collections.sort(fclist);
        for (FullyContractedPerson fc:fclist)
        {
            pub = fc.publish();
            if (pub.length()>0)
                personalPublish+=fc.publish();
        }
        String footer ="<hr></html>";

        String pubFile = header+tcdata+personalPublish+footer;
        PrintWriter outwriter = null;
        try {

            outwriter = new PrintWriter(new FileOutputStream("publish.html"));
            outwriter.write(pubFile);
            outwriter.close();
            String userdir = System.getProperty("user.dir");
            System.out.println("Report written to "+userdir+"\\publish.html");
        }
        catch (IOException ioe) {}

    }

    public void showData(PrintStream outStream)
    {
        boolean header = true;
        outStream.print("========="+ countTCons());
        outStream.println(" CONTRACT BATCH(ES)=======");
        printAllTBatches(outStream,header);
        outStream.print("========="+ countPersons());
        outStream.println(" PERSON(S)=======");
        printAllPersons(outStream,header);
        outStream.print("========="+ countApproved());
        outStream.println(" APPROVED PERSON(S) OUTSTANDING======");
        printAllApproved(outStream,header);
        outStream.print("========="+countTCons());
        outStream.println(" FULLY CONTRACTED PERSON(S)=======");
        printAllCons(outStream,header); 
    }
    
    
    public void printData(PrintStream outStream)
    {
        boolean header = false;
        //outStream.print("========="+ countTCons());
        //outStream.println(" CONTRACT BATCH(ES)=======");
        printAllTBatches(outStream,header);
        //outStream.print("========="+ countPersons());
        //outStream.println(" PERSON(S)=======");
        printAllPersons(outStream,header);
        //outStream.print("========="+ countApproved());
        //outStream.println(" APPROVED PERSON(S) OUTSTANDING======");
        printAllApproved(outStream,header);
        //outStream.print("========="+countTCons());
        //outStream.println(" FULLY CONTRACTED PERSON(S)=======");
        printAllCons(outStream,header); 
    }


}

