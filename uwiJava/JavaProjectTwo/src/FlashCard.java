/**
 * Represents a flash card with a question, answer, and rating.
 * Flash cards are used for study and memorization purposes.
 */
class FlashCard {
	private String question;
	private String answer;
	private int rating;
	
	/**
	 * Creates a new flash card with a question and answer, default rating of 3.
	 * @param question The question text for the flash card
	 * @param answer The answer text for the flash card
	 */
	public FlashCard(String question, String answer) {
		this.question = question;
		this.answer = answer;
		this.rating = 3;
	}
	
	/**
	 * Creates a new flash card with a question, answer and specified rating.
	 * @param question The question text for the flash card
	 * @param answer The answer text for the flash card
	 * @param rating The initial rating (1-5) of the flash card
	 */
	public FlashCard(String question, String answer, int rating) {
		this.question = question;
		this.answer = answer;
		this.rating = rating;
	}
	
	/**
	 * @return The question text of this flash card
	 */
	public String getQuestion() {
		return question;
	}
	
	/**
	 * @return The answer text of this flash card
	 */
	public String getAnswer() {
		return answer;
	}
	
	/**
	 * @return The current rating (1-5) of this flash card
	 */
	public int getRating() {
		return rating;
	}
	
	/**
	 * Updates the rating of this flash card if the new rating is valid.
	 * @param newRating The new rating value (must be between 1 and 5)
	 */
	public void updateRating(int newRating) {
		if (newRating >= 1 && newRating <= 5) {
			this.rating = newRating;
		}
	}
}
