package Java.uwiJava.javaLabs.javaLab2.src;
class Song {

    private String title, genre;
    Singer singer;
    private Studio studio;
      
    public Song(String title, String genre,Singer singer)
    {
    
        this.studio = null;
        this.genre = genre;
        this.singer = singer;
        this.title = title;
     
    }
    
        
    public int getEstEarnings() {
        //! QUESTION 2 --> EVALUATE THE ESTIMATED EARNINGS OF A SONG

        int titleLength =  title.length();
        int songPartEst = singer.getMinistry().getSongPartEst();
        int estEarnings = titleLength * songPartEst;

        return  estEarnings;
    }
    
    public int getClaimableEarnings() {
        int est = getEstEarnings();
        if (est > singer.getMinistry().getMinClaimable())
             return est;
        else
             return 0;
    }
    
    public String getTitle(){
        return title;
    }
    

  
    //! QUESTION 4A  --> IMPLEMENT THE SETSTUDIO METHOD
    // ACCEPT A STUDIO & SET ASSOCIATED STUDIO ON SONG TO THE REFERENCED STUDIO
 
    
    //! HELPER METHOD FOR QUESTION 4B
    public void setStudio(Studio studio){
        this.studio = studio;
    }   
    
    public Studio getStudio(Studio studio){
        this.studio = studio;
       return studio;
    }

    // public Studio getStudio(){
    //     return studio;
    // }

    private boolean isBestSeller() {
         return getEstEarnings()>singer.getMinistry().getBestSellLimit();
    }

    
    public boolean hasStudio() {
         return (!(studio==null));
    }
     
   public String toString() {
         String outval ="Released ";
        if (isBestSeller())
            outval+="**";
        int cost =0;
        if (!(studio==null))
           cost = studio.getCost();   
        outval+=title+" as a "+genre + " single to earn $"+String.format("%,d", getEstEarnings())+".";

        return outval;
    }
}

