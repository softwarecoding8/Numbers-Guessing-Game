package view;

import java.io.File;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import alert.AlertMaker;


public class NumberGuessingGameController implements Initializable {
	@FXML
    private TextField min;

    @FXML
    private TextField max;
    @FXML
	private ImageView guess_game_view;
    
    @FXML
    private Text secret_numbers_alert_text;

    @FXML
    private Button generateValuesButton;
    @FXML
    private Button startGameButton;
    private int firstSecretNumber = -1;
    private int secondSecretNumber = -2;
    private int thirdSecretNumber = -3;
    private int minValue =-4;
    private int maxValue =-5;
    		
   
public NumberGuessingGameController() {
   
}
    
@Override
public void initialize(URL url, ResourceBundle rb) {
	File gamePic = new File("src/resources/guess_game.jpg");
	
	Image image1 = new Image(gamePic.toURI().toString());
	guess_game_view.setImage(image1);
}

@FXML
private void generateValuesAction() {
	if (this.isValidated()) {
		String m = min.getText();
		minValue =Integer.parseInt(m);//Minimum value of range
		String mx = max.getText();
		maxValue =Integer.parseInt(mx);//Maximum value of range
		
		//Generate random integers in range minValue to maxValue
		Random rand = new Random();
		firstSecretNumber = rand.nextInt(maxValue - minValue + 1) + minValue;
		secondSecretNumber = rand.nextInt(maxValue - minValue + 1) + minValue;
		thirdSecretNumber = rand.nextInt(maxValue - minValue + 1) + minValue;
		
		//alert text,secret numbers generated
		secret_numbers_alert_text.setText("Random Secret Numbers Generated Between "+ minValue +" And " +maxValue+ ",Click Start Game Button To Play.");
		min.clear();
		max.clear();
	}else {
		//secret numbers not yet generated,set text to empty
		secret_numbers_alert_text.setText("");
	}
}
private boolean isValidated() {
	
    if (min.getText().equals("")) {
       
    	AlertMaker.showErrorMessage("Error","Minimum value text field cannot be blank.");
    	min.requestFocus();
    	return false;
        
    }else if(max.getText().equals("")) {
        
     	AlertMaker.showErrorMessage("Error","Maximum value text field cannot be blank.");
     	max.requestFocus();
     	return false;
         
     }else if(Integer.parseInt(min.getText())<0) {
    	 AlertMaker.showErrorMessage("Error","Enter valid minimum value greater than or equal to zero.");
      	 min.requestFocus();
      	 return false;
     }else if(Integer.parseInt(max.getText())<0) {
    	 AlertMaker.showErrorMessage("Error","Enter valid maximum value greater than zero.");
      	 max.requestFocus();
      	 return false;
     }else if(Integer.parseInt(max.getText())<=Integer.parseInt(min.getText())) {
    	 AlertMaker.showErrorMessage("Error","Enter a value greater than minimum value.");
      	 max.requestFocus();
      	 return false;
     }else {
    	 //secret_numbers_alert_text.setText("");
    	 //return false;
     }
   return true;
     
}
@FXML	
public void startGameAction(ActionEvent event) throws Exception{
 if(!(secret_numbers_alert_text.getText().isEmpty())) {
	FXMLLoader loader = new FXMLLoader((getClass().getResource("/view/Play.fxml")));
	PlayController playController = new PlayController();
    loader.setController(playController);
    playController.setData(firstSecretNumber,secondSecretNumber,thirdSecretNumber,minValue,maxValue);
    
    Parent root = loader.load();
    Stage stage = new Stage();
  
    Scene scene = new Scene(root);
    stage.initModality(Modality.APPLICATION_MODAL);
    stage.setTitle("Numbers Guessing Game");
    stage.initStyle(StageStyle.DECORATED);
    //stage.getIcons().add(new Image("/images/logo.png"));
    stage.getIcons().add(new Image("file:icon/game.png")) ;
    stage.setScene(scene);
    stage.setResizable(false);
    stage.showAndWait();
    
 }else {
	 AlertMaker.showWarningAlert("Warning","Please generate secret numbers first.");
 }
 }	
	
}
