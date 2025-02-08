import java.util.ArrayList;
import java.io.*;

public class Driver{

    public static void main(String[] args) throws IOException {
          int numGrants=0;
          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
           String[] preMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
           int t = Integer.parseInt(preMultipleInput[0]);
           
          int grantPool = Integer.parseInt(preMultipleInput[1]);
          int minGrantVal= Integer.parseInt(preMultipleInput[2]);
          int songPartEst= Integer.parseInt(preMultipleInput[3]);
          int bestSellLimit= Integer.parseInt(preMultipleInput[4]);
          String suggestStudios = preMultipleInput[5];
          String showSummary = preMultipleInput[6];
          boolean willSuggest = false;
          boolean summary = false;
          if (suggestStudios.equals("Yes"))
              willSuggest= true;
          if (showSummary.equals("Yes"))
              summary= true;      
         Ministry min = new Ministry(grantPool,minGrantVal,songPartEst,bestSellLimit, willSuggest);
         for(int test =0; test<t; test++)
          {
              try {
                Singer  sg;
                String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
                  String name = firstMultipleInput[0];
                  String genre = firstMultipleInput[1];
                  int budget = Integer.parseInt(firstMultipleInput[2]);
                  String applying = firstMultipleInput[3];
                  boolean willApply = false;
                  if (applying.equals("Yes"))
                      willApply = true;
                  if (firstMultipleInput.length ==4){
                    sg = new Singer(name, genre, budget,min, willApply);
                    System.out.println("------\nSinger\n------\n"+sg);
                 }
                else
                {
                       int fav = Integer.parseInt(firstMultipleInput[4]);
                       sg = new Singer(name, genre, budget,fav, min, willApply); 
                       System.out.println("------\nSinger\n------\n"+sg);
                }
                int numSongs = Integer.parseInt(bufferedReader.readLine().trim());
                for (int snum=0; snum<numSongs;snum++)
                {
                      
                       sg.addSong(bufferedReader.readLine());
                  }//for anum
                  if (sg.getSongs().size()>0)
                      System.out.println("-------\nSong(s)\n-------\n");
                 for(Song s:sg.getSongs())
                     System.out.println(s);
                 if (summary &&(sg.sumEstValue()>0)){
                    System.out.println("-------------------------------------------");
                    System.out.println("Total earnable for "+sg.getName()+" = $"+String.format("%,d",sg.sumEstValue()));
                 }
                 
                  if (sg.applying()){
                       sg.applyForGrant();
                 }
                 numGrants+=(sg.getGrantValue()>0?1:0); 
              } catch (IOException ex) {
                  throw new RuntimeException(ex);
              }
          }
        
       bufferedReader.close();
        if (numGrants>0)
        {
           System.out.println("=====================GRANTS AWARDED========================");
            min.showAwarded(true);
        }
        if (numGrants<min.countSingers())
            {
             System.out.println("====================APPLICATIONS NOT GRANTED====================");
             min.showAwarded(false);
            }
      }
  
      
  }
  
