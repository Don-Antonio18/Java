import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.swing.table.*;
import javax.swing.table.DefaultTableModel;
import java.util.Comparator;
import java.util.Collections;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.time.LocalDate;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

/**
 * Manages the task management system through a graphical user interface.
 * Provides functionality for creating, editing, deleting, and viewing tasks.
 * Tasks are stored persistently in a file and can be loaded/saved between sessions.
 *
 * Features:
 * - Add new tasks with title, type, and deadline
 * - Edit existing task details
 * - Delete tasks
 * - View all tasks in a sortable table
 * - Persistent storage of tasks in a text file
 * - Integration with main menu system
 */
public class TaskManager extends JPanel{
    //buttons for task functions
    private JButton     cmdAddTask;
    private JButton     cmdEditTask;
    private JButton     cmdDeleteTask;
    private JButton     cmdMarkComplete;
    private JButton     cmdSortByDueDate;
    private JButton     cmdSortByCategory;
    private JButton     cmdClose;
    private JButton     cmdSortByUrgency;
    
    
    private JPanel      pnlCommand;
    private JPanel      pnlDisplay;
    
    private ArrayList<Task> tasklist;
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
        tasklist = loadTasks("Tasks.txt");
        String[] columnNames= {"Title",
        "Type",
        "Days till Deadline"
        };
        model=new DefaultTableModel(columnNames,0);
        table = new JTable(model);
        showTable(tasklist);
        table.setCellSelectionEnabled(true);
        
        table.setPreferredScrollableViewportSize(new Dimension(500, tasklist.size()*15 +50));
        table.setFillsViewportHeight(true);
        
        scrollPane = new JScrollPane(table);
        
        add(scrollPane);
        add(pnlCommand);
        ListSelectionModel selectionModel = table.getSelectionModel();
        
        cmdAddTask  = new JButton("Add Task");
        cmdEditTask = new JButton("Edit Task");
        cmdSortByDueDate  = new JButton("Sort By Due Date");
        cmdSortByCategory = new JButton("Sort By Category");
        cmdDeleteTask = new JButton("Delete Task");
        cmdSortByDueDate = new JButton("Sort by Due Date");
        pnlCommand.add(cmdSortByDueDate);
        //cmdSortByDueDate.addActionListener(new SortByDateListener());
        
        
        cmdClose  = new JButton("Back to Main Menu");
        
        cmdAddTask.addActionListener(new AddButtonListener());
        cmdClose.addActionListener(new CloseButtonListener());
        cmdEditTask.addActionListener(new EditButtonListener());
        cmdSortByDueDate.addActionListener(new SortByDueDateListener());
        cmdSortByCategory.addActionListener(new SortByCategoryListener());
        cmdDeleteTask.addActionListener(new DeleteButtonListener());
        table.addMouseListener(new TaskOptionsListener());
        
        pnlCommand.add(cmdAddTask);
        pnlCommand.add(cmdEditTask);
        pnlCommand.add(cmdSortByDueDate);
        pnlCommand.add(cmdSortByCategory);
        pnlCommand.add(cmdDeleteTask);
        pnlCommand.add(cmdClose);
        
