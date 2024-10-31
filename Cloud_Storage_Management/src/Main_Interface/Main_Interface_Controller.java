package Main_Interface;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import media.Main_media_Controller;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main_Interface_Controller {

    @FXML
    private StackPane Main_interface;
    
    @FXML
    private Label UserName;
    
    @FXML
    private JFXButton ph_and_vi;

    @FXML
    private JFXButton Files;
    
    @FXML
    private Button OpenButton;
    
    @FXML
    private Button mediaView;

    @FXML
    private Button imageView;

    @FXML
    private Pane Pane_1;

    @FXML
    private Pane Pane_2;
    
    private Main_media_Controller Main_media_Controller;
    
    public void visible() {
    	Pane_2.setVisible(false);
    	Pane_2.setManaged(false);
    }
    
    @FXML
    public void initialize() {
    	Main_media_Controller = new Main_media_Controller();
    	mediaView.setOnAction(Main_media_Controller.handleVideoClick());
    	imageView.setOnAction(Main_media_Controller.handleImageClick());
    }

    @FXML
    void Change(ActionEvent event) {
    	if(!Pane_1.isVisible()) {
    		Pane_2.setVisible(false);
    		Pane_2.setManaged(false);
    		Pane_1.setVisible(true);
    		Pane_1.setManaged(true);
    	}
    }
    @FXML
    void Change2(ActionEvent event) {
    	if(!Pane_2.isVisible()) {
    		Pane_1.setVisible(false);
    		Pane_1.setManaged(false);
    		Pane_2.setVisible(true);
    		Pane_2.setManaged(true);
    	}
    }

    public void setlabel(String a) {
    	UserName.setText(a);
    }
    
    
}
