/**
 * @author Jessie Burton
 */

package Scheduler;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

// Class for handling the Customers scene
public class CustomersController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lbSignOut"
    private Label lbSignOut; // Value injected by FXMLLoader

    @FXML // fx:id="btnSignOutLabel"
    private Button btnSignOutLabel; // Value injected by FXMLLoader

    @FXML // fx:id="lbReports"
    private Label lbReports; // Value injected by FXMLLoader

    @FXML // fx:id="btnReports"
    private Button btnReports; // Value injected by FXMLLoader

    @FXML // fx:id="lbAppts"
    private Label lbAppts; // Value injected by FXMLLoader

    @FXML // fx:id="btnGoToAppts"
    private Button btnGoToAppts; // Value injected by FXMLLoader

    @FXML // fx:id="lbAppointmentAlert"
    private Label lbAppointmentAlert; // Value injected by FXMLLoader

    @FXML // fx:id="lbCustomerTbl"
    private Label lbCustomerTbl; // Value injected by FXMLLoader

    @FXML // fx:id="tvCustomerTable"
    private TableView<Customers> tvCustomerTable; // Value injected by FXMLLoader

    @FXML // fx:id="tcID"
    private TableColumn<Customers, Integer> tcID; // Value injected by FXMLLoader

    @FXML // fx:id="tcName"
    private TableColumn<Customers, String> tcName; // Value injected by FXMLLoader

    @FXML // fx:id="tcAddress"
    private TableColumn<Customers, String> tcAddress; // Value injected by FXMLLoader

    @FXML // fx:id="tcState"
    private TableColumn<Customers, String> tcState; // Value injected by FXMLLoader

    @FXML // fx:id="tcCountry"
    private TableColumn<Customers, String> tcCountry; // Value injected by FXMLLoader

    @FXML // fx:id="tcPostalCode"
    private TableColumn<Customers, String> tcPostalCode; // Value injected by FXMLLoader

    @FXML // fx:id="tcPhoneNumber"
    private TableColumn<Customers, String> tcPhoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="lbDeleteMessage"
    private Label lbDeleteMessage; // Value injected by FXMLLoader

    @FXML // fx:id="btnDeleteCustomer"
    private Button btnDeleteCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="lbCustomerID"
    private Label lbCustomerID; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustomerID"
    private TextField tfCustomerID; // Value injected by FXMLLoader

    @FXML // fx:id="lbName"
    private Label lbName; // Value injected by FXMLLoader

    @FXML // fx:id="tfName"
    private TextField tfName; // Value injected by FXMLLoader

    @FXML // fx:id="lbAddress"
    private Label lbAddress; // Value injected by FXMLLoader

    @FXML // fx:id="tfAddress"
    private TextField tfAddress; // Value injected by FXMLLoader

    @FXML // fx:id="lbCountry"
    private Label lbCountry; // Value injected by FXMLLoader

    @FXML // fx:id="cbState"
    private ComboBox<String> cbState; // Value injected by FXMLLoader

    @FXML // fx:id="lbState"
    private Label lbState; // Value injected by FXMLLoader

    @FXML // fx:id="cbCountry"
    private ComboBox<String> cbCountry; // Value injected by FXMLLoader

    @FXML // fx:id="lbPostalCode"
    private Label lbPostalCode; // Value injected by FXMLLoader

    @FXML // fx:id="tfPostalCode"
    private TextField tfPostalCode; // Value injected by FXMLLoader

    @FXML // fx:id="lbPhone"
    private Label lbPhone; // Value injected by FXMLLoader

    @FXML // fx:id="tfPhoneNumber"
    private TextField tfPhoneNumber; // Value injected by FXMLLoader

    @FXML // fx:id="btnClear"
    private Button btnClear; // Value injected by FXMLLoader

    @FXML // fx:id="btnUpdateCustomer"
    private Button btnUpdateCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddCustomer"
    private Button btnAddCustomer; // Value injected by FXMLLoader

    @FXML // fx:id="lbErrorMessage"
    private Label lbErrorMessage; // Value injected by FXMLLoader

    // Container for to store and move around form data
    private final HashMap<String, String> formData = new HashMap<>();

    // Action handler for signing out
    @FXML
    void onSignOutBtnClicked(ActionEvent event) throws IOException {
        AppHelper.clearSessionData();
        Parent parent = FXMLLoader.load(getClass().getResource("../Resources/SigninForm.fxml"));
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(parent));
    }

    // has the state and country been selected already storage values
    private boolean stateHasBeenSelected = false;
    private boolean countryHasBeenSelected = false;

    // When a button is clicked add the form data to the hashmap
    private void addFormDataToMap() {
        formData.clear();
        formData.put("customer name",tfName.getText());
        formData.put("customer ID",tfCustomerID.getText());
        formData.put("address",tfAddress.getText());
        formData.put("phone number",tfPhoneNumber.getText());
        formData.put("state",cbState.getValue());
        formData.put("country",cbCountry.getValue());
        formData.put("postal code", tfPostalCode.getText());
    }

    // return the first division (state) name
    private ObservableList<String> getFirstDivisionNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (FirstDivision state: AppHelper.firstDivisions) {
            names.add(state.getDivisionName());
        }
        return names;
    }

    // return all/only country names from countries
    private ObservableList<String> getCountryNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Country country: AppHelper.countries) {
            names.add(country.getCountryName());
        }
        return names;
    }

    // Add customer data to form when one is selected from table view
    private void addDataToForm() {
        tfCustomerID.setText(String.valueOf(tvCustomerTable.getSelectionModel().getSelectedItem().getCustomerID()));
        tfAddress.setText(tvCustomerTable.getSelectionModel().getSelectedItem().getAddress());
        tfPhoneNumber.setText(tvCustomerTable.getSelectionModel().getSelectedItem().getPhone());
        tfPostalCode.setText(tvCustomerTable.getSelectionModel().getSelectedItem().getPostalCode());
        tfName.setText(tvCustomerTable.getSelectionModel().getSelectedItem().getName());

        cbState.getSelectionModel().select(tvCustomerTable.getSelectionModel().getSelectedItem().getState());
        cbCountry.getSelectionModel().select(tvCustomerTable.getSelectionModel().getSelectedItem().getCountry());
    }

    // Clear all form data
    private void clearFormData() {
        btnUpdateCustomer.setDisable(true);
        btnAddCustomer.setDisable(false);
        tfPostalCode.clear();
        tfPhoneNumber.clear();
        tfName.clear();
        tfAddress.clear();
        tfCustomerID.clear();
        cbState.getSelectionModel().clearSelection();
        cbCountry.getSelectionModel().clearSelection();

        stateHasBeenSelected = false;
        countryHasBeenSelected = false;

        tfCustomerID.setText(String.valueOf(autoNextGenCustomerID()));
        cbState.setItems(getFirstDivisionNames());
        cbCountry.setItems(getCountryNames());
    }

    // get next available customer id number
    private int autoNextGenCustomerID() {
        TreeSet<Integer> temp = new TreeSet<>();
        int curr = 1;

        for (Customers id : tvCustomerTable.getItems()) {
            temp.add(tcID.getCellData(id));
        }
        for (int i = 0; i < temp.size(); i++) {
            if (temp.contains(curr)) {
                curr +=1;
            } else {
                return curr;
            }
        }
        System.out.println(temp);
        return curr;
    }

    // This method is called when initialization is complete. Override from Initializable Interface
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set display text in proper language
        lbAppts.setText(AppHelper.localeRB.getString("lbAppt"));
        lbSignOut.setText(AppHelper.localeRB.getString("lbSignOut"));
        lbCustomerTbl.setText(AppHelper.localeRB.getString("lbCustomerTbl"));
        tcID.setText(AppHelper.localeRB.getString("ID"));
        tcName.setText(AppHelper.localeRB.getString("Name"));
        tcAddress.setText(AppHelper.localeRB.getString("Address"));
        tcPhoneNumber.setText(AppHelper.localeRB.getString("Phone"));
        tcPostalCode.setText(AppHelper.localeRB.getString("Postal"));
        tcCountry.setText(AppHelper.localeRB.getString("Country"));
        tcState.setText(AppHelper.localeRB.getString("State"));
        lbCustomerID.setText(AppHelper.localeRB.getString("ID"));
        lbName.setText(AppHelper.localeRB.getString("Name"));
        lbPhone.setText(AppHelper.localeRB.getString("Phone"));
        lbPostalCode.setText(AppHelper.localeRB.getString("Postal"));
        lbCountry.setText(AppHelper.localeRB.getString("Country"));
        lbState.setText(AppHelper.localeRB.getString("State"));
        lbAddress.setText(AppHelper.localeRB.getString("Address"));
        btnAddCustomer.setText(AppHelper.localeRB.getString("btnAddCustomer"));
        btnClear.setText(AppHelper.localeRB.getString("btnClear"));
        btnUpdateCustomer.setText(AppHelper.localeRB.getString("btnUpdateCustomer"));
        btnDeleteCustomer.setText(AppHelper.localeRB.getString("btnDeleteCustomer"));


        // temp container for filtered states and countries to leave the original list unchanged
        ObservableList<String> filteredStates = FXCollections.observableArrayList();
        ObservableList<String> filteredCountries = FXCollections.observableArrayList();

        // uncomment to show database structure in console
