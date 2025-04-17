import java.time.LocalDate;

/**
 * Represents a task with a title, type, deadline, and completion status.
 * Tasks can have different types (Personal, Work, Chores, Health) and
 * track their deadline relative to the current date.
 */
public class Task {
    private String title;
    private String type;
    private int daystoDeadline;
    private LocalDate dueDate; // java date API
    private int urgency; // from 1-5
    private boolean completed;
    
    
    /**
     * Creates a new task with the specified details.
     *
     * @param title The task title
     * @param type The task type (Personal, Work, Chores, Health)
     * @param daystoDeadline Number of days until the deadline
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
     * @return The task's title
     */
    public String getTitle() { return title; }

    /**
     * @return The task's type category
     */
    public String getType() { return type; }

    /**
     * Calculates the number of days remaining until the deadline.
     *
     * @return Number of days until the deadline
     */
    public int daysRemaining() {
        return (int) LocalDate.now().until(dueDate).getDays();
    }

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
    
    
    @Override
    public String toString() {
        return "Task{" +
        "title='" + title + '\'' +
        ", type='" + type + '\'' +
        ", daystoDeadline=" + daystoDeadline +
        '}';
    }
}
