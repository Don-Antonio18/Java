package Java.uwiJava.javaLabs.javaLab4.src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.text.NumberFormat;
import java.io.*;

public class SML {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // TODO Auto-generated method stub
        double taxRate =0.15;
        Random r = new Random();
        //comment the line below to have fun with test cases 4 and 5
        r.setSeed(7);

        String test = bufferedReader.readLine().replaceAll("\\s+$","");
     
        ArrayList<Person> plist= new ArrayList<Person>();
  
        if (test.equals("CASE0-Q1"))
        {
            ExpatConsultant ec0 = new ExpatConsultant("2/1/2001","Computing",200000,taxRate,50000);
            if (ec0 instanceof RegisteredExpat)
                System.out.println(ec0);
            else
                System.out.println("RegisteredExpat not implemented");  
        }
      /*  
       /////////////////////////////////////////////////////        
         //MOVE COMMENT BLOCK BELOW THIS BLOCK TO PASS CASE0
         /////////////////////////////////////////////////////        
        if (test.equals("CASE1-Q2"))
        {
            ExpatConsultant ec0 = new ExpatConsultant("2/1/2001","Computing",200000,taxRate, 50000);
            System.out.println(ec0.getWorkPermit()+";Birthday:"+ec0.getDobDay()+"/"+ec0.getDobMonth()+"/"+ec0.getDobYear());
        }
        /////////////////////////////////////////////////////        
         //MOVE COMMENT BLOCK BELOW THIS BLOCK TO PASS CASE1
         /////////////////////////////////////////////////////        
        taxRate =0.1;

        if (test.equals("CASE2-Q3"))
        {
            NineToFiver n50 = new NineToFiver("2/1/2001","Computing",9,17,5,taxRate,200);

            if (n50 instanceof LocalResource)
            {
                System.out.println("LocalResource implemented");
                System.out.println(n50.getContact() +";Birthday:"+n50.getDobDay()+"/"+n50.getDobMonth()+"/"+n50.getDobYear());
                System.out.println(n50);//"TRN:" +n50.getTRN() +"; working in the "+n50.getSector()+" sector, is paid ");

            }
        }
        /////////////////////////////////////////////////////        
         //MOVE COMMENT BLOCK BELOW THIS BLOCK TO PASS CASE2
         /////////////////////////////////////////////////////        
        if (test.equals("CASE3-Q4"))
        {
            LocalConsultant lc0 = new LocalConsultant("2/1/2001","Computing",200000,taxRate);
            if (lc0 instanceof LocalResource)
                System.out.println(lc0);
        }
          /////////////////////////////////////////////////////        
         //MOVE COMMENT BLOCK BELOW THIS BLOCK TO PASS CASE3
         /////////////////////////////////////////////////////        
       if (test.equals("CASE4-Q5") ||test.equals("CASE5-Q6") )
        {

            double basefare = 50000;
            //add some nine to fivers
            int minwage = 100,numCases =5;
            for (int i=0; i< numCases;i++)
            {
                String birthday = getBirthDay("Jamaican",r);
                String sector = getSector(r);
                double hourlyRate = getHourlyRate(minwage,r);
                NineToFiver n5 = new NineToFiver(birthday, sector,9,17,5, hourlyRate, taxRate);
                plist.add(n5);
            }
            taxRate =0.15;
            //then some local consultants
            for (int i=0; i< numCases;i++)
            {
                String birthday = getBirthDay("Jamaican",r);
                String sector = getSector(r);
                double skillPrice = getConsultRate(r);
                LocalConsultant lc = new LocalConsultant(birthday, sector,skillPrice, taxRate);
                plist.add(lc);
            }

            // then some expat consultants
            for (int i=0; i< numCases;i++)
            {
                String birthday = getBirthDay("US/CAN",r);
                String sector = getSector(r);
                double skillPrice = getConsultRate(r);
                double airfare = getAirfare(basefare,r);
                ExpatConsultant ec = new ExpatConsultant(birthday, sector,skillPrice,taxRate, airfare);
                plist.add(ec);
            }	
            //thenprint some lists in various orders
            System.out.println("====================================");
            System.out.println("Displayed in order entered");
            System.out.println("====================================");
            for (Person p:plist)
                System.out.println(p);	
            System.out.println("-------------------------------------");
            } ///If testing case 4 or 5
            if (test.equals("CASE4-Q5"))
            {
                System.out.println("====================================");
                System.out.println("Displayed in order of day of birth");
                System.out.println("====================================");
                /////////////////////////////////////////////////////        
                //UPDATE AROUND HERE TO SORT THE LIST OF PERSONS TO PASS CASE 4
                /////////////////////////////////////////////////////        
                
                //QUESTION 5   - TEST CASE 4
                ///NEED CODE TO SORT plist --HINT- Person implements Comparable!//////
                for (Person p:plist)
                    System.out.println(p);	
                System.out.println("-------------------------------------");

            }
            
         /////////////////////////////////////////////////////        
         //MOVE COMMENT BLOCK BELOW THIS BLOCK TO PASS CASE4
         /////////////////////////////////////////////////////        

            //MOVE COMMENT BLOCK TO HERE TO PASS CASE5
            if (test.equals("CASE5-Q6"))
            {
                System.out.println("====================================");
                System.out.println("Displayed in order of pay rate");
                System.out.println("====================================");
                Collections.sort(plist,new PayOrder());
                for (Person p:plist)
                    System.out.println(p);	
                System.out.println("-------------------------------------");

                System.out.println("====================================");
                System.out.println("Displayed in order of sector then pay rate");
                System.out.println("====================================");
                //CODE TO EFFECT THE SORT//
                //CODE TO WRITE OUT SORTED DATA//	

                /////////////////////////////////////////////////////        
                //AFTER WRITING THE CODE THAT HANDLES THE SORT, INVOKE THE SORTED DATA THEN WRITE OUT THE RESULTS TO 
                //PASS CASE 5
                /////////////////////////////////////////////////////        

            }
          /////////////////////////////////////////////////////        
         //MOVE COMMENT BLOCK BELOW HERE, OR REMOVE COMMENTS - TO PASS CASE 5
         /////////////////////////////////////////////////////        

            */
            
