import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Main menu interface for the StudyWise application.
 * Provides access to Task Manager and Flashcard functionalities.
 */
public class MainMenu extends JFrame {
	private JButton     cmdTaskBtn;
	private JButton     cmdflashcardBtn;
	private JButton     cmdexitBtn;
	private JPanel      pnlCommand;
	
	/**
     * Creates and initializes the main menu window with navigation buttons.
     */
	public MainMenu() {
		setTitle("StudyWise - Main Menu");
		setSize(400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		pnlCommand = new JPanel();
		pnlCommand.setLayout(new GridLayout(3, 1, 10, 10));
		
		// Buttons
		JButton cmdTaskButton = new JButton("Task Manager");
		JButton cmdFlashCardButton = new JButton("Flashcards");
		JButton cmdExitButton = new JButton("Exit");
		
		// Action Listeners
		cmdTaskButton.addActionListener(new TaskButtonListener());
		cmdFlashCardButton.addActionListener(new FlashCardButtonListener());
		cmdExitButton.addActionListener(new ExitButtonListener());
		
		//color
		cmdTaskButton.setBackground(new Color(255, 178, 230));  // pink
		cmdTaskButton.setForeground(Color.WHITE);
		
		cmdFlashCardButton.setBackground(new Color(114, 221, 247));  // Light Blue
		cmdFlashCardButton.setForeground(Color.WHITE);
		
		cmdExitButton.setBackground(new Color(254, 74, 73)); // red
		cmdExitButton.setForeground(Color.BLACK);
		
		cmdTaskButton.setOpaque(true);
		cmdFlashCardButton.setOpaque(true);
		cmdExitButton.setOpaque(true);
		
		cmdTaskButton.setBorderPainted(false);
		cmdFlashCardButton.setBorderPainted(false);
		cmdExitButton.setBorderPainted(false);
		
		cmdTaskButton.setFocusPainted(false);
		cmdFlashCardButton.setFocusPainted(false);
		cmdExitButton.setFocusPainted(false);
		
		// Add buttons to panel
		pnlCommand.add(cmdTaskButton);
		pnlCommand.add(cmdFlashCardButton);
		pnlCommand.add(cmdExitButton);
		
		add(pnlCommand);
		setVisible(true);
	}
	
	/**
     * Listener for the Task Manager button.
     * Opens the task management interface.
     */
	private class TaskButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			TaskManager.createAndShowGUI();
		}
	}

	/**
     * Listener for the Flashcard button.
     * Opens the flashcard management interface.
     */
	private class FlashCardButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			FlashCardManager createFlashCards = new FlashCardManager(); // open the flashcard GUI
		}
	}
	
	/**
     * Listener for the Exit button.
     * Terminates the application.
     */
	private class ExitButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
	
	/**
     * Creates and displays the main menu window.
     */
	public static void createAndShowGUI() {
		new MainMenu();
	}
}
