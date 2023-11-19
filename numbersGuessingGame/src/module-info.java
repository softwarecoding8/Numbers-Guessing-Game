module numbersGuessingGame {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	
	opens application to javafx.graphics, javafx.fxml;
	//opens numberGuessingGame  to javafx.fxml;
	opens view to javafx.fxml;
}
