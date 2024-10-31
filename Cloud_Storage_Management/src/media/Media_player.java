package media;

import java.net.URL;

import com.jfoenix.controls.JFXSlider;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Media_player {

    @FXML
    private MediaView mediaView;
    
    @FXML
    private Pane ParentPane;

    @FXML
    private Pane Controlpane;
    
    @FXML
    private Button play;

    @FXML
    private Button pause;
    
    @FXML
    private Button Backward;

    @FXML
    private Button Forward;

    @FXML
    private JFXSlider timeSlider;
    
    @FXML
    private HBox Hbox;

    @FXML
    private Label starttime;

    @FXML
    public void initialize() {
    	
    	Controlpane.layoutXProperty().bind(ParentPane.widthProperty().subtract(Controlpane.widthProperty()).divide(2));
    	Controlpane.layoutYProperty().bind(ParentPane.heightProperty().subtract(Controlpane.heightProperty()).divide(2));
    	
    	Hbox.prefWidthProperty().bind(ParentPane.widthProperty());
    	timeSlider.prefWidthProperty().bind(Hbox.widthProperty().subtract(starttime.widthProperty()).subtract(20));
    	
    	URL mediaUrl = getClass().getResource("Media/d04ed1d1.mp4");
    	Media media = new Media(mediaUrl.toExternalForm());
    	MediaPlayer mediaPlayer = new MediaPlayer(media);
    	mediaView.setMediaPlayer(mediaPlayer);
    	
    	starttime.setMinWidth(50);
    	
    	mediaPlayer.setOnReady(() -> { 
    		Duration totalDuration = mediaPlayer.getTotalDuration(); 
    		timeSlider.setMax(totalDuration.toSeconds()); 
    		});
    	
    	mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> { 
    		timeSlider.setValue(newTime.toSeconds()); 
    		starttime.setText(formatTime(newTime));
    	});
    	
    	
    	
    	mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> { 
    		if (!timeSlider.isValueChanging()) { timeSlider.setValue(newTime.toSeconds()); 
    		starttime.setText(formatTime(newTime)); }
    	});
    	
    	timeSlider.valueProperty().addListener((obs, oldVal, newVal) -> { 
    		if (timeSlider.isValueChanging()) { 
    			// Only seek when user is dragging the slider 
    			mediaPlayer.seek(Duration.seconds(newVal.doubleValue())); 
    			} 
    		});
    
    		play.setOnAction(e -> {
    			if(mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
    				mediaPlayer.pause();
    			}
    			else {
    				mediaPlayer.play();
    			}
    		});
    		
    	Forward.setOnAction(e -> { 
    		Duration currentTime = mediaPlayer.getCurrentTime(); 
    		mediaPlayer.seek(currentTime.add(Duration.seconds(20)));
    	});
    	
    	Backward.setOnAction(e -> { 
    		Duration currentTime = mediaPlayer.getCurrentTime(); 
    		mediaPlayer.seek(currentTime.subtract(Duration.seconds(20))); 
    	});
    	
    	mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
    		timeSlider.setValue(newTime.toSeconds());
    	});
    	
    	timeSlider.setOnMouseReleased(e -> {
    		mediaPlayer.seek(Duration.seconds(timeSlider.getValue()));
    	});
    	mediaPlayer.setOnReady(() -> {
    		Duration totalDuration = mediaPlayer.getTotalDuration();
    		timeSlider.setMax(totalDuration.toSeconds());
    	});
    	
    	Platform.runLater(() -> {
    		mediaView.fitWidthProperty().bind(mediaView.getScene().widthProperty()); 
        	mediaView.fitHeightProperty().bind(mediaView.getScene().heightProperty());
    	});
    	
    	
    	Platform.runLater(() -> {
    		Stage stage = (Stage) mediaView.getScene().getWindow();
    		stage.setOnCloseRequest((WindowEvent) -> {
    			if(mediaPlayer != null) {
    				mediaPlayer.stop();
    			}
    		});
    	});
    	
    }
    private String formatTime(Duration duration) { 
    	int minutes = (int) duration.toMinutes(); 
    	int seconds = (int) duration.toSeconds() % 60; 
    	return String.format("%02d:%02d", minutes, seconds); 
    	}
}
