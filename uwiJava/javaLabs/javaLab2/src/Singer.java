import java.util.ArrayList;

class Singer {

    private String name="", genre;
    private int budget;
    private Studio favStudio;
    private ArrayList<Song> songs, registeredSongs;
    private Ministry ministry;
    private int grantValue;
    private String grantMessage;
    private boolean willApply;

    public     Singer(String n, String g, int b, Ministry min, boolean wantToApply ) {
        //! QUESTION 1A
        name = n;
        genre = g;
        budget = b;
        ministry= min; 
        willApply = wantToApply;

        songs = new ArrayList< Song>();
        registeredSongs= new ArrayList< Song>();
        grantMessage ="";
        this.favStudio=null;
        

    }

            

    public Singer(String name, String genre, int budget, int fav, Ministry ministry, boolean willApply ) {
        //! QUESTION 1B
        this.name = name;
        this.genre = genre;
        this.budget = budget;
        this.favStudio= ministry.getStudio(fav);
        this.ministry = ministry;
        this.willApply = willApply;

        songs = new ArrayList<Song>();
        this.registeredSongs= new ArrayList<Song>();
        grantMessage = " ";
        grantValue = 0;
        
    }



    public Ministry getMinistry() {
        return ministry;
    }

    public boolean applying(){
        return willApply;
    }
    public boolean studioExists(Studio favStudio)
    {
        return (favStudio!=null) ;
    }

    public boolean canAfford(Studio studio)
    {
        return studio.getCost() <= budget;
    }
  
    public int getGrantValue(){
        return grantValue;
    }

    public int sumRegisteredSongs() {
        int sum = 0;
        for (Song regsong:registeredSongs)
            sum+=regsong.getClaimableEarnings();

        return sum;
    }

    
        
    
    public int sumEstValue() {
        int sum = 0;
        //! QUESTION 3 --> EVALUATE THE TOTAL EARNABLE VALUE OF ALL SONGS
        for (Song song : songs)
            sum += song.getEstEarnings();
        return sum;
    }
    
    public String getName() {
        return name;
    }

    
        
    public void tryToRegisterSong(Song song) {
      Studio selectedStudio=null;
        String str= "Registering "+song.getTitle()+" with budget $"+ String.format("%,d",budget) + ".";
        if (favStudio==null)
            str = str + " No preferred studio.";
        else
        {
            str = str + "Prefers "+favStudio.getName();
            if (!favStudio.isAvailable())
                str+="(Not available).";
            else{
                str+="(Available:cost[$"+String.format("%,d",favStudio.getCost())+"]).";
                selectedStudio=favStudio;
            }
      
        }
         
        System.out.println(str);
        //////////IN THIS METHOD, DO NOT MODIFY ABOVE THIS LINE /////////////////////

              // Add code here to compelete Q4B so that if the selected studio is available,    
        // the studio on the song is set to the selectedstudio

        if (favStudio != null && favStudio.isAvailable() && canAfford(favStudio)) {
        selectedStudio = favStudio;
        } else if (ministry != null) {
                selectedStudio = ministry.getBestAvailStudio(budget, favStudio);
            }           
        

        if (selectedStudio !=null && selectedStudio.isAvailable() && canAfford(selectedStudio)){
            song.setStudio(selectedStudio);
            selectedStudio.reserve(); 
            registeredSongs.add(song);
            budget -= selectedStudio.getCost();

        }
        
  
    }
    

    
    public void addSong(String title){
            songs.add(new Song(title, genre, this ));
    }
    
    public ArrayList<Song> getSongs(){
        return songs;
    }

    public void applyForGrant() {
        for (Song song:songs)
            tryToRegisterSong(song);
        String response = ministry.processGrant(this);
        String[] responseParts = response.split(";");
        grantValue = Integer.parseInt(responseParts[0]);
        grantMessage =responseParts[1];
    }

    public String toString() {
        String str = "";
        str += "-----------------------------------------------------------------\n";
        str += name.toUpperCase();
        if (studioExists(favStudio))
            str += "[" + favStudio.getName() + "]";
        if (grantValue > 0) {

            str += "::GRANTED $" + String.format("%,d", grantValue) + "\n";
            str += "SONGS SUPPORTED\n";
            for (int i = 0; i < registeredSongs.size(); i++)
                str += registeredSongs.get(i) + "\n";
        } else
            str += "::" + grantMessage + "\n";

        return str;
    }

    

}







