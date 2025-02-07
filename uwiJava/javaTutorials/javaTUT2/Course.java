package Java.uwiJava.javaTutorials.javaTUT2;

public class Course {
    private String code;
    private int numCredits;
    private int level;
    public Course(String code, int level, int numCredits){
    this.code = code;
    this.numCredits = numCredits;
    this.level = level;
    }
    public String getCode(){
     return code;
     }
    public int getCredits() {
    return numCredits;
    }
    public int getLevel() {
    return level;
    }
    }
    public class Student {
    private String name;
    public String getName() {
    return name;
    }
    }  
}
