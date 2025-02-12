package Java.uwiJava.javaLabs.javaLab2.src;

class Studio {

    private String name;
    private int freetime;
    private int cost, recordTime;

    public Studio(String name, int freetime, int cost, int recordTime)
    {
        this.name = name;
        this.freetime = freetime;
        this.cost = cost;
        this.recordTime = recordTime;
        
    }
    
    public String getName(){
        return name;
    }

    public int getFreeTime()
    { 
        return freetime;
    }
    public Boolean isAvailable()
    {
        return freetime >= recordTime;
    }

    public void reserve()
    {
        freetime -=recordTime;
    }

    public int getCost() {

        return cost;
    }
}

