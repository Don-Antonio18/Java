package Java.uwiJava.javaTutorials.javaTUT2;

import java.util.ArrayList;

public class School {
    private String name;
    private ArrayList<Integer> gradReqs; // arrayLists are mutable

    public School(String name, int[] reqs) {
        this.name = name;
        gradReqs = new ArrayList<Integer>();
        for (int req : reqs)
            gradReqs.add(Integer.valueOf(req));
    }

    public String getName() {
        return name;
    }

    public int getReqForLevel(int level) {
        return gradReqs.get(level);
    }

    public ArrayList<Integer> getReqs() {
        return gradReqs;
    }
}
