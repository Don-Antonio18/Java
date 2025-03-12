
public class FullyContractedPerson extends BasePerson implements Comparable<FullyContractedPerson>{
    private String operatorName;
    public FullyContractedPerson(int age, String name,boolean publish,int id, String operatorName)
    {
        super( name, age, publish);
        super.setId(id);
        this.operatorName= operatorName;
    }

    public String getOperatorName()
    {
        return operatorName;
    }

    public String publish()
    {
        String returnval ="";
        return returnval;

    }

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
    
   public static String getFCHeader()
    {
     String returnval = "ID\tName\t\tOperator";
     returnval+="\n---------------------------------";
     return returnval;
     
    }
 
 
    public int compareTo(FullyContractedPerson other)
    {
       return   this.getName().compareTo(other.getName()); 
    }
      


}
