package game;
import java.util.Random;

/**
 * The Dictionary class represents a simple dictionary containing a list of words.
 */
public class Dictionary {
    // Array of words in the dictionary
    private String[] dictionary = {"apple", "orange", "banana", "grape", "kiwi", "watermelon", "strawberry", "blueberry", "mango", "pineapple",
            "peach", "pear", "plum", "cherry", "raspberry", "lemon", "lime", "avocado", "pomegranate", "melon"};

    // Static variable representing the alphabet
    private final static String ABC = " a b c d e f g h i j k l m n o p q r s t u v w x y z ";
    private static String abc = ABC;
    
    /**
     * Gets a random word from the dictionary.
     *
     * @return A randomly selected word from the dictionary.
     */
    public String getWord() {        
        Random r = new Random();	// Create an instance of the Random class        
        int numWord = r.nextInt(20);	// Generate a random index to select a word from the dictionary array  
        Dictionary.abc = ABC;
        return dictionary[numWord];	// Return the selected word
    }

    /**
     * Gets the current state of the alphabet.
     *
     * @return The alphabet as a string.
     */
    public static String getABC() {
        return abc;
    }

    /**
     * Removes a specified letter from the alphabet.
     *
     * @param letter The letter to be removed.
     */
    public static void removeLetters(String letter) {
        
        String toHold = Dictionary.abc;	// Hold the current state of the alphabet        
        int index = Dictionary.abc.indexOf(letter);	// Find the index of the specified letter in the alphabet
        // Modify the alphabet by removing the specified letter
        String modifiedString = toHold.substring(0, index - 1) + " " + toHold.substring(index + 2);        
        Dictionary.abc = modifiedString;	// Update the alphabet
    }

    /**
     * Checks if a given letter is present in the alphabet.
     *
     * @param letter The letter to check.
     * @return True if the letter is present, false otherwise.
     */
    public static boolean letterCheck(char letter) {
        // Check if the alphabet contains the specified letter
        return Dictionary.abc.indexOf(letter) != -1;
    }
    
}
