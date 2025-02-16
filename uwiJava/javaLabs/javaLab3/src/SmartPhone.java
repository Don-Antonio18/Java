package Java.uwiJava.javaLabs.javaLab3.src;
//QUESTION 3. Create class SmartPhone
//Pass case2 by completing SmartPhone
class SmartPhone extends InternetThing{
    String  model;
    int megaPixels;
    boolean locked; 
     
   public SmartPhone(String  manufacturer, String serialNumber,String model,int megaPixels){
       //! Initialize Superclass & store args appropriately
        super(manufacturer, serialNumber);
        this.model = model;
        this.megaPixels = megaPixels;

        //! Set "locked" --> true:
        this.locked = true;
        System.out.println("Created "+ this);
   }
         
      public void lock(){
          locked =true;
          System.out.println("Locked " +this);
      }
      public void unlock(String pw){
            if (password.equals(pw)){
                locked = false;
                System.out.println("Unlocked " + this);  
            }
                
                
          }
      
      public void setPassword(String oldPassword, String newPassword){ 
               if (password.equals(oldPassword)){
                  password  = newPassword;
                  System.out.println("Successfully changed password for " +this);
          }
      }
                                                                      
                                                                  
    //! Custom toString() method for test case 2:
    @Override
    public String toString() {
        return "Thing#" + getId() + "::PHONE made by " + getManufacturer() + ":Model=" + model + "@IP:" + getIPAddress();
    }

     
     
 }
 