        setVisible(true);
        
    }
    
    //? ----------- IMPLEMENTATION OF TASK SORTING  ----------------
    
    //! Sorting by Due Date
    
    private class DueDateComparator implements Comparator<Task>{
        @Override
        public int compare(Task task1, Task task2) {
            return task1.getdueDate().compareTo(task2.getdueDate());
        }
    }
    
    private void sortByDueDate(){
        Collections.sort(tasklist, new DueDateComparator());
        refreshTable();
    }
    
    
    //! Sorting by Task Type
    
    private class sortByCategoryComparator implements Comparator<Task> {
        @Override
        public int compare(Task task1, Task task2) {
            return task1.getType().compareTo(task2.getType());
        }
    }
    
    private void sortByCategory(){
        Collections.sort(tasklist, new sortByCategoryComparator());
        refreshTable();
    }
    
    public void addTask (Task t){
        tasklist.add(t);
    }
    
    //loads all saved Tasks from a file
    private ArrayList<Task> loadTasks(String tfile) {
        ArrayList<Task> tlist = new ArrayList<Task>();
        File file = new File(tfile);
        
        // Create empty file if it doesn't exist
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Created new tasks file: " + tfile);
            } catch (IOException e) {
                System.out.println("Error creating tasks file: " + e.getMessage());
            }
            return tlist; // Return empty list for new file
        }
        
        // Existing file loading logic
        Scanner tscan = null;
        try {
            tscan = new Scanner(file);
            while(tscan.hasNext()) {
                String[] nextLine = tscan.nextLine().split(" ");
                String name = nextLine[0];
                String type = nextLine[1];
                int daystoDeadline = Integer.parseInt(nextLine[2]);
                Task t = new Task(name, type, daystoDeadline);
                tlist.add(t);
            }
            tscan.close();
        } catch(IOException e) {
            System.out.println("Error reading tasks file: " + e.getMessage());
        }
        return tlist;
    }
    
    private void saveTasks(ArrayList<Task> tasks, String taskFile) {
        try {
            // Ensure parent directory exists
            File file = new File(taskFile);
            file.getParentFile().mkdirs(); // Creates parent directories if needed
            
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Task task : tasks) {
                    String formattedTitle = task.getTitle().replace(" ", "_");
                    String taskType = task.getType();
                    String formattedDate = task.daysRemaining() + "";
                    writer.write(formattedTitle + " " + taskType + " " + formattedDate);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
            "Failed to save tasks to file",
            "Save Error",
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void showTable(ArrayList<Task> tlist)
    {
        if (tlist.size()>0) {
            for (Task t : tlist)
                addToTable(t);
        }
    }
    
    public void addToTable(Task t)
    {
        String[] item={t.getTitle(), t.getType(), ""+t.daysRemaining()};
        model.addRow(item);
        
    }
    
    private void refreshTable() {
        model.setRowCount(0); // Clear the table
        for (Task t : tasklist) {
            addToTable(t);
        }
    }
    
    public static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("List of pending tasks");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        //Create and set up the content pane.
        TaskManager newContentPane = new TaskManager();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
    
    private class AddButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            TaskEntry te = new TaskEntry(thisManager);
            System.out.println("Step 1");
        }
    }
    
    private class TaskOptionsListener implements MouseListener{
        public void mouseClicked(MouseEvent e){
            if (SwingUtilities.isRightMouseButton(e)){
            
            }
        }
        
        @Override
        public void mouseEntered(MouseEvent e) {
        
        }
        @Override
        public void mouseExited(MouseEvent e){
        
        }
        @Override
        public void mouseReleased(MouseEvent e){
        
        
        }
        @Override
        public void mousePressed(MouseEvent e){
        
        }
    }
    
    
    private class EditButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                // Get the selected task
                Task selectedTask = tasklist.get(selectedRow);
                
                // Create edit dialog
                JDialog editDialog = new JDialog((Frame)null, "Edit Task", true);
                editDialog.setLayout(new GridLayout(0, 2, 5, 5));
                
                // Create form fields with current values
                JTextField txtTitle = new JTextField(selectedTask.getTitle().replace("_", " "));
                JComboBox<String> cmbType = new JComboBox<>(new String[]{"Health", "Personal", "Work", "Chores"});
                cmbType.setSelectedItem(selectedTask.getType());
                JSpinner spnDays = new JSpinner(new SpinnerNumberModel(selectedTask.daysRemaining(), 1, 365, 1));
                
                // Add components to dialog
                editDialog.add(new JLabel("Task Title:"));
                editDialog.add(txtTitle);
                editDialog.add(new JLabel("Task Type:"));
                editDialog.add(cmbType);
                editDialog.add(new JLabel("Days Until Due:"));
                editDialog.add(spnDays);
                
                // Add save and cancel buttons
                JButton cmdSave = new JButton("Save");
                JButton cmdCancel = new JButton("Cancel");
                
                cmdSave.addActionListener(ev -> {
                    // Get updated values
                    String title = txtTitle.getText().trim();
                    String type = (String)cmbType.getSelectedItem();
                    int days = (Integer)spnDays.getValue();
                    
                    if (title.isEmpty()) {
                        JOptionPane.showMessageDialog(editDialog,
                        "Please enter a task title",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    // Update the task
                    Task updatedTask = new Task(title, type, days);
                    tasklist.set(selectedRow, updatedTask);
                    
                    // Update the table
                    model.removeRow(selectedRow);
                    addToTable(updatedTask);
                    
                    editDialog.dispose();
                });
                
                cmdCancel.addActionListener(ev -> editDialog.dispose());
                
                editDialog.add(cmdSave);
                editDialog.add(cmdCancel);
                
                editDialog.pack();
                editDialog.setLocationRelativeTo(thisManager);
                editDialog.setVisible(true);
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(
                thisManager,
                "Please select a task to edit",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    private class SortByDueDateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sortByDueDate();
        }
    }
    
    private class SortByCategoryListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sortByCategory();
        }
        
    }
   
    private class DeleteButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                // Confirm deletion with user
                int confirm = JOptionPane.showConfirmDialog(
                thisManager,
                "Are you sure you want to delete this task?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    // Remove from both the ArrayList and the table model
                    tasklist.remove(selectedRow);
                    model.removeRow(selectedRow);
                    
                    JOptionPane.showMessageDialog(
                    thisManager,
                    "Task deleted successfully",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(
                thisManager,
                "Please select a task to delete",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            }
            refreshTable();
        }
    }
    
        
        
        
        private class CloseButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            saveTasks(tasklist, "Tasks.txt");
            thisManager.setVisible(false);
        }
    }
    
   
    
}
