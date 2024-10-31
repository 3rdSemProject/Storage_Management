package Login_Page;


import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextField;

import Main_Interface.Main_Interface_Controller;
import SQLConnection.SQLCheckController;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Login_Page_Controller {
	
	public void LoadFXML() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main_Interface/Main_Interface.fxml"));
		Parent root = loader.load();
		Main_Interface.Main_Interface_Controller control = loader.getController();
		control.visible();
		Stage currentstage = (Stage) Register.getScene().getWindow();
		Stage stage = new Stage();
		stage.setTitle("Project");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setWidth(800);
    	stage.setHeight(600);
		currentstage.hide();
	}
	public void setLabel() throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main_Interface/Main_Interface.fxml"));
		Parent root = loader.load();
		Stage currentstage = (Stage) Register.getScene().getWindow();
		Stage stage = new Stage();
		stage.setTitle("Project");
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		stage.setWidth(800);
    	stage.setHeight(600);
		currentstage.hide();
		Main_Interface.Main_Interface_Controller control = loader.getController();
		control.setlabel(gettext());
	}


    @FXML
    private Pane Image;
    @FXML
    private JFXTextField UserName;
    @FXML
    private JFXTextField Email;
    @FXML
    private JFXTextField Code;
    
    @FXML
    private JFXButton Register;

    @FXML
    private JFXButton Login;
    
    @FXML
    private Label Username_Warning;

    public String gettext() {
    	return UserName.getText();
    }
    
    @FXML
    private JFXCheckBox Terms_and_Condition;
    
    private SQLConnection.SQLCheckController sql;
    
    @FXML
    private void initialize() {
    	sql = new SQLCheckController(this);
    }
    
    boolean register = false;
    boolean login = false;

    private boolean isEmailValid(String email) {
        // Regular expression for validating an Email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        if (email == null) {
            return false;
        }
        return email.matches(emailRegex);
    }

    @FXML
    void Login(ActionEvent event) throws IOException {
        String a = Code.getText();
        if (a == null || a.trim().isEmpty()) {
            Username_Warning.setText("Enter a valid code.");
        } else {
            try {
                int code = Integer.parseInt(a);
                System.out.println("Entered code: " + code);
                LoadFXML();
                System.out.print(gettext());
            } catch (NumberFormatException e) {
                Username_Warning.setText("Enter a valid integer code.");
            }
        }
    }

    @FXML
    void Register(ActionEvent event) throws IOException {
        String username = UserName.getText();
        String email = Email.getText();
        if(Terms_and_Condition.isSelected()) {
        	if (username == null || username.trim().isEmpty() || email == null || email.trim().isEmpty()) {
        		Username_Warning.setText("Entered Username or Email is invalid.");
        	}
        	else {
        		char u = username.charAt(0);
        		char e = email.charAt(0);
        		if (Character.isAlphabetic(u) && Character.isAlphabetic(e)) {
        			if (isEmailValid(email)) {
        					System.out.printf("Username: %s, Email: %s%n", username, email);
        					sql.CheckUser(username, email);
        				
        			} 
        			else {
        				Username_Warning.setText("Email format is invalid.");
        			}
        		} 
        		else {
        			Username_Warning.setText("Username and Email must start with an alphabet.");
        		}
        	}
        }
        else {
        	Username_Warning.setText("Please select the check box.");
        }
    }
    public void updateFeedbackLabel(String message) { 
    	Username_Warning.setText(message);
    }
}
