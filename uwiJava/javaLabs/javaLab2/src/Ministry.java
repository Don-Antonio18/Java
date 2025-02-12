package Java.uwiJava.javaLabs.javaLab2.src;

import java.util.ArrayList;


class Ministry {
    private Studio[] studios;
    private int grantPool;
    private int minGrantVal;
    private final int MINCLAIMABLE =0;//50000;
    private int songPartEst, bestSellLimit;
    private boolean suggestStudios;
    private ArrayList<Singer> singers;
    


    public Ministry(int grantPool, int minGrantVal, int songPartEst, int bestSellLimit, boolean suggestStudios) {
        studios = new Studio[5];
        studios[0]= new Studio("Ruff Gong", 12, 10000, 3);
        studios[1]= new Studio("Studio One", 12, 20000, 3);
        studios[2]= new Studio("Rich Entertainment",12, 60000, 3);
        studios[3]= new Studio("Eight76 Music", 12,80000,3);
        studios[4]= new Studio("Juss Buss",12, 160000, 3);
        
        this.minGrantVal = minGrantVal;
        this.grantPool=grantPool;
        this.bestSellLimit = bestSellLimit;
        this.songPartEst=songPartEst;
        this.suggestStudios = suggestStudios;
        singers = new ArrayList<Singer>();
    }

    public Studio getStudio(int id) {
        return studios[id];
    }
    
    public int getSongPartEst() {
        return songPartEst;
    }
    public int getBestSellLimit() {
        return bestSellLimit;
    }

    public int getMinClaimable() {
        return MINCLAIMABLE;
    }

    public String processGrant(Singer singer) {
        int grantVal=0;
        String grantMsg=" ";
        int singval = singer.sumRegisteredSongs();
        System.out.println(">>Processing request from " +singer.getName()+" for $"+String.format("%,d",singval));
        if (singval > MINCLAIMABLE){

            singers.add(singer);
            if (singval < minGrantVal)
                grantMsg ="Request for $"+String.format("%,d", singval)+" declined:minimum intellectual property for grants is $"+String.format("%,d", minGrantVal);
            else
            {
                if (singval > grantPool)
                    grantMsg ="Request for $"+String.format("%,d", singval)+" declined:Insufficient funds in grant pool";
                else {
                    grantVal = singval;
                    grantMsg = "$"+String.format("%,d", grantVal) +" granted to "+singer.getName();
                    grantPool -=grantVal;
                }
            }
        }
        return grantVal+";"+grantMsg;
    }

    public void showAwarded(boolean awarded){
        singers.forEach((singer)->{
              if((singer.getGrantValue()>0)==awarded)
                  System.out.println(singer);});
        //ANOTHER WAY TO DO THIS IS ....
        ///for(int i=0; i<singers.size();i++)
        ///    if((singers.get(i).getGrantValue()>0)==awarded)
        //          System.out.println(singers.get(i));
            
    }
    
    public int countSingers(){
        return singers.size();
    }
    public Studio getBestAvailStudio(int budget, Studio favStudio)
    {
        //studio already stored in sorted order of cost... no need to sort
        boolean found =false;
        Studio retStudio=favStudio;
        int foundDx =-1; 
        if (suggestStudios)
        {
            for (int dx=0; ((dx<studios.length)&&(!found));dx++)
            {
                if (!found)
                {

                    if(studios[dx].isAvailable())
                    {
                        System.out.println(">>"+studios[dx].getName() +" is available for $"+String.format("%,d", studios[dx].getCost())+ ".");
                        found = (budget >=studios[dx].getCost());
                        if (found)
                        {
                            foundDx=dx;
                            retStudio = studios[dx];

                        }
                    }
                    else
                       System.out.println(">>"+studios[dx].getName() +"is not available.");


                }

            }

            if (retStudio==null)
                System.out.println(">>No studio available for $"+String.format("%,d", budget)+".");
            else
                System.out.println(">>Assigned studio "+retStudio.getName()+".");
        }

        return retStudio;

    }
  
  
}
