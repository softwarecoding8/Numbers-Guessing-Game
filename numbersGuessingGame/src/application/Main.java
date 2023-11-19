package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	//constructor
	public Main() {
		
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(Main.class.getResource("/view/NumberGuessingGame.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			//title
			primaryStage.setTitle("Numbers Guessing Game");
			//set the application icon
			primaryStage.getIcons().add(new Image("file:icon/game.png")) ;
			primaryStage.show();
			primaryStage.setResizable(false);
			
	} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
