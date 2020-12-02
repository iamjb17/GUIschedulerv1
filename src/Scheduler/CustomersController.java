/**
 * @author Jessie Burton
 */

package Scheduler;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class CustomersController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="mnCustomers"
    private Menu mnCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="mnAppointments"
    private Menu mnAppointments; // Value injected by FXMLLoader

    @FXML // fx:id="lbAppointments"
    private Label lbAppointments; // Value injected by FXMLLoader

    @FXML // fx:id="mnExit"
    private Menu mnExit; // Value injected by FXMLLoader

    @FXML // fx:id="lbExit"
    private Label lbExit; // Value injected by FXMLLoader

    @FXML // fx:id="lbAppointmentAlert"
    private Label lbAppointmentAlert; // Value injected by FXMLLoader

    @FXML // fx:id="tvCustomerTable"
    private TableView<?> tvCustomerTable; // Value injected by FXMLLoader

    @FXML // fx:id="tcID"
    private TableColumn<?, ?> tcID; // Value injected by FXMLLoader

    @FXML // fx:id="tcName"
    private TableColumn<?, ?> tcName; // Value injected by FXMLLoader

    @FXML // fx:id="tcAddress"
    private TableColumn<?, ?> tcAddress; // Value injected by FXMLLoader

    @FXML // fx:id="tcState"
    private TableColumn<?, ?> tcState; // Value injected by FXMLLoader

    @FXML // fx:id="tcCountry"
    private TableColumn<?, ?> tcCountry; // Value injected by FXMLLoader

    @FXML // fx:id="tcPostalCode"
    private TableColumn<?, ?> tcPostalCode; // Value injected by FXMLLoader

    @FXML // fx:id="tcPhoneNumber"
    private TableColumn<?, ?> tcPhoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="lbErrorMessage"
    private Label lbErrorMessage; // Value injected by FXMLLoader

    @FXML // fx:id="btnDeleteCustomer"
    private Button btnDeleteCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustomerID"
    private TextField tfCustomerID; // Value injected by FXMLLoader

    @FXML // fx:id="tfName"
    private TextField tfName; // Value injected by FXMLLoader

    @FXML // fx:id="tfAddress"
    private TextField tfAddress; // Value injected by FXMLLoader

    @FXML // fx:id="cbState"
    private ComboBox<?> cbState; // Value injected by FXMLLoader

    @FXML // fx:id="cbCountry"
    private ComboBox<?> cbCountry; // Value injected by FXMLLoader

    @FXML // fx:id="tfPostalCode"
    private TextField tfPostalCode; // Value injected by FXMLLoader

    @FXML // fx:id="tfPhoneNumber"
    private TextField tfPhoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="btnUpdateCustomer"
    private Button btnUpdateCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddCustomer"
    private Button btnAddCustomer; // Value injected by FXMLLoader

    @FXML
    void onAppointmentsLabelClicked(MouseEvent event) {

    }

    @FXML
    void onExitLabelClicked(MouseEvent event) {

    }

    // This method is called when initialization is complete. Override from Initializable Interface
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert mnCustomers != null : "fx:id=\"mnCustomers\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert mnAppointments != null : "fx:id=\"mnAppointments\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbAppointments != null : "fx:id=\"lbAppointments\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert mnExit != null : "fx:id=\"mnExit\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbExit != null : "fx:id=\"lbExit\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbAppointmentAlert != null : "fx:id=\"lbAppointmentAlert\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tvCustomerTable != null : "fx:id=\"tvCustomerTable\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcID != null : "fx:id=\"tcID\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcName != null : "fx:id=\"tcName\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcAddress != null : "fx:id=\"tcAddress\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcState != null : "fx:id=\"tcState\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcCountry != null : "fx:id=\"tcCountry\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcPostalCode != null : "fx:id=\"tcPostalCode\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcPhoneNumber != null : "fx:id=\"tcPhoneNumber\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbErrorMessage != null : "fx:id=\"lbErrorMessage\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnDeleteCustomer != null : "fx:id=\"btnDeleteCustomer\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfCustomerID != null : "fx:id=\"tfCustomerID\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfAddress != null : "fx:id=\"tfAddress\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert cbState != null : "fx:id=\"cbState\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert cbCountry != null : "fx:id=\"cbCountry\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfPostalCode != null : "fx:id=\"tfPostalCode\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfPhoneNumber != null : "fx:id=\"tfPhoneNumber\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnUpdateCustomer != null : "fx:id=\"btnUpdateCustomer\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnAddCustomer != null : "fx:id=\"btnAddCustomer\" was not injected: check your FXML file 'CustomerScene.fxml'.";

    }
}
