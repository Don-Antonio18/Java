package Java.uwiJava.javaLabs.javaLab3.src;
class SmartHome {
    InternetThing baseAddress, router;
    private final int MAX_THINGS = 252;
    int numThings =0;
    public InternetThing[] things = new InternetThing[MAX_THINGS];
    //NB Making "things" public violates the principle of self-governing classes, but without additional knowledge that will be gained in coming weeks, this is the available strategy to aggregate a group of related sub-classes  
    public SmartHome()
    {
        InternetThing baseAddress = new InternetThing("BASENET","N00");
        InternetThing router = new InternetThing("BASENET","N01");
        things[0]=baseAddress;
        things[1]=router;
        //numThings=2;
        System.out.println(baseAddress.toString());
        System.out.println(router.toString());
      
    }

//! Set item in arrayThings[] to refridgerator object 
    public int addThing(String [] args )
    {
        int returnval=-1;
        //TO HELP YOUR DEBUGGING IF NECESSARY
        //String argList ="";
        //for (int i=0; i< args.length;i++)
        //     System.out.println(i+":"+args[i]);
        if (args[0].equals("REFRIGERATOR"))  //! Fridge
        {
            String manufacturer = args[1];
            String serialNo = args[2];
            int basePower = Integer.parseInt(args[3]);
            int powerRating =Integer.parseInt(args[4]);
            int capacity = Integer.parseInt(args[5]);
             //Pass case 1 by adding a newly created Refrigerator to array things at position rf.getId() (see how it is done with the SmartPhone), then uncommenting the line that sets returnval
            Refrigerator rf = new Refrigerator( manufacturer, serialNo,  basePower, powerRating,  capacity);
        //!1. Set the value in array things, at the position that matches the ID of the refrigerator ie (rf.getId()), to the refrigerator object.
            things[rf.getId()] = rf;
            returnval = rf.getId();
        }

        if (args[0].equals("SMARTPHONE"))    //! smartphone 

        {
            String manufacturer = args[1];
            String serialNo = args[2];
            String model = args[3];
            int megaPix = Integer.parseInt(args[4]);

            SmartPhone sp = new SmartPhone(manufacturer,serialNo,model,megaPix);
            things[sp.getId()]=sp;
            returnval = sp.getId();
        }
        

         if (args[0].equals("LIGHTBULB"))
        {
            String manufacturer = args[1];
            String serialNo = args[2];
            int lumens = Integer.parseInt(args[3]);
            LightBulb lb = new LightBulb(manufacturer, serialNo, lumens);   
            things[lb.getId()]=lb;
            returnval = lb.getId();
        }


    
        return returnval;
    }


    public void showItemPower(int id)
    {

        System.out.println(things[id].getPowerUse() +"mW\t"+things[id].toString());
    }


    public void showThing(int id)
    {
        // shows the toString method of the thing at position id
        System.out.println( things[id].toString());
    }

    public void showAllThings()
    {
        System.out.println("===============SHOWING ALL "+InternetThing.getNumThings()+" THINGS===============");
        for(int i=0; i<InternetThing.getNumThings();i++)
            showThing(i);
    }

    public void showAllPower()
    {
        //!.Question 6 Pass case 5 by completing showAllPower()

        System.out.println("===============SHOWING ALL POWER===============");
        
        int sumPower =0;
        for(int i=0; i<InternetThing.getNumThings();i++)//replace with appropriate code
          {  
            int powerVal= things[i].getPowerUse();//replace with code to extract power use of the internet thing
            sumPower += powerVal;
            System.out.println(powerVal+"\t"+things[i].toString());
        }
        System.out.println("TOTAL POWER = "+sumPower+"mW");
    }
}
