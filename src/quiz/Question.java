package quiz;
import java.util.ArrayList;

/**
 * The `Question` class represents a quiz question with its associated answers.
 */
public class Question {
    
    // The text of the question
    private String q = "";

    // The correct answer to the question
    private String rightAnswer = "";

    // A list containing the correct answer and three incorrect answers
    ArrayList<String> stringList = new ArrayList<>();

    /**
     * Constructs a new question with the provided parameters.
     *
     * @param question The text of the question.
     * @param answer The correct answer to the question.
     * @param wAnswer1 An incorrect answer.
     * @param wAnswer2 Another incorrect answer.
     * @param wAnswer3 Another incorrect answer.
     */
    public Question(String question, String answer, String wAnswer1, String wAnswer2, String wAnswer3) {
        q = question;
        rightAnswer = answer;

        // Add answers to the list
        stringList.add(answer);
        stringList.add(wAnswer1);
        stringList.add(wAnswer2);
        stringList.add(wAnswer3);
    }

    /**
     * Retrieves the text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestionText() {
        return q;
    }

    /**
     * Retrieves the text of the correct answer.
     *
     * @return The text of the correct answer.
     */
    public String getRightAnswerText() {
        return rightAnswer;
    }

    /**
     * Retrieves the list of answers (including the correct and incorrect answers).
     *
     * @return The list of answers.
     */
    public ArrayList<String> getList() {
        return stringList;
    }
}
