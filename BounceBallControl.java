//U10416003

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

//create a class extend Application
public class BounceBallControl extends Application {
	@Override // Override the start method in the Application class
	public void start(Stage primaryStage) {
		BallPane ballPane = new BallPane(); //create a ball pane

		//pause and resume animation
		ballPane.setOnMousePressed(e -> ballPane.pause());
		ballPane.setOnMouseReleased(e -> ballPane.play());

		//increase and decrease animation   
		ballPane.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.UP) {
				ballPane.increaseSpeed();
			} 
			else if (e.getCode() == KeyCode.DOWN) {
				ballPane.decreaseSpeed();
			}
		});

		//create a scene and place it in the stage
		Scene scene = new Scene(ballPane, 300, 300);
		primaryStage.setTitle("BounceBallControl"); //set the stage title
		primaryStage.setScene(scene); //place the scene in the stage
		primaryStage.show(); //display the stage
    
		//must request focus after the primary stage is displayed
		ballPane.requestFocus();
	}

	//create Main method
	public static void main(String[] args) {
		launch(args);
	}
}