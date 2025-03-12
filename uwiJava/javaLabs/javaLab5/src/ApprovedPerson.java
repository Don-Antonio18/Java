import java.util.ArrayList;

/**
 * Represents an approved person with additional safety requirements.
 * Extends BasePerson and implements sorting by age.
 */
public class ApprovedPerson extends BasePerson implements Comparable<ApprovedPerson>{
    ArrayList<String> safeReqs;     // List of safety requirements
    
    /**
     * Creates an approved person with specified attributes
     * @param age Person's age
     * @param name Person's full name
     * @param publish Consent to publish
     * @param id Unique identifier
     */
    public ApprovedPerson(int age, String name, boolean publish, int id)
    {
        super( name, age,publish);
        super.setId(id);
        safeReqs = new ArrayList<String>();

    }

    /**
     * Adds a safety requirement
     * @param sreq The safety requirement to add
     */
    public void addSafeReq(String sreq)
    {
        safeReqs.add(sreq);
    }

    /**
     * @return List of all safety requirements
     */
    public ArrayList<String> getSafeReqs()
    {
        return safeReqs;
    }

    /**
     * Compares approved persons by age in descending order
     */
    public int compareTo(ApprovedPerson ap)
    {
        return ap.getAge() - this.getAge() ;
    }

    /**
     * @return Name formatted as "LastName, FirstName"
     */
    public String getName()
    {
        String returnval =  "";
        String[] nameparts = name.split(" ");
        if (nameparts.length ==1)
            returnval = nameparts[0];
        if (nameparts.length ==2)
            returnval = nameparts[1]+", "+nameparts[0];    

        return returnval;
    }
    
    /**
     * @return Original unformatted name
     */
    public String getSimpleName()
    {
        return name;
    }
 
    /**
     * @return Header string for approved persons table
     */
    public static String getAPHeader()
    {
        String returnval = "ID\tName\t\tSafety_Requirements";
        returnval+="\n---------------------------------";
        return returnval;
    }

    /**
     * @return String representation of approved person
     */
    public String toString()
    {
        return(getId()+"\t"+getName()+"\t\t"+getSafeReqs());
    }
}
