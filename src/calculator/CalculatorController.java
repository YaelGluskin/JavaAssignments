// import javax.swing.JOptionPane;
package calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.control.TextField;
/**
 * The CalculatorController class controls the behavior of a simple calculator application.
 * 
 * It manages the user interface defined in an associated FXML file, handling button clicks
 * 
 * The main features include:
 * - Numerical input: Clicking on digit buttons appends the corresponding digit to the calculator display.
 * - Decimal point: The calculator supports decimal input, preventing multiple decimal points in a single number.
 * - Sign change: Users can change the sign of the current number, allowing positive or negative input.
 * - Arithmetic operations: Addition, subtraction, multiplication, and division are supported.
 * - Result calculation: Clicking the equals button performs the final calculation and displays the result.
 * - Error handling: Division by zero is flagged as an error, and the display text turns red.
 * - Continuous calculations: Users can perform multiple operations without pressing the clear button.
 *
 * Note: The calculator allows continuous calculations without clearing the display. Division by zero
 * is flagged as an error, and the display text turns red until the user performs a valid operation.
 *
 * @author Yael Gluskin
 * @version 1.0 - I'm kidding... there were so many 😤.
 */

public class CalculatorController {

    @FXML
    private GridPane grid;

    @FXML
    private TextField calc;

    @FXML
    private TextField result;
    
//    private boolean vaildLastPressed = true;
    private boolean validPressed = true; // A flag that is turned off when there is an invalid result.  
    private boolean operatorFlag = false; // Flag for arithmetic operation button.
    private boolean resultFlag = false; // A flag for a result button.
    private boolean pointFlag = false; // Flag for decimal point button.
    private boolean validFlag = true; // A flag that turns off when a decimal point is pressed.
    private boolean signFlag = false; // A flag for a sign switch.
    private double firstFactor = 0; //  A variable that holds the first factor in the exercise.
    private double secondFactor = 0; //  A variable that holds the second factor in the exercise.
    private String lastOperator = "@"; // A variable that stores the last arithmetic operation performed, initialized to @.    
    private String stringToCalc = ""; // A string that preserves the entered exercise without sign switch.
    
    
    /**
     * Initializes the calculator by making the "calc" and "result" TextFields non-editable
     * and showing a message dialog.
     */
    @FXML   
    public void initialize() {
        /*JOptionPane.showMessageDialog(null, "Welcome to the calculator app\r\n"
        		+ "Written by Yael Gluskin");
        		*/
        calc.setEditable(false);
        result.setEditable(false);
    }
    
    /**
     * Handles button clicks for numerical digits, updating the calculator display.
     * @param event
     */
    @FXML
    public void hanleButton(ActionEvent event) {  	
    	appendBtnText(event);
    	validPressed = true; // The last pressed is not point
    }
    
    /**
     * Handles button clicks for the decimal point, preventing multiple decimal points in a number.
     * @param event
     */
    @FXML
    public void hanlePointButton(ActionEvent event) {
    	if(pointFlag == true)
    		return;
    	pointFlag = true;   	
    	appendBtnText(event);
    	validPressed = false; //
    }
    /**
     * Handles button clicks for changing the sign of the current number.
     * @param event
     */
    @FXML
    public void hanldeChangeSign(ActionEvent event) {
    	if(clickAllowed()) // The last pressed was a operator
    		return;
    	if(resultFlag == true) // if not, Make problems
    		return;
    	calc.appendText(((Button)event.getSource()).getText());
    	signFlag = true;    
    	
    	operatorFlag = false; // Mark that the last pressed was not a oprator
    	validPressed = true; //
    }
   
    /**
     * Handles button clicks for arithmetic operation buttons (+, -, *, /),
     * updating the calculator display and performing calculations.
     * @param event
     */
    @FXML
    public void hanldeOperButton(ActionEvent event) {
    	if(clickAllowed()) // Is is not possible to keep pressing
    		return;	
    	operatorFlag = true; // Mark that the last pressed was a opertor    	
    	pointFlag = false; // 
    	validPressed = true; // The last pressed is not point    	
    	if(resultFlag == true) {
    		lastOperator = ((Button)event.getSource()).getText(); 
    		firstFactor = Double.parseDouble(result.getText());
    	}
    	else {
    		if(lastOperator == "@" ) {
        		lastOperator = ((Button)event.getSource()).getText();    		
        		getNumber(calc.getText());
        	}
    		else {
    			String result = getLastSubstring(calc.getText(), lastOperator);
	    		secondFactor = Double.parseDouble(result);
	    		changeSignCheck(2); // Change the sign
	    		firstFactor = getResult(firstFactor, secondFactor, lastOperator);     		
	    		lastOperator = ((Button)event.getSource()).getText();
	    	}    		
    	}
    	calc.appendText(((Button)event.getSource()).getText());
    	// The string without the sign
		stringToCalc += ((Button)event.getSource()).getText();
		signFlag = false; //
		resultFlag = false; // Mark that the last pressed was not a result
    }
    
    /**
     * Handles button clicks for the delete button, clearing the calculator display
     * and resetting the calculator state.
     * @param event
     */
    @FXML
    public void handleDeleteButton(ActionEvent event) {
    	calc.setText("");
    	result.setText("");
    	setTextFieldStyles(calc, "black", "white");
    	setCalculator();
    }
    
