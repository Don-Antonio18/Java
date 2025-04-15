import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Manages the creation and display of flash cards through a graphical interface.
 * Provides functionality for creating and reviewing flash cards.
 */
public class FlashCardManager {
	private ArrayList<FlashCard> flashcards;
	private JTextArea question;
	private JTextArea answer;
	private JFrame frame;
	
	/**
	 * Creates a new FlashCardManager window with interface for managing flash cards.
	 * Initializes the GUI components and sets up the layout.
	 */
	public FlashCardManager() {
		frame = new JFrame("Study Wise Flash Card Builder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// stores components
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		// font and word styling
		Font font = new Font("Arial", Font.BOLD, 16);
		question = new JTextArea(6,20);
		question.setLineWrap(true); // wraps text if too long
		question.setWrapStyleWord(true);
		question.setFont(font);
		
		// question area
		JScrollPane questionScrollPane = new JScrollPane(question);
		questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		// answer area
		answer = new JTextArea(6,20);
		answer.setLineWrap(true);
		answer.setWrapStyleWord(true);
		answer.setFont(font);
		JScrollPane answerScrollPane = new JScrollPane(answer);
		answerScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		answerScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		//implement scrollability
		JScrollPane scrollPane = new JScrollPane(answer);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		JButton nextButton = new JButton("Next Card");
		
		// create labels
		JLabel questionLabel = new JLabel("Question");
		questionLabel.setHorizontalTextPosition(JLabel.CENTER);
		questionLabel.setVerticalTextPosition(JLabel.CENTER);
		JLabel answerLabel = new JLabel("Answer");
		answerLabel.setHorizontalTextPosition(JLabel.CENTER);
		answerLabel.setVerticalTextPosition(JLabel.CENTER);
		
		// add JComponents to main panel
		mainPanel.add(questionLabel);
		mainPanel.add(questionScrollPane);
		mainPanel.add(answerLabel);
		mainPanel.add(answerScrollPane);
		mainPanel.add(nextButton);
		nextButton.addActionListener(new NextCardButtonListener() );
		
		
		// add main panel to frame
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(400,500);
		frame.setVisible(true);
	}
	
	/**
	 * Main entry point for running the FlashCardManager as a standalone application.
	 * @param args Command line arguments (not used)
	 */
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() { // runs the program
				new FlashCardManager();
			}
		});
		
	}
	class NextCardButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Next Card");
		}
	}
	
	/**
	 * Creates and displays the FlashCardManager GUI.
	 * Can be called from other parts of the application.
	 */
	public static void createAndShowGUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new FlashCardManager();
			}
		});
	} // allows flashcard window to be opened from the main menu
	
	
	
}


class NextMenuButtonListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Enter Menu");
	}
}

