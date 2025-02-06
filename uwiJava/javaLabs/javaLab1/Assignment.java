public class Assignment {
    private String assignmentName;
    private int effort;
    private int numOfResources;
    private int estimatedDifficulty;
    private double expectedScore;

    public Assignment(String name, int effort, int resources, int difficulty) {
        this.assignmentName = name;
        this.effort = effort;
        this.numOfResources = resources;
        this.estimatedDifficulty = difficulty;
        this.expectedScore = calcScore();
    }

    private double calcScore() {
        return 0.1 * effort * estimatedDifficulty + 
               Math.pow(numOfResources * effort, 2) / 
               (Math.PI * Math.sqrt(estimatedDifficulty));
    }

    public int getEffort() { return effort; }
    public int getNumResources() { return numOfResources; }
    public int getDifficulty() { return estimatedDifficulty; }
    public double getScore() { return expectedScore; }
    public String getName() { return assignmentName; }
}