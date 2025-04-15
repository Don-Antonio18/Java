import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class FlashCardBuilder {
	private ArrayList<FlashCard> flashcards;
	private JTextArea question;
	private JTextArea answer;
	private JFrame frame;
	
	
	
	
	
	public FlashCardBuilder() {
		frame = new JFrame("Study Wise Flash Card Builder");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// stores components
		JPanel mainPanel = new JPanel();
		
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
		JLabel answerLabel = new JLabel("Answer");
		
		// add JComponents to main panel
		mainPanel.add(questionLabel);
		mainPanel.add(questionScrollPane);
		mainPanel.add(answerLabel);
		mainPanel.add(answerScrollPane);
		mainPanel.add(nextButton);
		
		// add main panel to frame
		frame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		frame.setSize(450,550);
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() { // runs the program
				new FlashCardBuilder();
			}
		});
		
	}
}
