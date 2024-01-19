package quiz;

import java.util.Collections;
import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Controller class for the game's graphical user interface.
 * Handles user interactions and manages the game flow.
 */

public class QuizController {

    @FXML
    private Text question; // Represents the Text element for displaying the current question.

    @FXML
    private Text text1; // Represents the Text element for displaying the first answer choice.

    @FXML
    private Text text2; // Represents the Text element for displaying the second answer choice.

    @FXML
    private Text text3; // Represents the Text element for displaying the third answer choice.

    @FXML
    private Text text4; // Represents the Text element for displaying the fourth answer choice.

    private boolean alreadyPressed = false; // Indicates whether the user has already selected an answer.
    private int goodAnswers = 0; // Keeps track of the number of correctly answered questions.
    QuestionsRepository questionsToDisplay = new QuestionsRepository(); // Provides a set of questions for the game.
    private int counter = 0; // Keeps track of the current question index.

    /**
     * Initializes the controller when the associated FXML file is loaded.
     * Displays a welcome message and sets up the initial question for the game.
     */
    @FXML
    public void initialize() {
    	String message = "<html><body style='text-align:center;'><p style='font-size:24px; color:purple;'>Welcome to the geography quiz!<br>"
                + "There are 20 questions.<br>"
                + "Good Luck!</p></body></html>";
        JOptionPane.showMessageDialog(null, message);
        setText();
    }

    /**
     * Handles the change of color for selected answer choices.
     * Checks if the selected answer is correct and updates the game state accordingly.
     *
     * @param event The mouse event triggered by the user's interaction.
     */
    @FXML
    public void changeColor(javafx.scene.input.MouseEvent event) {
        Text clickedText = (Text) event.getSource();

        if (!alreadyPressed) {
            if (clickedText.getText().equals(questionsToDisplay.getQuestion(counter).getRightAnswerText())) {
                clickedText.setFill(Color.GREEN);
                alreadyPressed = true;
                goodAnswers += 1;
                counter++;
            } else {
                if (!clickedText.getText().equals(questionsToDisplay.getQuestion(counter).getQuestionText())) {
                    clickedText.setFill(Color.RED);
                    alreadyPressed = true;
                    counter++;
                }
            }
        }
    }

    /**
     * Handles the continuation of the game.
     * Displays the next question or shows the final grade if all questions are answered.
     */
    @FXML
    public void continueClicked() {
        if (!alreadyPressed) {
            return;
        }
        if (counter == 20) {
            JOptionPane.showMessageDialog(null, "Your grade is: " + getGoodAnswers() * 5 + "%");
            askUser();
        } else {
            setText();
        }
    }

    /**
     * Retrieves the count of correctly answered questions.
     *
     * @return The number of correctly answered questions.
     */
    private int getGoodAnswers() {
        return goodAnswers;
    }

    /**
     * Shuffles the list of answer choices for the current question.
     */
    private void shuffleList() {
        Collections.shuffle(questionsToDisplay.getQuestion(counter).getList());
    }

    /**
     * Sets the text for the question and answer choices on the user interface.
     */
    private void setText() {
        alreadyPressed = false;
        question.setText(questionsToDisplay.getQuestion(counter).getQuestionText());
        shuffleList();
        text1.setText(questionsToDisplay.getQuestion(counter).getList().get(0));
        text1.setFill(Color.BLACK);
        text2.setText(questionsToDisplay.getQuestion(counter).getList().get(1));
        text2.setFill(Color.BLACK);
        text3.setText(questionsToDisplay.getQuestion(counter).getList().get(2));
        text3.setFill(Color.BLACK);
        text4.setText(questionsToDisplay.getQuestion(counter).getList().get(3));
        text4.setFill(Color.BLACK);
    }

    /**
     * Starts a new game by resetting the counter and goodAnswers, then displaying the first question.
     */
    private void startGameAgain() {
        counter = 0;
        goodAnswers = 0;
        setText(); // Set the questions from the beginning.
    }

    /**
     * Asks the user if they want to play another game or quit.
     * Exits the program if the user chooses to quit.
     */
    private void askUser() {
        // Display a dialog with "Yes" and "No" options
        int userChoice = JOptionPane.showConfirmDialog(null, "Do you want to play another game?", "Quit Game",
                JOptionPane.YES_NO_OPTION);

        // Check the user's choice
        if (userChoice == JOptionPane.YES_OPTION) {
            // If the user chooses to play another game
            startGameAgain(); // Start a new game
        } else {
            // If the user chooses to quit the game
            JOptionPane.showMessageDialog(null, "Goodbye!");
            System.exit(0); // Exit the program
        }
    }

}
