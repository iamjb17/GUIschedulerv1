package Scheduler;

/**
 * @author Jessie Burton
 */

import java.io.IOException;
import java.net.URL;
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

    @FXML
    void onClick(ActionEvent event) throws IOException {
        DBHelper.getConnection();
        Parent parent = FXMLLoader.load(getClass().getResource("../Resources/CustomerScene.fxml"));
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(parent));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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
