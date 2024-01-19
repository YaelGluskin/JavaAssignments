package quiz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Quiz extends Application{

	public static void main(String[] args) {
        launch(args);
        
    }
	/**
	@Override
    public void start(Stage stage) throws Exception {
        // Load the FXML file
        Parent root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));

        // Create a scene and set it on the stage
        Scene scene = new Scene(root);
        stage.setTitle("Quiz");
        stage.setScene(scene);

        // Display the stage
        stage.show();
    }
	*/
	@Override
	public void start(Stage stage) throws Exception {
	    try {
	        // Load the FXML file
	        Parent root = FXMLLoader.load(getClass().getResource("Quiz.fxml"));

	        // Create a scene and set it on the stage
	        Scene scene = new Scene(root);
	        stage.setTitle("Quiz");
	        stage.setScene(scene);

	        // Display the stage
	        stage.show();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}



}
