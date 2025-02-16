package Java.uwiJava.javaLabs.javaLab3.src;
//QUESTION 4. Create class LightBulb
//! Pass case3 by completing LightBulb

class LightBulb extends InternetThing{
    int lumenCount;
    boolean lightOn;

    public void turnOn(){
         lightOn = true;
         System.out.println ("Turned on "+ this);
    }
    public void turnOff(){
          lightOn = false;
         System.out.println ("Turned off "+ this);
    }
    
    public LightBulb(String manufacturer, String serialNumber, int lumenCount){
        super(manufacturer, serialNumber);
        this.lumenCount = lumenCount;
        this.lightOn =  false; //! --> set lights on to false 
        System.out.println("Created " + this);
     }
    
    //! QUESTIONN 6: Override getPowerUse() method
    @Override
    public int getPowerUse(){
        if (lightOn) {  //! --> check if light is on
            return lumenCount * super.getPowerUse();
        } else {
            return 0;
        }
        
    }
    
 }
