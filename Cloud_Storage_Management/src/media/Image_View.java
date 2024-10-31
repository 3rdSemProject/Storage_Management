package media;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Image_View {

    @FXML
    private ImageView imageView;

    @FXML
    public void initialize() {
    	String imageUrl = "media/Media/Screenshot (6).png";
    	Image image = new Image(imageUrl);
    	imageView.setImage(image);
    	
    	Platform.runLater(() -> {
    		imageView.fitWidthProperty().bind(imageView.getScene().widthProperty()); 
    		imageView.fitHeightProperty().bind(imageView.getScene().heightProperty());
    	});
    }
}
