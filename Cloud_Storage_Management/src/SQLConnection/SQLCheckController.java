package SQLConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class SQLCheckController {
	String message = null;
	String take = "True";
	private Login_Page.Login_Page_Controller login;
		public SQLCheckController(Login_Page.Login_Page_Controller login) {
			this.login = login;
		}
        // Adjust these variables to match your MySQL server settings
    	public Connection connect() {
    	    String url = "jdbc:mysql://localhost:3306/Javafx_application";
    	    String user = "root";
    	    String password = "Project3rdsem";

    	    try {
    	        return DriverManager.getConnection(url, user, password);
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	        login.updateFeedbackLabel("Database error: " + e.getMessage());
    	        return null;
    	    }
    	}


    public void CheckUser(String UserEntered, String UserEmailEntered) throws IOException {
    	if (UserEntered == null || UserEmailEntered == null) { 
    		login.updateFeedbackLabel("Username or email field is empty.");
    		return; 
    		}

        String query = "SELECT COUNT(*) FROM user_registration_details WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn != null ? conn.prepareStatement(query) : null) {

            if (pstmt == null) {
            	login.updateFeedbackLabel("Failed to create a database connection.");
            	return;
            }

            pstmt.setString(1, UserEntered);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next() && rs.getInt(1) > 0) {
            	login.updateFeedbackLabel("Username already exists.");
            	return;
            } else {
            	if(addUser(UserEntered, UserEmailEntered)) {
            		login.setLabel();
            	}
            }
        } catch (SQLException e) {
            e.printStackTrace();
            login.updateFeedbackLabel("Database error: " + e.getMessage());
        }
    }
    
    public boolean addUser(String username, String email) throws SQLException { 
    	if (username == null || email == null) { 
    		login.updateFeedbackLabel("Username or email field is empty.");
    		return false; 
    		}
    	
    	String emailCheckQuery = "SELECT COUNT(*) FROM user_registration_details WHERE email = ?"; 
    	try (Connection conn = connect()) { 
    		if (conn == null) { 
    			login.updateFeedbackLabel("Failed to create a database connection.");
    			return false; 
    			} // Check if the email exists 
    		try (PreparedStatement emailPstmt = conn.prepareStatement(emailCheckQuery)) { 
    			emailPstmt.setString(1, email); 
    			ResultSet emailRs = emailPstmt.executeQuery(); 
    			if (emailRs.next() && emailRs.getInt(1) > 0) { 
    				login.updateFeedbackLabel("Email already exists.");
    				return false; 
    				} 
    			}
    	}
    	
    	String insertQuery = "INSERT INTO user_registration_details (username, email) VALUES (?, ?)"; 
    	try (Connection conn = connect(); 
    			PreparedStatement pstmt = conn.prepareStatement(insertQuery)) { 
    		pstmt.setString(1, username); 
    		pstmt.setString(2, email); 
    		int affectedRows = pstmt.executeUpdate(); 
    		if (affectedRows == 0) { 
    			login.updateFeedbackLabel("Failed to add user.");
    			return false;
    			}
    		return affectedRows > 0;
    		}
    	catch (SQLException e) { 
    		e.printStackTrace(); 
    		login.updateFeedbackLabel("Database error: " + e.getMessage());
    		}
    	return false;
    }
}