    /**
     * Handles button clicks for the equals button, performing the final calculation
     * and displaying the result.
     * @param event
     */
    @FXML
    public void hanldeEquaulButton(ActionEvent event) {
    	if(clickAllowed()) // The last pressed was a operator
    		return;
    	if(resultFlag == true) { // If there were a result and now it a number
    		result.setText(result.getText());
    		endResult();
    		return;
    	}
    	String s = getLastSubstring(calc.getText(), lastOperator);
    	if(s == null) {
    		getNumber(calc.getText());
    		printResult(firstFactor);
    	} else {
	    	secondFactor = Double.parseDouble(s);
	    	changeSignCheck(2);	    	
	    	double solve = getResult(firstFactor, secondFactor, lastOperator);  
	    	printResult(solve);
    	} 
    	endResult();
    }
    
    private void printResult(double number) {
    	String toPrint = "";
    	toPrint = formatResult(number);
		result.setText(toPrint);
    }

    private static String formatResult(double result) {
    	if(result % 1 == 0)
    		return ((int)result + "");
        // Convert the double to a string to count decimal places
        String resultAsString = Double.toString(result);
        // Find the index of the decimal point
        int decimalIndex = resultAsString.indexOf('.');
        int startIndex = resultAsString.indexOf("000");

        if (startIndex != -1) {
        	return resultAsString.substring(0, startIndex);
        }
        // Calculate the number of decimal places
        int decimalPlaces = resultAsString.length() - decimalIndex - 1;

        // Set the precision based on the condition (up to 5 decimal places)
        int precision = Math.min(decimalPlaces, 5);

        // Format the result with the determined precision
        return String.format("%." + precision + "f", result);
    }
    /**
    private boolean getCaseNumber(double number) {
    	if(number % 1 == 0) {
    		intSolution = (int)number;
    		return true;
    	}
    	return false;
    }
    */
    private void appendBtnText(ActionEvent event) {    	
    	if(resultFlag == true) { // If there were a result and now it a number
    		handleDeleteButton(event);
    	}
    	if(signFlag == true)
    		return;
    	calc.appendText(((Button)event.getSource()).getText());
    	// The string without the sign
		stringToCalc += ((Button)event.getSource()).getText();
    	operatorFlag = false; // Mark that the last pressed was not a oprator
    	resultFlag = false; // Mark that the last pressed was not a result
    }
    
    /**
     * Extracts and converts the current number from the calculator display,
     * considering sign changes.
     * @param s The string representation of the current calculator display.
     */
    private void getNumber(String s) {
    	String numberString = s;
    	// take the second string
    	if(signFlag == true) {
    		numberString = stringToCalc;
        }
    	double doubleValue = Double.parseDouble(numberString);
    	firstFactor = doubleValue;
    	changeSignCheck(1);
    	
    }
    
    /**
     * Performs the arithmetic operation specified by the operator on the given numbers.
     * @param num1 The first operand.
     * @param num2 The second operand.
     * @param operator The arithmetic operator (+, -, *, /).
     * @return The result of the arithmetic operation.
     */
    private double getResult(double num1, double num2, String operator) {
    	switch (operator) {
        case "*":
            return num1 * num2;
        case "/":
        	if(num2 == 0) {        		
        		validFlag = false;
        	}
            return num1 / num2;
        case "-":
            return (num1 - num2);
        case "+":
            return num1 + num2;
        default:
            return 0;
    	}
    }
    
    /**
     * Checks if a sign change is requested and applies it to the appropriate operand.
     * @param marker An indicator to specify which operand to check and modify.
     */
    private void changeSignCheck(int marker) {
        if (signFlag == true) {
            if (marker == 1)
                firstFactor = firstFactor * -1;
            else
                secondFactor = secondFactor * -1;
            signFlag = false;
        }
    }
    
    private boolean clickAllowed() {
        	if(operatorFlag == true || calc.getText().equals("") || validPressed == false) // The last pressed was a operator
        		return true;
        	return false;
    }
    
    /**
     * Sets the text and background styles of a TextField.
     * @param textField The TextField to modify.
     * @param textColor The color of the text.
     * @param backgroundColor The color of the background.
     */
    public static void setTextFieldStyles(TextField textField, String textColor, String backgroundColor) {
        textField.setStyle("-fx-text-fill: " + textColor + "; -fx-background-color: " + backgroundColor + ";");
    }

    /**
     * Retrieves the substring in the calculator display from the last occurrence of a specified operator to the end.
     * @param s1 The string representation of the current calculator display.
     * @param s2 The operator to search for.
     * @return The substring after the last occurrence of the specified operator, or null if not found.
     */
    public String getLastSubstring(String s1, String s2) {
        String calcString = s1;
        if (signFlag == true) {
            calcString = stringToCalc;
        }
        int lastIndex = calcString.lastIndexOf(s2);

        if (lastIndex != -1) {
            return calcString.substring(lastIndex + 1);
        } else {
            return null;
        }
    }

    /**
     * Resets the calculator state to its initial values.
     */
    private void setCalculator() {
        operatorFlag = false;
        resultFlag = false;
        pointFlag = false;
        validFlag = true;
        signFlag = false;
        validPressed = true;
        firstFactor = 0;
        secondFactor = 0;
        lastOperator = "@";
        stringToCalc = "";
    }

    /**
     * Checks if the result is valid, setting the text color to red if not.
     * @return True if the result is invalid, false otherwise.
     */
    private boolean checkValid() {
        if (validFlag == false) {
            setTextFieldStyles(calc, "black", "red");
            return true;
        }
        return false;
    }

    /**
     * Handles the end of a calculation, determining whether to allow further operations.
     */
    private void endResult() {
        if (checkValid() == true)
            operatorFlag = true;
        else
            operatorFlag = false;
        resultFlag = true;
        pointFlag = false;      
        validPressed = true;
        
    }

}

