
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.Collections;
import java.awt.Color;


public class TaskManager extends JPanel {
    //buttons for task functions
    private JButton     cmdAddTask;
    private JButton     cmdEditTask;
    private JButton     cmdDeleteTask;
    private JButton     cmdMarkComplete;
    private JButton     cmdClose;
    private JButton     cmdSortByUrgency;
    
    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;

    private ArrayList<Task> taskList;
    private TaskManager thisManager; //a reference to the current task manger
    private  JScrollPane scrollPane;

    private JTable table;
    private DefaultTableModel model;

    public TaskManager(){
        super(new GridLayout(2,1));
        thisManager = this;

        pnlCommand = new JPanel();
        pnlDisplay = new JPanel();


        //all of this shows the tasks
        taskList = loadTasks("Tasks.txt");
        String[] columnNames=  {"Title",
                "Type",
                "Days till Deadline",
                };
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        showTable(taskList);

        table.setPreferredScrollableViewportSize(new Dimension(500, taskList.size()*15 +50));
        table.setFillsViewportHeight(true);

        scrollPane = new JScrollPane(table);

        add(scrollPane);
        add(pnlCommand);
        
        //! Antonio - add button to sort by due date
        JButton cmdSortByDueDate = new JButton("Sort by Due Date");
        pnlCommand.add(cmdSortByDueDate);
        //! Antonio - implement button listener
        cmdSortByDueDate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortByDueDate();
            }
        });
    }

    //loads all saved Tasks from a file
    private ArrayList<Task> loadTasks(String tfile)
    {
        Scanner tscan = null;
        ArrayList<Task> tlist = new ArrayList<Task>();
        try
        {
            tscan  = new Scanner(new File(tfile));
            while(tscan.hasNext())
            {
                String [] nextLine = tscan.nextLine().split(" ");
                String name = nextLine[0];
                System.out.println(name);
                String type = nextLine[1];
                int daystoDeadline = Integer.parseInt(nextLine[2]);
                Task t = new Task(name, type, daystoDeadline);
                System.out.println(t);
                tlist.add(t);
            }
            tscan.close();
        }
        catch(IOException e)
        {
            System.out.println("File not Found");
        }
        return tlist;
    }

    private void showTable(ArrayList<Task> tlist)
    {
        if (tlist.size()>0) {
            for (Task t : tlist)
                addToTable(t);
        }
    }

    private void addToTable(Task t)
    {
        String[] item={t.getTitle(), t.getType(), ""+t.daysRemaining()};
        model.addRow(item);

    }

    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("List of pending tasks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        TaskManager newContentPane = new TaskManager();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    //! Antonio - sorting based on due date
    public void sortByDueDate () {
        Collections.sort(taskList, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.getdueDate().compareTo(task2.getdueDate());
            }
        });
                //!  ant - refresh the table
        model.setRowCount(0);
        showTable(taskList);
    }
    
    // Period result = Period.between(LocalDate.of(2024, 12, 1), LocalDate.of(2023, 10, 1));
    //System.out.println(result); // Output: P-1Y-2M
    
    /*
import java.time.LocalDate;
import java.time.Period;

public class DeadlineCalculator {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now(); // Current date (assumed: 12/17/2024)
        LocalDate deadline = LocalDate.of(2025, 12, 31); // Project deadline

        Period remaining = Period.between(today, deadline);

        System.out.println("Time remaining: " + remaining.getYears() + " years, "
            + remaining.getMonths() + " months, "
            + remaining.getDays() + " days.");
    }
}
     */
}



