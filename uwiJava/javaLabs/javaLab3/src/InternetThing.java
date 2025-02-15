class InternetThing{
    //! QUESTION 1. Complete this class as described in question 1 (after correcting the compile error)
        private String manufacturer, serialNumber, ipAddress;
        private static int nextId =0;
        private static int numThings =0; //! fixed compile error
        private int id;
        private int powerUse =1;
        protected String password;
        
        public InternetThing(){}
        //! QUESTION 2: Complete InternetThing to pass case 1
        public InternetThing(String manufacturer, String serial){
            this.manufacturer = manufacturer;
            this.serialNumber = serial;
            this.id = numThings;
            this.ipAddress = "192.168.0." + id;
            this.powerUse = 1;
            this.password = "admin";
            System.out.println("Created " +toString());
            numThings++;
           
            
           
        }
        
      public int getId(){
          return id;
      }
        
      public String getManufacturer(){
           return manufacturer;
       }
        
      public String getIPAddress(){
            return ipAddress;
        }  
        
        public int getPowerUse(){
            return powerUse;
        }
        
      public static int getNumThings(){
          return numThings;
      }
        
        
        public String toString(){
            return "Thing#"+getId()+":made by "+ manufacturer+"@IP:"+ipAddress;
        }
    
        
    
        
    }        
        
        

