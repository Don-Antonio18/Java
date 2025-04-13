import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TaskEntry extends JFrame{
    private TaskManager taskList;
    private TaskEntry thisEntry = this;

    private String[] taskTypes;//stores the possible task types

    private JTextField  taskName;       //name
    private JTextField  taskDate;        //age
    private JComboBox   taskcategory;
    private JButton     cmdSave;
    private JButton     cmdClose;
    private JButton     cmdClearAll;
    private JOptionPane jpane;

    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;

    public TaskEntry()
    {
        setTitle("Entering new task");
        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();
        pnlDisplay.setLayout(new GridLayout(5,1));

        //Entering the title
        pnlDisplay.add(new JLabel("Title:"));
        taskName = new JTextField(20);
        pnlDisplay.add(taskName);

        //Selecting the task type
        //pnlDisplay.add(new JLabel("Task Type:"));
        String[] taskTypes = {"Personal", "Work", "Chores", "Health"};
        taskcategory = new JComboBox<String>(taskTypes);
        taskcategory.setSelectedIndex(3);
        pnlDisplay.add(taskcategory);

        //Entering the days left till deadline
        pnlDisplay.add(new JLabel("Days left till deadline:"));
        taskDate= new JTextField(3);
        pnlDisplay.add(taskDate);
        //LocalDate dueDate = LocalDate.parse(taskDate.getText(), DateTimeFormatter.BASIC_ISO_DATE);

        cmdSave    = new JButton("Save");
        cmdClose   = new JButton("Close");

        pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);
        add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);
        pack();
        setVisible(true);

        cmdClose.addActionListener(new SaveButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());
    }
    
    
    private class SaveButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String title = taskName.getText();
            String type = taskTypes[taskcategory.getSelectedIndex()];
            int daysLeft = Integer.parseInt(taskDate.getText());
            Task p = new Task(title, type, daysLeft);
        }

    }

    private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            thisEntry.setVisible(false);
        }

    }

}

