import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents a task with a title, type, deadline, and completion status.
 * Tasks can be managed and tracked through the task management system.
 */
public class Task {
    private String title;
    private String type;
    private int daystoDeadline;
    private LocalDate dueDate; // java date API
    private int urgency; // from 1-5
    private boolean completed;
    
    /**
     * Creates a new task with specified title, type and days until deadline.
     * @param title The title of the task
     * @param type The type/category of the task
     * @param daystoDeadline Number of days until the task deadline
     */
    public Task(String title, String type, int daystoDeadline){
        this.title = title;
        this.type = type;
        this.daystoDeadline = daystoDeadline;
        //! Ant - adds deadline date to current date
        this.dueDate = LocalDate.now().plusDays(daystoDeadline);
        this.completed = false; // tasks false by default
        
    }
    
    // user shouldn't need to set completed to false when adding tasks, should be by default
    
    /**
     * @return The title of the task
     */
    public String getTitle() { return title; }
    
    /**
     * @return The type/category of the task
     */
    public String getType() { return type; }
    
    /**
     * Converts task type to numeric value for sorting
     * @return integer value representing task type priority
     */
    public int getTypeValue() {
        switch(this.type.toLowerCase()) {
            case "health": return 1;
            case "work": return 2;
            case "chores": return 3;
            case "personal": return 4;
            default: return 5;
        }
    }
    
    /**
     * Calculates the number of days remaining until the task deadline.
     * @return Number of days until deadline
     */
    public int daysRemaining() {
        return (int) LocalDate.now().until(dueDate).getDays();
    }
    
    /**
     * Updates the deadline of the task.
     * @param daystoDeadline New number of days until deadline
     */
    public void setDaystoDeadline(int daystoDeadline) {
        this.daystoDeadline = daystoDeadline;
        this.dueDate = LocalDate.now().plusDays(daystoDeadline);
    }
    
    public LocalDate getdueDate() { return dueDate; }
    public int getUrgency() { return urgency; }
    public boolean isCompleted() { return completed; }
    
    public void setTitle(String title) {this.title = title;}
    public void setdueDate(LocalDate dueDate) {this.dueDate = dueDate;}
    public void setUrgency(int urgency) { this.urgency = urgency; }
    public void setCompleted(boolean completed) { this.completed = completed;}
    public void setType(String type) { this.type = type; }
    
    @Override
    public String toString() {
        return "Task{" +
        "title='" + title + '\'' +
        ", type='" + type + '\'' +
        ", daystoDeadline=" + daystoDeadline +
        '}';
    }
}

