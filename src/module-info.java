module OfficePlan {
	requires javafx.graphics;
	requires javafx.fxml;
	requires javafx.controls;
	
	exports controller to javafx.graphics,  javafx.fxml, javafx.base;
	opens controller to javafx.graphics,  javafx.fxml, javafx.base;
	
	exports model to javafx.base;
	opens model to javafx.base;
}

