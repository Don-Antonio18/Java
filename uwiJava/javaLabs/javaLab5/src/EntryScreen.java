import java.util.ArrayList;
import java.util.Scanner;

//import src.BasePerson;
//import src.Person;
/**
*
* @author PGaynor
*/
/**
 * Provides a text-based user interface for data entry operations.
 * Handles input for persons, transport contracts, and approvals.
 */
public class EntryScreen
{
    /**
     * Creates a new entry screen interface
     */
    public EntryScreen()
    {
        /**
        * This is the default constructor, which is the only one needed.
        */
    }

    /**
     * Prompts for and collects person details from user input
     * @param scan Scanner for reading user input
     * @return New Person object or null if input invalid
     */
    public Person getPerson(Scanner scan)
    {
        /**
        * This method accepts information on a person
        */
        Person p=null;
        scan.nextLine();//This clears the buffer of any previously waiting input
        try{
            System.out.println("Enter Name:");
            String name = scan.nextLine();
            System.out.println("Enter Age:");
            int age= Integer.parseInt(scan.nextLine());
            System.out.println("Willing to publish?(1=yes,0=no):");
            int willpub= Integer.parseInt(scan.nextLine());
            boolean pub;
            if (willpub==0)
                pub =false;
            else
                pub = true;
            p = new Person(name, age,pub);;
        }
        catch (NumberFormatException nfe)
        {
            System.out.println("Invalid input");
        }
        return p;

    }

    /**
     * Prompts for and collects transport contract details
     * @param scan Scanner for reading user input
     * @return New TransportCon object or null if input invalid
     */
    public TransportCon getTransportCon(Scanner scan)
    {
        /**
        * This method accepts information on a batch of transport contracts
        */
        TransportCon tc=null;
        //Complete code to accect vacci nebatch from screen
        return tc;

    }

    /**
     * Handles the person approval process
     * @param scan Scanner for reading user input
     * @param tcp Program instance containing person list
     * @return New ApprovedPerson object or null if not found/invalid
     */
    public ApprovedPerson approvePerson(Scanner scan, TCProgram tcp)
    {
        /**
        * This method accepts approves a person
        */
        ApprovedPerson ap = null;
        try{
            scan.nextLine();//This clears the buffer of any previously waiting input
            ArrayList<? extends BasePerson> plist =tcp.getPlist();
            System.out.println("Enter ID:");
            int id= Integer.parseInt(scan.nextLine());
            int foundpos = -1;//vx.findPerson(plist, id);//.indexOf(id);//***
            if (foundpos >=0)
            {
                Person foundPerson = (Person)plist.get(foundpos);
                ap = new ApprovedPerson(foundPerson.getAge(),
                    foundPerson.getName(), foundPerson.getPublish(), foundPerson.getId());

                String sr="";
                do 
                {

                    if (sr.length() >1)
                        ap.addSafeReq(sr);
                    System.out.println("Add a safety requirement, or press [X] to exit:");
                }
                while (!(sr = scan.nextLine() ).equalsIgnoreCase("x")) ;
            }
        }
        catch(NumberFormatException nfe){}
        return ap;
    }

}
