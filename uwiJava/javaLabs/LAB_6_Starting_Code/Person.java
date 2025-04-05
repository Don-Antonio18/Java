public class Person extends BasePerson implements Comparable<Person>
{

    private static int nextId=0;


    public Person(String name, int age, boolean willPublish)
    {
        super(name, age, willPublish);
         super.setId(nextId);
         nextId++;
    }

    public String getName()
    {
        return name;
    }

    public static String getPHeader()
    {
        String returnval = "ID\tName\tAge\tWillPublish";
        returnval+="\n---------------------------------";
        return returnval;

    }

    public String toString()
    {
        return(getId()+"\t"+getName()+"\t"+getAge()+"\t"+getPublish());
    }

    
    public static void resetId()
    {
       nextId=0;   
    }
    
    public int compareTo(Person other)
    {
       return other.getId() - this.getId();   
    }

}