//        DBHelper.showAllDB();

        // Setup Customers tableview
        tcID.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("customerID"));
        tcName.setCellValueFactory(new PropertyValueFactory<Customers, String>("name"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
        tcState.setCellValueFactory(new PropertyValueFactory<Customers, String>("state"));
        tcCountry.setCellValueFactory(new PropertyValueFactory<Customers, String>("country"));
        tcPostalCode.setCellValueFactory(new PropertyValueFactory<Customers, String>("postalCode"));
        tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<Customers, String>("phone"));

        // Get all customers from db and fill the table view with data from database if not done already
        if (AppHelper.customers.isEmpty()) {
            DBHelper.setInitDataFromDB();
        }

        // set customer table view with proper data
        tvCustomerTable.setItems(AppHelper.customers);

        // Check for appointments that is within 15 min of login.
        AppHelper.apptWithinTimeFrame();
        // Set Alert if appointment is within 15 min
        if (AppHelper.apptIDSoon != -1) {
            lbAppointmentAlert.setText(AppHelper.localeRB.getString("altAppt") + AppHelper.apptIDSoon + " " +
                    AppHelper.localeRB.getString("altStartingSoon") + AppHelper.apptTDSoon.getTime().toString() + "!");
        }

        // Fill state and country comboboxes with data originally from database
        cbState.setItems(getFirstDivisionNames());
        cbCountry.setItems(getCountryNames());

        // Add autogen ID to form on add
        tfCustomerID.setText(String.valueOf(autoNextGenCustomerID()));

        // Lamda expression changing to appointments scene
        btnGoToAppts.setOnAction(event -> {
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("../Resources/AppointmentsScene.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(parent));
        });

        // Customize the data per country/state selected
        cbState.setOnAction(event -> {
            filteredCountries.clear();
            if(!countryHasBeenSelected) {
                FirstDivision tempFD = AppHelper.getFirstDivision(cbState.getValue());
                for (Country country : AppHelper.countries) {
                    if (tempFD != null && tempFD.getCountryID() == country.getCountryID()) {
                        filteredCountries.add(country.getCountryName());
                    }
                }
                cbCountry.setItems(filteredCountries);
            }
            stateHasBeenSelected = true;
        });

        // Customize the data per country/state selected
        cbCountry.setOnAction(event -> {
            filteredStates.clear();
            if(!stateHasBeenSelected) {
                Country tempCountry = AppHelper.getCountry(cbCountry.getValue());
                for (FirstDivision firstDivision : AppHelper.firstDivisions) {
                    if (tempCountry != null && firstDivision.getCountryID() == tempCountry.getCountryID()) {
                        filteredStates.add(firstDivision.getDivisionName());
                    }
                }
                cbState.setItems(filteredStates);
            }
            countryHasBeenSelected = true;
        });

        // Once customer table clicked attempt to add clicked items data to form
        tvCustomerTable.setOnMouseClicked(event -> {
            addDataToForm();
            btnAddCustomer.setDisable(true);
            btnUpdateCustomer.setDisable(false);
            lbErrorMessage.setVisible(false);
            lbDeleteMessage.setVisible(false);
        });

        // Once add customer button clicked add the form data to storage map, check for correctness, attempt to add to database
        btnAddCustomer.setOnAction(event -> {
            addFormDataToMap();
            String statusMessage = AppHelper.checkFormCorrectness(formData);
            if (statusMessage.equals("correct")) {
                DBHelper.addCustomer(AppHelper.createCustomerFromFormData(formData));
                AppHelper.customers.add(AppHelper.createCustomerFromFormData(formData));
                clearFormData();
            } else {
                lbErrorMessage.setText(statusMessage);
                lbErrorMessage.setVisible(true);
            }
        });

        // clear form data button on action
        btnClear.setOnAction(event -> {
            clearFormData();
        });

        // Once delete button clicked, check if customer was selected and attempt to delete from database
        btnDeleteCustomer.setOnAction(event -> {
            if (tvCustomerTable.getSelectionModel().isEmpty()) {
                lbDeleteMessage.setText(AppHelper.localeRB.getString("erNoSelection"));
                lbDeleteMessage.setVisible(true);
            } else {
                if (DBHelper.deleteCustomer(tvCustomerTable.getSelectionModel().getSelectedItem().getCustomerID())) {
                    AppHelper.customers.remove(tvCustomerTable.getSelectionModel().getSelectedItem());
                    lbDeleteMessage.setText(tvCustomerTable.getSelectionModel().getSelectedItem().getName() +" "+ AppHelper.localeRB.getString("erDeleteSuc"));
                    lbDeleteMessage.setVisible(true);
                    clearFormData();
                } else {
                    lbDeleteMessage.setText(AppHelper.localeRB.getString("erUnableToDelete"));
                    lbDeleteMessage.setVisible(true);
                }
            }
        });

        // Update the customer on action, once fields checked for correctness attempt to update the database
        btnUpdateCustomer.setOnAction(event -> {
            addFormDataToMap();
            String statusMessage = AppHelper.checkFormCorrectness(formData);
            if (statusMessage.equals("correct")) {
                DBHelper.updateCustomer(AppHelper.createCustomerFromFormData(formData));
                AppHelper.customers.set(Integer.parseInt(formData.get("customer ID"))-1, AppHelper.createCustomerFromFormData(formData));
                clearFormData();
            } else {
                lbErrorMessage.setText(statusMessage);
                lbErrorMessage.setVisible(true);
            }
        });

        // Go to reports window and set backWhere variable
        btnReports.setOnAction(event -> {
            AppHelper.backWhere = "customer";
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("../Resources/SummaryReport.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(parent));
        });

        // If any of the the form fields get focused, remove error message
        tfAddress.setOnMouseClicked(event -> { lbErrorMessage.setVisible(false); lbDeleteMessage.setVisible(false);});
        tfName.setOnMouseClicked(event -> { lbErrorMessage.setVisible(false); lbDeleteMessage.setVisible(false);});
        tfPhoneNumber.setOnMouseClicked(event -> { lbErrorMessage.setVisible(false); lbDeleteMessage.setVisible(false);});
        tfPostalCode.setOnMouseClicked(event -> { lbErrorMessage.setVisible(false); lbDeleteMessage.setVisible(false);});
        cbState.setOnMouseClicked(event -> { lbErrorMessage.setVisible(false); lbDeleteMessage.setVisible(false);});
        cbCountry.setOnMouseClicked(event -> { lbErrorMessage.setVisible(false); lbDeleteMessage.setVisible(false);});

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert lbSignOut != null : "fx:id=\"lbSignOut\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnSignOutLabel != null : "fx:id=\"btnSignOutLabel\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbAppts != null : "fx:id=\"lbAppts\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnGoToAppts != null : "fx:id=\"btnGoToAppts\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbReports != null : "fx:id=\"lbReports\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnReports != null : "fx:id=\"btnReports\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbAppointmentAlert != null : "fx:id=\"lbAppointmentAlert\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbCustomerTbl != null : "fx:id=\"lbCustomerTbl\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tvCustomerTable != null : "fx:id=\"tvCustomerTable\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcID != null : "fx:id=\"tcID\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcName != null : "fx:id=\"tcName\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcAddress != null : "fx:id=\"tcAddress\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcState != null : "fx:id=\"tcState\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcCountry != null : "fx:id=\"tcCountry\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcPostalCode != null : "fx:id=\"tcPostalCode\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tcPhoneNumber != null : "fx:id=\"tcPhoneNumber\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbDeleteMessage != null : "fx:id=\"lbDeleteMessage\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnDeleteCustomer != null : "fx:id=\"btnDeleteCustomer\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbCustomerID != null : "fx:id=\"lbCustomerID\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfCustomerID != null : "fx:id=\"tfCustomerID\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbName != null : "fx:id=\"lbName\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbAddress != null : "fx:id=\"lbAddress\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfAddress != null : "fx:id=\"tfAddress\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbCountry != null : "fx:id=\"lbCountry\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert cbCountry != null : "fx:id=\"cbCountry\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbState != null : "fx:id=\"lbState\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert cbState != null : "fx:id=\"cbState\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbPostalCode != null : "fx:id=\"lbPostalCode\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfPostalCode != null : "fx:id=\"tfPostalCode\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbPhone != null : "fx:id=\"lbPhone\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfPhoneNumber != null : "fx:id=\"tfPhoneNumber\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnUpdateCustomer != null : "fx:id=\"btnUpdateCustomer\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnAddCustomer != null : "fx:id=\"btnAddCustomer\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbErrorMessage != null : "fx:id=\"lbErrorMessage\" was not injected: check your FXML file 'CustomerScene.fxml'.";

    }
}
