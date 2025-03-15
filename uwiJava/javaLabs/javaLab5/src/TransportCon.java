 

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class TransportCon implements Comparable<TransportCon>{
    private int size;
    private int balance=0;
    private String name;
    private int preference;
    private ArrayList<String> disclaims;
    
    public TransportCon( String name,int size,  int preference, String disclaimers)
    {
        this.size = size;
        this.balance = size;
        this.preference = preference;
        this.name = name;
        Scanner stringScan = new Scanner(disclaimers);
        disclaims = new ArrayList<String>();
        stringScan.useDelimiter(",");
        while (stringScan.hasNext())
           disclaims.add(stringScan.next());
        
    }
    
    public int getSize()
    {
        return size;
    }
    public int getBalance()
    {
        return balance;
    }
    
    public int getPreference()
    {
        return preference;
    }
    public String getName()
    {
        return name;
    }
    
    public String getDisclaims()
    {
       String returnval="";
       for (int i=0; i<disclaims.size();i++)
       {
          returnval+=disclaims.get(i);
          if (i<disclaims.size()-1)
             returnval+=",";
          
        }
       return returnval;  
    }

    public static String getTCHeader()
    {
     String returnval = "Name\tSize\tBalance\tPreference\tDisclaimers";
     returnval+="\n---------------------------------";
     return returnval;
     
    }
    //! 4(b)
    public String testString()
    {
         return(getName()+"\t"+getSize()+"\t"+getPreference()+"\t"+getDisclaims());
    }
        
    
    public boolean discImpact(ArrayList<String> secReqs)    {
        boolean found = false;
        int i =0;
        while((!found)&&(i<disclaims.size()))
        {
            int j=0;
            while ((!found)&&(j<secReqs.size()))
            {
                if (disclaims.get(i).equals(secReqs.get(j)))
                    found=true;
                else
                    j++;
            }
            i++;
        }
        return found;
    }
    
    public int compareTo(TransportCon other)
    {
        //? descending order based on preference
        return other.preference-this.preference ;
        
    }
    
    public void reduceBalance()
    {
        
       balance--;   
    }


    

}
