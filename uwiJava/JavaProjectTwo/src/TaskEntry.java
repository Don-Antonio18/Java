import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

/**
 * Provides a form interface for creating new tasks.
 * Allows users to input task details including title, type, and deadline.
 * Integrates with TaskManager to add new tasks to the system.
 */
public class TaskEntry extends JFrame{
    public TaskManager taskman;
    private TaskEntry thisEntry = this;
    
    private String[] taskTypes = new String[4];//stores the possible task types
    
    private JTextField  taskName;       //name
    private JTextField  taskDate;        //age
    private JComboBox   taskcategory;
    private JButton     cmdSave;
    private JButton     cmdClose;
    private JButton     cmdClearAll;
    private JOptionPane jpane;
    
    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    
    public TaskEntry(TaskManager tm)
    {
        this.taskman = tm;
        setTitle("Entering new task");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.setLayout(new GridLayout(5,1));
        
        //Entering the title
        pnlDisplay.add(new JLabel("Title:"));
        taskName = new JTextField(20);
        pnlDisplay.add(taskName);
        
        //Selecting the task type
        pnlDisplay.add(new JLabel("Task Type:"));
        taskTypes[0] = "Personal";
        taskTypes[1] = "Work";
        taskTypes[2] = "Chores";
        taskTypes[3] = "Health";
        
        //this.taskTypes = {"Personal", "Work", "Chores", "Health"};
        taskcategory = new JComboBox<String>(taskTypes);
        taskcategory.setSelectedIndex(0);
        pnlDisplay.add(taskcategory);
        
        //Entering the days left till deadline
        pnlDisplay.add(new JLabel("Days left till deadline:"));
        taskDate= new JTextField(3);
        pnlDisplay.add(taskDate);
        //LocalDate dueDate = LocalDate.parse(taskDate.getText(), DateTimeFormatter.BASIC_ISO_DATE);
        
        cmdSave    = new JButton("Save");
        cmdClose   = new JButton("Close");
        cmdSave.setBackground(new Color(255,255,255));// sets default button color to white
        cmdClose.setBackground(new Color(255,255,255));// sets default button color to white
        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);
        
        
        cmdSave.addMouseListener(new SaveButtonListener());// i changed it such that cmdSave uses the savebutton listener instead of cmd close.
        cmdClose.addMouseListener(new CloseButtonListener());
        
    }
    
    private class SaveButtonListener implements MouseListener
    {
        public void mouseClicked(MouseEvent e)// once the button is clicked, the code is executed, similar to action listener but mouse listener gives us more flexibility(as you can see with additional methods)
        {
            String title = taskName.getText();
            String type = taskTypes[taskcategory.getSelectedIndex()];
            int daysLeft = Integer.parseInt(taskDate.getText());
            Task t = new Task(title, type, daysLeft);
            taskman.addToTable(t);
            thisEntry.setVisible(false);
        }
        @Override
        public void mouseEntered(MouseEvent e) {
            cmdSave.setBackground(new Color(0,255,0));
        }
        @Override
        public void mouseExited(MouseEvent e){
            cmdSave.setBackground(new Color(255,255,255));
        }
        @Override
        public void mouseReleased(MouseEvent e){
        
        
        }
        @Override
        public void mousePressed(MouseEvent e){
        
        }
        
        
    }
    
    private class CloseButtonListener implements MouseListener
    {
        
        @Override
        public void mouseEntered(MouseEvent e) {
            cmdClose.setBackground(new Color(255,0,0));
        }
        @Override
        public void mouseExited(MouseEvent e){
            cmdClose.setBackground(new Color(255,255,255));
        }
        @Override
        public void mouseReleased(MouseEvent e){
        
        
        }
        @Override
        public void mouseClicked(MouseEvent e){// has the same result as action listener but grants us more flexibility
            thisEntry.setVisible(false);
        }
        @Override
        public void mousePressed(MouseEvent e){
        
        }
        
        
    }
    
}
