package view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import alert.AlertMaker;

public class PlayController implements Initializable {
	@FXML
    private Text min_value;

    @FXML
    private Text max_value;
    @FXML
	private ImageView guess_no_view;

    @FXML
    private TextField guess_one;

    @FXML
    private TextField guess_two;
    
    @FXML
    private TextField guess_three;

    @FXML
    private Button submitButton;
    private int secretNum1;
    private int secretNum2;
    private int secretNum3;
    private int value1;//minimum value
    private int value2;//maximum value
    
    //Generated list
    ArrayList <Integer> list1 = new ArrayList<>();
    //Guess numbers list
    ArrayList <Integer> list2 = new ArrayList<>();

   
	public PlayController() {
	    
	}
	    
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		 //minimum and maximum initial values
		 min_value.setText(String.valueOf(value1));
		 max_value.setText(String.valueOf(value2));
		 //adding secret numbers to list 1
		 list1.add(secretNum1);
		 list1.add(secretNum2);
		 list1.add(secretNum3);
		 //load image view
		 loadImageView();
		 
	}
	
	public void setData(int firstSecretNumberIn,int secondSecretNumberIn, int thirdSecretNumberIn,int value1In,int value2In) {
	  	this.secretNum1 = firstSecretNumberIn;
	    this.secretNum2 = secondSecretNumberIn;
	    this.secretNum3 = thirdSecretNumberIn;
	    this.value1 = value1In;
	    this.value2 =value2In;
	   
	 }

	@FXML
	private void submitAction(ActionEvent event) {
		if(validateInput()) {
			int guess1 =Integer.parseInt(guess_one.getText());
			int guess2 =Integer.parseInt(guess_two.getText());
			int guess3 =Integer.parseInt(guess_three.getText());
			 //adding guess numbers in list 2
			 list2.add(guess1);
			 list2.add(guess2);
			 list2.add(guess3);
			 //System.out.println(list1);
			 //System.out.println(list2);
			 //retaining common elements between list 1 and list 2
			 list2.retainAll(list1);
			 //System.out.println(list2);
			 if(list2.size()==3) {
				 	Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Results");
			        alert.setHeaderText("Congratulations You Are The Master,You Won 300 points!!!!");
			        alert.setContentText("You Guessed "+ list2.size() +" Numbers Correctly");
			        styleAlert(alert);
			        alert.showAndWait();
			        list2.clear();
				 
			 }else if(list2.size()==2) {
				 	Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Results");
			        alert.setHeaderText("Congratulations You Won 200 points!!!!");
			        alert.setContentText("You Guessed "+ list2.size() +" Numbers Correctly");
			        styleAlert(alert);
			        alert.showAndWait();
			        list2.clear();
				 
			 }else if(list2.size()==1) {
				 	Alert alert = new Alert(AlertType.INFORMATION);
			        alert.setTitle("Results");
			        alert.setHeaderText("Congratulations You Won 100 points!!!!");
			        alert.setContentText("You Guessed "+ list2.size() +" Number Correctly");
			        styleAlert(alert);
			        alert.showAndWait();
			        list2.clear();
				 
			 }else {
				 	Alert alert = new Alert(AlertType.WARNING);
			        alert.setTitle("Results");
			        alert.setHeaderText("Lost");
			        alert.setContentText("You Lost The Game,Try Again!!");
			        styleAlert(alert);
			        alert.showAndWait();
			        list2.clear();
			 }
		}
		 
	}
	
    private static void styleAlert(Alert alert) {
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        setStageIcon(stage);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStyleClass().add("custom-alert");
    }
    
    public static void setStageIcon(Stage stage) {
    	stage.getIcons().add(new Image("file:icon/game.png"));
    }
	
	private boolean validateInput() {
	    	String errorMessage = "";
	        if(guess_one.getText().isEmpty()||guess_two.getText().isEmpty()|| guess_three.getText().isEmpty()){
	        	errorMessage += "Please fill blank fields!!\n";
	        } 
	        
	       try {
	        	if (Integer.parseInt(guess_one.getText()) < value1 || Integer.parseInt(guess_one.getText()) > value2) {
			        
			    	errorMessage +="Your guess number cannot be less than "+ value1 + " and greator than " + value2;
			    	guess_one.requestFocus();
			    }
	        	
	        	if (Integer.parseInt(guess_two.getText()) < value1 || Integer.parseInt(guess_two.getText()) > value2) {
			        
			    	errorMessage +="Your guess number cannot be less than "+ value1 + " and greator than " + value2;
			    	guess_two.requestFocus();
	        	}
	        	
	        	if (Integer.parseInt(guess_three.getText()) < value1 || Integer.parseInt(guess_three.getText()) > value2) {
			        
			    	errorMessage +="Your guess number cannot be less than "+ value1 + " and greator than " + value2;
			    	guess_three.requestFocus();
	        	}
	        	
	        }catch (NumberFormatException ex) {
	        		errorMessage +="Enter valid integer value" ;
	        }	

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            Alert alert = new Alert(Alert.AlertType.ERROR);
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Please correct invalid fields");
	            alert.setContentText(errorMessage);
	            styleAlert(alert);
	            alert.showAndWait();
	
	            return false;
	        }
		}
	
	private void loadImageView() {
		File guessPic = new File("src/resources/guess_no.png");
		
		Image image1 = new Image(guessPic.toURI().toString());
		guess_no_view.setImage(image1);
	}
	
	@FXML
	private void clearAction(ActionEvent event) {
		reset();
	}
	
	private void reset() {
		guess_one.clear();
		guess_two.clear();
		guess_three.clear();
	   
	}
}
