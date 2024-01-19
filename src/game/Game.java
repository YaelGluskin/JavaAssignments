package game;

import java.util.Scanner;

/**
 * Assignment 11 question 1 in the course - advanced programming in the Java language 20554
 *
 * By Yael Gluskin
 * Id: 213303035
 *
 * The Game class represents the main class for the guessing game.
 */

public class Game {

    /**
     * The main method that executes the guessing game.
     *
     * @param args Command-line arguments (not used in this program).
     */
    public static void main(String[] args) {
    	
        // Prompt the user to start the game
        System.out.println("Welcome to the guessing game!");
        System.out.println("To start a game, press any key.\nTo exit, press (N/n)");
        Scanner scan = new Scanner(System.in); 
        String press = scan.nextLine();

        while(!press.equals("n") && !press.equals("N")) {	// Check if the user wants to exit the game    
        	press = playGame();
        }
        System.out.println("You chose not to play the game. Goodbye!");
    }
    
    
    /**
     * Plays the guessing game.
     *
     * @return User input to start a new game or exit.
     */
    private static String playGame() {
        initializeGame(); // Call the function to initialize the game.
        boolean play = true; // Boolean flag to control the game loop.
        int numRounds = 0; // Counter to keep track of the number of rounds played.

        while (play) {
            String press = getUserGuess(); // Get user input for guessing a letter.

            if (press == null) {
                continue; // Skip to the next iteration if the input is invalid.
            }

            numRounds++; // Increment the number of rounds.
            processGuess(press, new ChosenWord(), numRounds); // Process the user's guess.

            if (ChosenWord.getHiddenGameWord().indexOf('_') == -1) {
                play = false; // Exit the loop if the word is fully revealed.
            }
        }

        return endGame(); // Call the function to handle end-of-game logic and return user input.
    }

    
    /**
     * Initializes the game by hiding the chosen word.
     */
    private static void initializeGame() {
        String word;
        ChosenWord chosenWord = new ChosenWord(); // Create an instance of the ChosenWord class.
        word = chosenWord.hideWord(); // Call the hideWord() method using the instance.
        System.out.println("The word is:\t" + word); // Display the hidden word.
        return;
    }

    /**
     * Gets user input for guessing the word.
     *
     * @return The user's guess or null if the input is invalid.
     */
    private static String getUserGuess() {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nPress any letter to guess the word!");
        System.out.println("The letters you haven't chosen to guess yet:\t" + Dictionary.getABC());

        String press = scan.nextLine();
        press = validityChecks(press); // Perform validity checks on the input.

        if (press.equals("-")) {
            return null; // Return null if the input is invalid.
        }

        return press; // Return the valid user input.
    }


    /**
     * Processes the user's guess and updates the game state.
     *
     * @param press      The user's guess.
     * @param chosenWord The ChosenWord instance.
     * @param numRounds  The current round number.
     */
    private static void processGuess(String press, ChosenWord chosenWord, int numRounds) {
        char letter = press.charAt(0);
        Dictionary.removeLetters(press); // Remove guessed letters from the available options.
        System.out.print("The " + numRounds + " guess is '" + press + "':");
        String currHiddenWord = chosenWord.revealWord(letter); // Reveal the guessed letter in the hidden word.

        if (currHiddenWord == null) {
            System.out.println("\nThere is no '" + letter + "' in the word, \nTry again!\t" + ChosenWord.getHiddenGameWord());
        } else {
            System.out.println("\t" + currHiddenWord);
        }

        if (currHiddenWord != null && (currHiddenWord.indexOf('_') == -1)) {
            System.out.println("\nYou revealed the word '" + ChosenWord.getGameWord() + "' in " + numRounds + " rounds!");
        }
    }


    /**
     * Handles end-of-game logic and returns user input for starting a new game or exiting.
     *
     * @return User input to start a new game or exit.
     */
    private static String endGame() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n\nTo start a new game, press any key.\tTo exit, press (N/n)");
        String press = scan.nextLine();
        return press; // Return the user's input for starting a new game or exiting.
    }


    

    
    
    /**
     * Performs validity checks on the user input for guessing a letter.
     *
     * @param let The user input for guessing a letter.
     * @return The valid letter if it passes checks, otherwise, returns "-".
     */
    private static String validityChecks(String let) {
    	char symbol;	// Variable to store the user's input for further processing.
    	String toReturn;	// Variable to store the processed user input or result.
    	if(let.length() == 0) {
    		return warningPrints("4");
    	}
        if (let.length() != 1) {	// Check if the input length is not equal to 1
            return warningPrints("1");
        }
        symbol = let.charAt(0);	// Extracting the first character from the user's input for further processing.
        toReturn = let.toLowerCase();	// Convert the input to lower case
        if (Character.isLetter(symbol)) {	// Check if the input is a letter
            // Check if the letter has already been guessed
            if (Dictionary.letterCheck(Character.toLowerCase(symbol))) {
                return toReturn;
            } else {
                return warningPrints("2");
            }
        } else {
            return warningPrints("3");
        }
    }

    /**
     * Prints warning messages based on the type of input error.
     *
     * @param charWarning The type of input error.
     * @return The string "-" to indicate an error occurred.
     */
    private static String warningPrints(String charWarning) {
        // Print a warning message based on the input error type
        if (charWarning.equals("1")) {
            System.out.println("You can only guess one letter at a time!");
        }
        if (charWarning.equals("2")) {
            System.out.println("You already guessed this letter!");
        }
        if (charWarning.equals("3")) {
            System.out.println("It is not a letter!");
        }
        if (charWarning.equals("4")) {
            System.out.println("You didnt enter any letter!");
        }
        return "-";
    }
}

