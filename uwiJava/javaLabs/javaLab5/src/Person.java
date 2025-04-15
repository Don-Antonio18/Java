//import src.BasePerson;
//import src.Person;

/**
 * Concrete implementation of BasePerson representing a basic person in the system.
 * Implements Comparable to allow sorting based on ID.
 */
public class Person extends BasePerson implements Comparable<Person>
{
    private static int nextId=0;    // Auto-incrementing ID counter
    
    /**
     * Creates a new Person with auto-generated ID
     * @param name The person's full name
     * @param age The person's age
     * @param willPublish Whether person consents to data publication
     */
    public Person(String name, int age, boolean willPublish)
    {
        super(name, age, willPublish);
        super.setId(nextId);
        nextId++;
    }

    /**
     * @return The person's unformatted full name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return Header string for person listing table
     */
    public static String getPHeader()
    {
        String returnval = "ID\tName\tAge\tWillPublish";
        returnval+="\n---------------------------------";
        return returnval;
    }

    @Override
    public String toString()
    {
        return(getId()+"\t"+getName()+"\t"+getAge()+"\t"+getPublish());
    }

    /**
     * Resets the auto-incrementing ID counter
     */
    public static void resetId()
    {
        nextId=0;   
    }
    
    /**
     * Compares persons based on ID in descending order
     * @param other The person to compare with
     * @return Positive if this person's ID is lower
     */
    public int compareTo(Person other)
    {
        return other.getId() - this.getId();   
    }
}