import java.util.ArrayList;
public class School {
    private String name;
    private ArrayList<Integer>gradReqs;
    
    

    public School(String name, int[] reqs) {
        this.name = name;
        gradReqs =  new ArrayList<Integer>();
        for (int req:reqs)
            gradReqs.add((req));
    }
    public String getName(){
        return name;
    }
    public int getReqForLevel(int level){
        return gradReqs.get(level);
    }
    public ArrayList<Integer> getReqs(){
        return gradReqs;
    }
}