        if (test.equals("CASE4-Q5"))
        {
            System.out.println("====================================");
            System.out.println("Displayed in order of day of birth");
            System.out.println("====================================");

        // Sort plist by day of birth using Person's compareTo method
            Collections.sort(plist);
        }
        
    }

    static String getBirthDay(String country, Random r) {
        int d = Math.abs(r.nextInt()) % 28 + 1;
        int m = Math.abs(r.nextInt()) % 12 + 1;
        int y = 1956 + Math.abs(r.nextInt()) % 65;

        String daypart = (d < 10) ? "0" + d : "" + d;
        String monthpart = (m < 10) ? "0" + m : "" + m;
        String yearpart = (y < 10) ? "0" + y : "" + y;
        String returnval = "";
        if (country.equals("Jamaica"))
            returnval = daypart + "/" + monthpart + "/" + yearpart;
        else
            returnval = monthpart + "/" + daypart + "/" + yearpart;

        return returnval;
    }

    static String getSector(Random r) {
        String returnval = "";
        int d = Math.abs(r.nextInt()) % 8;
        switch (d) {
            case 0:
                returnval = "Finance";
                break;
            case 1:
                returnval = "Entertainment";
                break;
            case 2:
                returnval = "Sports";
                break;
            case 3:
                returnval = "Computing";
                break;
            case 4:
                returnval = "Medical";
                break;
            case 5:
                returnval = "Manufacturing";
                break;
            case 6:
                returnval = "Legal";
                break;
            default:
                returnval = "Unknown";
        }
        return returnval;
    }

    static double getHourlyRate(int base, Random r) {
        int d = Math.abs(r.nextInt()) % 1000;
        return (double) (base + d);

    }

    static double getConsultRate(Random r) {
        return Math.abs(r.nextInt()) % 500000;

    }

    static double getAirfare(double basefare, Random r) {
        return basefare + Math.abs(r.nextInt()) % 150000;
    }
}
