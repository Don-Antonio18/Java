/**
 * Abstract base class representing a person with basic attributes.
 * Serves as the parent class for different types of people in the system.
 */
public abstract class BasePerson {
    protected String name;      // Person's full name
    private boolean publish;    // Whether person consents to data publication
    private int age;           // Person's age
    private int id;            // Unique identifier
    
    /**
     * Creates a new BasePerson with specified attributes
     * @param name The person's full name
     * @param age The person's age
     * @param pub Whether the person consents to data publication
     */
    public BasePerson(String name, int age, boolean pub)
    {
        this.name = name;
        this.age = age;
        publish = pub;
    }
    
    /**
     * @return The person's age
     */
    public int getAge()
    {
        return age;
    }
    
    /**
     * Abstract method to get formatted name
     * @return The formatted name string
     */
    public abstract String getName();
    
    /**
     * Sets the person's unique identifier
     * @param id The ID to set
     */
    protected void setId(int id)
    {
        this.id = id;
    }
    
    /**
     * @return The person's unique identifier
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * @return Whether the person consents to data publication
     */
    public boolean getPublish()
    {
        return publish;
    }
}
