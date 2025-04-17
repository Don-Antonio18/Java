/**
 * Represents a flashcard with a question, answer, and rating.
 * The rating represents how well the user remembers the card's content,
 * with 1 being the weakest and 5 being the strongest recall.
 */
class FlashCard {
	private String question;
	private String answer;
	private int rating;
	
	/**
	 * Creates a new flashcard with the given question and answer.
	 * The default rating is set to 3 (medium recall).
	 *
	 * @param question The question or front side of the flashcard
	 * @param answer The answer or back side of the flashcard
	 */
	public FlashCard(String question, String answer) {
		this.question = question;
		this.answer = answer;
		this.rating = 3;
	}
	
	/**
	 * Creates a new flashcard with the given question, answer, and rating.
	 *
	 * @param question The question or front side of the flashcard
	 * @param answer The answer or back side of the flashcard
	 * @param rating The initial rating from 1-5
	 */
	public FlashCard(String question, String answer, int rating) {
		this.question = question;
		this.answer = answer;
		this.rating = rating;
	}
	
	/**
	 * @return The question text of this flashcard
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * @return The answer text of this flashcard
	 */
	public String getAnswer() {
		return answer;
	}
	
	/**
	 * @return The current rating (1-5) of this flashcard
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * Updates the rating of this flashcard if the new rating is valid (1-5).
	 *
	 * @param newRating The new rating to set, must be between 1 and 5
	 */
	public void updateRating(int newRating) {
		if (newRating >= 1 && newRating <= 5) {
			this.rating = newRating;
		}
	}
}

