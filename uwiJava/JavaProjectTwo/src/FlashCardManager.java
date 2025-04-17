import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * A graphical user interface for managing flashcards.
 * This class provides functionality to create, review, and rate flashcards
 * to help with memorization and learning.
 * 
 * Features include:
 * - Adding new flashcards with questions and answers
 * - Reviewing flashcards in a spaced repetition system
 * - Rating recall effectiveness from 1-5
 * - Persistent storage of flashcards and their ratings
 * - Prioritization of flashcards based on recall ratings
 */
public class FlashCardManager extends JFrame {
	
	private ArrayList<FlashCard> flashcards = new ArrayList<>();
	private int currentIndex = 0;
	
	// GUI Components
	private JTextField questionInput;
	private JTextField answerInput;
	private JLabel questionLabel;
	private JLabel answerLabel;
	private JButton showAnswerButton;
	private JPanel reviewPanel;
	private final String FILE_NAME = "flashcards.txt";
	
    /**
     * Constructs a new FlashCardManager window.
     * Initializes the GUI components and loads any previously saved flashcards.
     */
	public FlashCardManager() {
		setTitle("FlashLearn Pro");
		setSize(550, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		
		loadFlashCardsFromFile();
		
		// --- Top Buttons ---
		JButton addCardButton = new JButton("Add FlashCard");
		JButton reviewButton = new JButton("Review FlashCards");
		JPanel topPanel = new JPanel();
		topPanel.add(addCardButton);
		topPanel.add(reviewButton);
		add(topPanel, BorderLayout.NORTH);
		
		// --- Add FlashCard Panel ---
		JPanel addPanel = new JPanel(new GridLayout(5, 1));
		questionInput = new JTextField();
		answerInput = new JTextField();
		JButton saveButton = new JButton("Save FlashCard");
		
		addPanel.add(new JLabel("Enter Question:"));
		addPanel.add(questionInput);
		addPanel.add(new JLabel("Enter Answer:"));
		addPanel.add(answerInput);
		addPanel.add(saveButton);
		
		// --- Review Panel ---
		reviewPanel = new JPanel(new GridLayout(5, 1));
		questionLabel = new JLabel("Question");
		answerLabel = new JLabel("");
		showAnswerButton = new JButton("Show Answer");
		
		JPanel ratingPanel = new JPanel();
		for (int i = 1; i <= 5; i++) {
			int rating = i;
			JButton rateBtn = new JButton(String.valueOf(i));
			rateBtn.addActionListener(e -> rateFlashCard(rating));
			ratingPanel.add(rateBtn);
		}
		
		reviewPanel.add(questionLabel);
		reviewPanel.add(showAnswerButton);
		reviewPanel.add(answerLabel);
		reviewPanel.add(new JLabel("Rate Your Recall (1-5):"));
		reviewPanel.add(ratingPanel);
		
		// --- Default Add Panel ---
		add(addPanel, BorderLayout.CENTER);
		
		// --- Button Actions ---
		addCardButton.addActionListener(e -> {
			clearCenter();
			add(addPanel, BorderLayout.CENTER);
			revalidate();
			repaint();
		});
		
		reviewButton.addActionListener(e -> {
			clearCenter();
			currentIndex = 0;
			prioritizeFlashCards(); // sort by weakest memory
			showFlashCard();
			add(reviewPanel, BorderLayout.CENTER);
			revalidate();
			repaint();
		});
		
		saveButton.addActionListener(e -> {
			String q = questionInput.getText().trim();
			String a = answerInput.getText().trim();
			if (!q.isEmpty() && !a.isEmpty()) {
				FlashCard card = new FlashCard(q, a);
				flashcards.add(card);
				saveFlashCardsToFile();
				JOptionPane.showMessageDialog(this, "FlashCard saved!");
				questionInput.setText("");
				answerInput.setText("");
			}
		});
		
		showAnswerButton.addActionListener(e -> {
			if (currentIndex < flashcards.size()) {
				answerLabel.setText("A: " + flashcards.get(currentIndex).getAnswer());
			}
		});
		
		setVisible(true);
	}
	
    /**
     * Displays the current flashcard's question.
     * If no flashcards exist or all have been reviewed, displays appropriate messages.
     */
	private void showFlashCard() {
		if (flashcards.isEmpty()) {
			questionLabel.setText("No flashcards available.");
			answerLabel.setText("");
			showAnswerButton.setEnabled(false);
			return;
		}
		
		if (currentIndex >= flashcards.size()) {
			questionLabel.setText("You've reviewed all flashcards!");
			answerLabel.setText("");
			showAnswerButton.setEnabled(false);
			return;
		}
		
		FlashCard current = flashcards.get(currentIndex);
		questionLabel.setText("Q: " + current.getQuestion());
		answerLabel.setText("");
		showAnswerButton.setEnabled(true);
	}
	
    /**
     * Updates the rating for the current flashcard and advances to the next one.
     * 
     * @param rating The user's recall rating (1-5, where 1 is poorest recall and 5 is strongest)
     */
	private void rateFlashCard(int rating) {
		if (currentIndex < flashcards.size()) {
			flashcards.get(currentIndex).updateRating(rating);
			currentIndex++;
			saveFlashCardsToFile();
			showFlashCard();
		}
	}
	
    /**
     * Sorts flashcards by their rating in ascending order.
     * This ensures that cards with lower ratings (weaker recall) are shown first.
     */
	private void prioritizeFlashCards() {
		flashcards.sort(Comparator.comparingInt(FlashCard::getRating)); // lowest rating first
	}
	
    /**
     * Removes the current panel from the center of the layout.
     * Used when switching between add and review modes.
     */
	private void clearCenter() {
		if (getContentPane().getComponentCount() > 1) {
			getContentPane().remove(1); // remove center component (panel)
		}
	}
	
    /**
     * Loads saved flashcards from the file system.
     * Each line in the file represents one flashcard in the format:
     * question|answer|rating
     */
	private void loadFlashCardsFromFile() {
		try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] parts = line.split("\\|");
				if (parts.length == 3) {
					flashcards.add(new FlashCard(parts[0], parts[1], Integer.parseInt(parts[2])));
				}
			}
		} catch (IOException e) {
			System.out.println("No saved flashcards found.");
		}
	}
	
    /**
     * Saves all flashcards to the file system.
     * Each flashcard is written as a line in the format:
     * question|answer|rating
     */
	private void saveFlashCardsToFile() {
		try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
			for (FlashCard card : flashcards) {
				pw.println(card.getQuestion() + "|" + card.getAnswer() + "|" + card.getRating());
			}
		} catch (IOException e) {
			System.out.println("Failed to save flashcards.");
		}
	}
	
    /**
     * Entry point for running the FlashCardManager as a standalone application.
     * 
     * @param args Command line arguments (not used)
     */
	public static void main(String[] args) {
		new FlashCardManager();
	}
}
