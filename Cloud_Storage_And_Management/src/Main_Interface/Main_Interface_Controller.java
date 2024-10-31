package Main_Interface;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main_Interface_Controller {

	@FXML
    private Pane View;
	
	@FXML
    private Button btn;
    @FXML
    private Pane Details;
    
    @FXML
    private Button btn2;

    @FXML
    private Label label;
    @FXML
    boolean isTextset = false;
    boolean panesize = false;

    @FXML
    void On_Click(MouseEvent event) {
    	btn2.onMouseClickedProperty().set(e -> {
    		if(panesize) {
    			Details.setPrefHeight(0);
    			Details.setPrefWidth(0);
    			View.setPrefWidth(430);
    		}
    		else {
    			View.setPrefWidth(230);
    			Details.setPrefHeight(200);
    			Details.setPrefWidth(200);
    		}
    		panesize = !panesize;
    	});
    	btn.onMouseClickedProperty().set(b -> {
    		try {
				Parent root = FXMLLoader.load(getClass().getResource("Check.fxml"));
				Stage currentStage = (Stage) btn.getScene().getWindow();
				Stage primaryStage = new Stage();
				Scene scene = new Scene(root, 300, 300);
				primaryStage.setScene(scene);
				primaryStage.show();
				
				currentStage.hide();
				
				primaryStage.onCloseRequestProperty().set(a -> {
					currentStage.show();
				});
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    	});
    }
    

}
