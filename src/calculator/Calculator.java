package calculator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Assignment 13 question 1 in the course - advanced programming in the Java language 20554
 * 
 * @author YaelGluskin
 * Id: 213303035
 * 
 * Main class for the game application.
 * Extends the JavaFX Application class to launch the graphical user interface.
 */
public class Calculator extends Application {

    /**
     * Entry point for launching the JavaFX application.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
        
    }

    /**
     * Start method called by the JavaFX application thread to initialize and display the GUI.
     *
     * @param stage The primary stage for the application.
     * @throws Exception If an error occurs during initialization or loading of the FXML file.
     */
    @Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));

        // Create a scene and set it on the stage
        Scene scene = new Scene(root);
        stage.setTitle("Calculator");
        stage.setScene(scene);

        // Display the stage
        stage.show();
    }
}

