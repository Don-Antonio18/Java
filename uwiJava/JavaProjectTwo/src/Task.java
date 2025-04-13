import java.time.LocalDate;
import java.util.Scanner;

public class Task {
    private String title;
    private String type;
    private int daystoDeadline;
    private LocalDate dueDate; // java date API
    private int urgency; // from 1-5
    private boolean completed;


    //constructors
    public Task(String title, String type, int daystoDeadline){
        this.title = title;
        this.type = type;
        this.daystoDeadline = daystoDeadline;
        
        //! Ant - adds deadline date to current date
        this.dueDate = LocalDate.now().plusDays(daystoDeadline);
        this.completed = false; // tasks false by default
    }
    

    // getters and setters
    public String getTitle() { return title; }
    public String getType() { return type; }
    
    //! Ant - calculates deadline date
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
                ", dueDate=" + dueDate + // Ant
                '}';
    }
}

