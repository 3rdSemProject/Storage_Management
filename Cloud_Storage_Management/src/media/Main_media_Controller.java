package media;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_media_Controller {

    public EventHandler<ActionEvent> handleImageClick() {
    	return event -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/media/ImageViewer.fxml"));
    		Stage stage = new Stage();
    		try {
    			stage.setScene(new Scene(loader.load()));
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		stage.show();
    	};
    }

    @FXML
    public EventHandler<ActionEvent> handleVideoClick() {
    	return event -> {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/media/MediaPlayer.fxml"));
    		Stage stage = new Stage();
    		try {
				stage.setScene(new Scene(loader.load()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		stage.show();
    	};
    }

}
