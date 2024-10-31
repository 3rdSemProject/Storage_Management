package Main_Interface;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class New_Class {
	
	@FXML
	boolean isTextset = true;

    @FXML
    private Button btn3;

    @FXML
    private Label Label2;
    
    @FXML
    private Button new1;

    @FXML
    void Click(MouseEvent event) {
    	
    	btn3.onMouseClickedProperty().set(b -> {
    		if(isTextset) {
    			Label2.setText("New");
    		}
    		else {
    			Label2.setText("Hello");
    		}
    		isTextset = !isTextset;
    	});
    	
    }

}
