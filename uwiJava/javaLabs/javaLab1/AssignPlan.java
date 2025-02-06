import java.text.DecimalFormat;

//Implement the AssignPlan class here


class AssignPlan{
  private String highestPriorityItem="";
  private int numAssignments, numComplete, hoursAvailable;
  private double totalPoints;
  DecimalFormat df = new DecimalFormat("#.##");
  
  //Question 1. Properly implement accessor methods for the private variables 
  // highestPriorityItem, numAssignments, numComplete, hoursAvailable, totalPoints. 
  
  //! METHODS 
  private String getHighestPriorityItem()   {return highestPriorityItem;}
  public int getNumAssignments()            {return numAssignments;}
  public int getNumComplete()               {return numComplete;}
  public int getHoursAvailable()            {return hoursAvailable;}
  public double getTotalPoints()            {return totalPoints;}

  public String toString()  {

    return "Priority:"+getHighestPriorityItem()+"\n"
     + "Assignments:"+getNumAssignments()+"\n"
     + "Complete:"+getNumComplete()+"\n"
     + "Hours Avail:"+getHoursAvailable()+"\n"
     + "Tot. Points:"+ df.format(getTotalPoints())+"\n";
  }
  
  // CREATES INSTANCE OF ASSIGNPLAN
   public AssignPlan(){   
  }
  

 public AssignPlan(int numAssigns){   
     numAssignments = numAssigns;
  }
  
  
  //Question 2. Implement an alternate constructor for AssignPlan
  
   public AssignPlan(String highestPriority, int numberOfAssigns, int numberComplete, int hoursAvail, double totPoints){
         //complete this constructor
       
       this.highestPriorityItem = highestPriority;
       this.numAssignments = numberOfAssigns;
       this.numComplete = numberComplete;
       this.hoursAvailable = hoursAvail;
      this.totalPoints = totPoints;
          
  }


    //Question 3.	Implement a mutator function to set the highestPriorityItem to the name passed in
  public void setPriorityTo(String aname){
      
      highestPriorityItem = aname;
  }
  
 public void setAssigns(int numAssigns){
     numAssignments = numAssigns;
 }



 //! Question 4
//The Assignment class will privately store information on the effort (hours required to complete)(int),
// the number of resources to be referenced (int), the estimated difficulty(int), and the expected score(double). 
//in Assignment, create a method called calcScore that evaluates the score for a difficult assignment using the formula in the .pdf
//Create a constructor for an Assignment, that accepts the name, effort, resources and difficulty. 
// The constructor then evaluates and stores the expected score of the asignment,

public static class Assignment {
    private String assignmentName;
    private int effort;
    private int numOfResources;
    private int estimatedDifficulty;
    private double expectedScore;



    // Constructor for assignment
    public Assignment(String name, int effort, int resources, int difficulty) {
      this.assignmentName = name;
      this.effort = effort;
      this.numOfResources = resources;
      this.estimatedDifficulty = difficulty;
      this.expectedScore = calcScore();
    }

    // Method to calculate score
    private double calcScore() { 
      return 0.1 * effort * estimatedDifficulty + 
            Math.pow(numOfResources * effort, 2) / 
            (Math.PI * Math.sqrt(estimatedDifficulty));
    }


  //? ACCESSOR METHODS
  public String getName()         {return assignmentName;}
  public int getEffort()          {return effort;}
  public int getnumOfResources()  {return numOfResources;} 
  public int getDifficulty()      {return estimatedDifficulty;}
  public double getScore()        {return expectedScore;}
  }


//! Question 5.    Complete the handleAssignment method of the AssignPlan object so that it
public void handleAssignment (int effort){

//   a.    reduces the hours available by the effort;
      hoursAvailable -= effort;
//        b.    increments the totalPoints by 1 
      totalPoints += 1;
//        c.    increments numComplete by 1
      numComplete += 1;
  }
    
//! Question 6.    Complete the getUrgentAssignment method of the AssignPlan object so that it
//        a.    Creates an Assignment with knowledge of the difficulty, resources and effort
//        b.    Update the hoursAvailable by subtracting the effortHours 
//        c.    Update the total points by adding the score calculated on the assignment.''
//        d.    increments the number of complete assignments by 1
//        e.    set the priority of the assignment plan to the name of this assignment      


public void handleUrgentAssignment(String name, int effort, int resources, int difficulty) {
  // Create assignment with knowledge of difficulty, resources and effort
  Assignment urgentAssignment = new Assignment(name, effort, resources, difficulty);

  hoursAvailable -= urgentAssignment.getEffort();

  totalPoints += urgentAssignment.getScore(); 

  numComplete += 1;  
  
  setPriorityTo(urgentAssignment.getName());  

  }
}   



