package Scheduler;

/**
 * @author Jessie Burton
 */

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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

// Controller for the sign in form
public class SignInController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lbLocation"
    private Label lbLocation; // Value injected by FXMLLoader

    @FXML // fx:id="tfUserName"
    private TextField tfUserName; // Value injected by FXMLLoader

    @FXML // fx:id="tfPassword"
    private TextField tfPassword; // Value injected by FXMLLoader

    @FXML // fx:id="lbErrorMessage"
    private Label lbErrorMessage; // Value injected by FXMLLoader

    @FXML // fx:id="btnSignIn"
    private Button btnSignIn; // Value injected by FXMLLoader

    // Container to store form data
    private final HashMap<String, String> formData1 = new HashMap<>();

    // Action even handler for the Sign In button
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

                formData1.clear();
            } else {
                lbErrorMessage.setText("User Name And/Or Password Incorrect");
                lbErrorMessage.setVisible(true);
                formData1.clear();
            }
        } else {
            lbErrorMessage.setText(message1);
            lbErrorMessage.setVisible(true);
            formData1.clear();
        }
    }

    // Override Initialize method to init state of the scene
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Get and set the current users location
        lbLocation.setText(AppHelper.getCurrentLocal());

        // Lamda Expressions for turning off error message once user begins to retry inputting data
        tfUserName.setOnMouseClicked(event -> {
            lbErrorMessage.setVisible(false);
        });
        tfPassword.setOnMouseClicked(event -> {
            lbErrorMessage.setVisible(false);
        });

    }



    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert lbLocation != null : "fx:id=\"lbLocation\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert tfUserName != null : "fx:id=\"tfUserName\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert tfPassword != null : "fx:id=\"tfPasswrod\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert lbErrorMessage != null : "fx:id=\"lbErrorMessage\" was not injected: check your FXML file 'SignInForm.fxml'.";
        assert btnSignIn != null : "fx:id=\"btnSignIn\" was not injected: check your FXML file 'SignInForm.fxml'.";

    }

}
