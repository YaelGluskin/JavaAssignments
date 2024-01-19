package game;
/**
 * The ChosenWord class represents a selected word for the game and manages its visibility.
 */
public class ChosenWord {
	// The original word chosen for the game
	private static String theGameWord;
	// The hidden representation of the chosen word for the game
	private static String theHiddenGameWord;
	
	/**
     * Generates a hidden version of a randomly chosen word from the Dictionary.
     *
     * @return The hidden representation of the chosen word for the game.
     */
	public String hideWord() {
		Dictionary dictionary = new Dictionary(); // Create an instance of the Dictionary class
        String word = dictionary.getWord(); // Call the getWord() method using the instance
        int wordLen = word.length();	// Get the length of the word
        String repeatedChar = "_ ";		// Define the repeated character for the hidden representation
        ChosenWord.theGameWord = word;	// Store the original chosen word 
     // Generate the hidden representation of the word
        String hiddenWord = String.format("%0" + wordLen + "d", 0).replace("0", repeatedChar);
        ChosenWord.theHiddenGameWord = hiddenWord;
		return ChosenWord.theHiddenGameWord;
	}

	/**
     * Reveals occurrences of a specified letter in the hidden word.
     *
     * @param letter The letter to reveal in the hidden word.
     * @return The updated hidden representation of the word with revealed letters.
     */
	public String revealWord(char letter) {
		int index = 0;	// Variable to keep track of the index during iterations.
		int wordIndex = 0;	// Variable to keep track of the index of the word during iterations.
		char[] theHiddenWordArr = ChosenWord.theHiddenGameWord.toCharArray();   // The hidden word represented as an array.
		boolean isFound = false;	// Boolean flag to indicate whether the letter is found in the word.
		
		while (true) {
            // Find the next occurrence of the letter in the original word
            index = ChosenWord.theGameWord.indexOf(letter, wordIndex);

            // If the letter is not found and has not been found before, return null
            if (index == -1 && !isFound) {
                return null;
            }
            if (index == -1) {	// If the letter is not found, exit the loop
                break;
            }

            isFound = true;
            wordIndex = index + 1;
            theHiddenWordArr[wordIndex * 2 - 2] = letter;	// Update the hidden word array with the revealed letter
        }
		// Convert the modified array back to a string
		String modifiedStr = new String(theHiddenWordArr);
		ChosenWord.theHiddenGameWord = modifiedStr;
		return (modifiedStr);
	}
	
	/**
     * Gets the current hidden representation of the chosen word for the game.
     *
     * @return The hidden representation of the chosen word for the game.
     */
	public static String getHiddenGameWord() {
		String getHiddenGameWord = theHiddenGameWord;
		return getHiddenGameWord;
	}
	
	/**
     * Gets the original chosen word for the game.
     *
     * @return The original chosen word for the game.
     */
	public static String getGameWord() {
		String getGameWord = theGameWord;
		return getGameWord;
	}
	

}
