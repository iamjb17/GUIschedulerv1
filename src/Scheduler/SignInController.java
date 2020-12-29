package Scheduler;

/**
 * @author Jessie Burton
 */

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controller for the sign in scene
 */
public class SignInController implements Initializable {

    /**
     * FXML ResourceBundle that was given to the FXMLLoader
     */
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    /**
     * FXML URL location of the FXML file that was given to the FXMLLoader
     */
    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    /**
     * FXML value for the user name label
     */
    @FXML // fx:id="lbUserName"
    private Label lbUserName; // Value injected by FXMLLoader

    /**
     * FXML value for the password label
     */
    @FXML // fx:id="lbPassword"
    private Label lbPassword; // Value injected by FXMLLoader

    /**
     * FXML value for the location label
     */
    @FXML // fx:id="lbLocation"
    private Label lbLocation; // Value injected by FXMLLoader

    /**
     * FXML value for the user name text field
     */
    @FXML // fx:id="tfUserName"
    private TextField tfUserName; // Value injected by FXMLLoader

    /**
     * FXML value for the password text field
     */
    @FXML // fx:id="tfPassword"
    private TextField tfPassword; // Value injected by FXMLLoader

    /**
     * FXML value for the error message label
     */
    @FXML // fx:id="lbErrorMessage"
    private Label lbErrorMessage; // Value injected by FXMLLoader

    /**
     * FXML value for the sign in button
     */
    @FXML // fx:id="btnSignIn"
    private Button btnSignIn; // Value injected by FXMLLoader

    /**
     * Container to store form data
     */
    private final HashMap<String, String> formData1 = new HashMap<>();

    /**
     * Action even handler for the Sign In button
     * @param event the click event of the sign in button
     * @throws IOException throws IO exception caused by calling a method that tries to write to a file
     */
    @FXML
    void onClick(ActionEvent event) throws IOException{

        // Add form data to container for easier use and portability
        formData1.put("user name", tfUserName.getText());
        formData1.put("password", tfPassword.getText());

        // Get status message on form data
        String message1 = AppHelper.checkFormCorrectness(formData1);

        // If the status is correct, try to sign user in, if not show error status message
        if (message1 == "correct") {

            // Check if a user with given username and password is in the database
            if (DBHelper.signInUser(formData1)) {
                Parent parent = FXMLLoader.load(getClass().getResource("../Resources/CustomerScene.fxml"));
                Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
                primaryStage.setScene(new Scene(parent));
                System.out.println("user signed in");

                createLog(true);
                formData1.clear();
            } else {
                createLog(false);
                lbErrorMessage.setText(AppHelper.localeRB.getString("userAndPasswordError"));
                lbErrorMessage.setVisible(true);
                formData1.clear();
            }
        } else {
            createLog(false);
            lbErrorMessage.setText(message1);
            lbErrorMessage.setVisible(true);
            formData1.clear();
        }
    }

    /**
     * Create log String
     * @param successful boolean value to note if the user was successful in logging in
     * @throws IOException throws IO exception caused by trying to write to a file
     */
    private void createLog(boolean successful) throws IOException {
        StringBuilder loginActivityStr = new StringBuilder();

        loginActivityStr.append("User Name: " +tfUserName.getText() + " ");
        Instant now = Instant.now();
        loginActivityStr.append(now.toString() + " ");
        loginActivityStr.append("Login Attempt Successful: " + successful + "," + System.lineSeparator());

        FileWriter fileWriter = new FileWriter("login_activity", true);
        fileWriter.append(loginActivityStr.toString());
        fileWriter.close();
    }

    /**
     * Override Initialize method to init state of the scene
     * @param url the fxml of this scene
     * @param resourceBundle used to get the fxml file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Set the proper language text
        lbUserName.setText(AppHelper.localeRB.getString("userName"));
        lbPassword.setText(AppHelper.localeRB.getString("signInPassword"));
        tfUserName.setPromptText(AppHelper.localeRB.getString("tfUserName"));
        tfPassword.setPromptText(AppHelper.localeRB.getString("tfPassword"));
        btnSignIn.setText(AppHelper.localeRB.getString("btnSignIn"));

        // Get and set the current users location
        lbLocation.setText(AppHelper.getCurrentLocal());

        // Lambda Expressions for turning off error message once user begins to retry inputting data
        tfUserName.setOnMouseClicked(event -> {
            lbErrorMessage.setVisible(false);
        });

        // Lambda Expressions for turning off error message once user begins to retry inputting data
        tfPassword.setOnMouseClicked(event -> {
            lbErrorMessage.setVisible(false);
        });

    }

    /**
     * FXML This method is called by the FXMLLoader when initialization is complete
     */
    @FXML
    void initialize() {
        assert lbLocation != null : "fx:id=\"lbLocation\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert lbUserName != null : "fx:id=\"lbUserName\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert tfUserName != null : "fx:id=\"tfUserName\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert lbPassword != null : "fx:id=\"lbPassword\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert tfPassword != null : "fx:id=\"tfPassword\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert lbErrorMessage != null : "fx:id=\"lbErrorMessage\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert btnSignIn != null : "fx:id=\"btnSignIn\" was not injected: check your FXML file 'SignInForm.fxml'.";

    }

}
