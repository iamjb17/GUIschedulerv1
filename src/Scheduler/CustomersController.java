/**
 * @author Jessie Burton
 */

package Scheduler;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class CustomersController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

//    @FXML // fx:id="mnCustomers"
//    private Menu mnCustomers; // Value injected by FXMLLoader

//    @FXML // fx:id="mnAppointments"
//    private Menu mnAppointments; // Value injected by FXMLLoader

    @FXML // fx:id="lbAppointments"
    private Label lbAppointments; // Value injected by FXMLLoader

    @FXML // fx:id="btnAppointmentsLabel"
    private Button btnAppointmentsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="btnSignOutLabel"
    private Button btnSignOutLabel; // Value injected by FXMLLoader

//    @FXML // fx:id="mnExit"
//    private Menu mnExit; // Value injected by FXMLLoader

    @FXML // fx:id="lbExit"
    private Label lbExit; // Value injected by FXMLLoader

    @FXML // fx:id="lbAppointmentAlert"
    private Label lbAppointmentAlert; // Value injected by FXMLLoader

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
    private ComboBox<String> cbState; // Value injected by FXMLLoader

    @FXML // fx:id="cbCountry"
    private ComboBox<String> cbCountry; // Value injected by FXMLLoader

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

    @FXML // fx:id="lbDeleteMessage"
    private Label lbDeleteMessage; // Value injected by FXMLLoader

    @FXML // fx:id="btnGoToAppts"
    private Button btnGoToAppts; // Value injected by FXMLLoader

    // Container for to store and move around form data
    private final HashMap<String, String> formData = new HashMap<>();


    @FXML
    void onSignOutBtnClicked(ActionEvent event) throws IOException {
        AppHelper.clearSessionData();
        Parent parent = FXMLLoader.load(getClass().getResource("../Resources/SigninForm.fxml"));
        Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        primaryStage.setScene(new Scene(parent));
    }

    // has the state and country been selected already storage value
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

    private ObservableList<String> getFirstDivisionNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (FirstDivision state: AppHelper.firstDivisions) {
            names.add(state.getDivisionName());
        }
        return names;
    }

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

        // temp container for filtered states and countries to leave the original list unchanged
        ObservableList<String> filteredStates = FXCollections.observableArrayList();
        ObservableList<String> filteredCountries = FXCollections.observableArrayList();

//        DBHelper.showAllDB();

        // Setup Customers tableview
        tcID.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("customerID"));
        tcName.setCellValueFactory(new PropertyValueFactory<Customers, String>("name"));
        tcAddress.setCellValueFactory(new PropertyValueFactory<Customers, String>("address"));
        tcState.setCellValueFactory(new PropertyValueFactory<Customers, String>("state"));
        tcCountry.setCellValueFactory(new PropertyValueFactory<Customers, String>("country"));
        tcPostalCode.setCellValueFactory(new PropertyValueFactory<Customers, String>("postalCode"));
        tcPhoneNumber.setCellValueFactory(new PropertyValueFactory<Customers, String>("phone"));

        // Get all customers from db and fill the table view with data from database
        DBHelper.setInitDataFromDB();
        if (!AppHelper.customers.isEmpty()) {
            tvCustomerTable.setItems(AppHelper.customers);
        }


        // Fill state and country comboboxes with data originally from database
        cbState.setItems(getFirstDivisionNames());
        cbCountry.setItems(getCountryNames());

        // Add autogen ID to form on add
        tfCustomerID.setText(String.valueOf(autoNextGenCustomerID()));

//        btnAppointmentsLabel.setOnAction(event -> {
//            Parent parent = null;
//            try {
//                parent = FXMLLoader.load(getClass().getResource("../Resources/AppointmentsScene.fxml"));
//            } catch (IOException exception) {
//                exception.printStackTrace();
//            }
//            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
//            primaryStage.setScene(new Scene(parent));
//
//        });

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

        tvCustomerTable.setOnMouseClicked(event -> {
            addDataToForm();
            btnAddCustomer.setDisable(true);
            btnUpdateCustomer.setDisable(false);
            lbErrorMessage.setVisible(false);
            lbDeleteMessage.setVisible(false);
        });

        btnAddCustomer.setOnAction(event -> {
            addFormDataToMap();
            System.out.println(formData.toString());
            String statusMessage = AppHelper.checkFormCorrectness(formData);
            if (statusMessage.equals("correct")) {
//                DBHelper.getConnection();
//                AppHelper.createCustomerFromFormData(formData);
                DBHelper.addCustomer(AppHelper.createCustomerFromFormData(formData));
                AppHelper.customers.add(AppHelper.createCustomerFromFormData(formData));
                clearFormData();
            } else {
                lbErrorMessage.setText(statusMessage);
                lbErrorMessage.setVisible(true);
            }
        });

        btnClear.setOnAction(event -> {
            clearFormData();
        });

        btnDeleteCustomer.setOnAction(event -> {
            if (tvCustomerTable.getSelectionModel().isEmpty()) {
                lbDeleteMessage.setText("No Customer Selected");
                lbDeleteMessage.setVisible(true);
            } else {
                if (DBHelper.deleteCustomer(tvCustomerTable.getSelectionModel().getSelectedItem().getCustomerID())) {
                    AppHelper.customers.remove(tvCustomerTable.getSelectionModel().getSelectedItem());
                    lbDeleteMessage.setText("Item was deleted successfully");
                    lbDeleteMessage.setVisible(true);
                    clearFormData();
                } else {
                    lbDeleteMessage.setText("Unable to delete. Item must have appointment associated with it. Delete appointment first.");
                    lbDeleteMessage.setVisible(true);
                }
            }

        });

        btnUpdateCustomer.setOnAction(event -> {
            addFormDataToMap();
            System.out.println(formData.toString());
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
        assert lbExit != null : "fx:id=\"lbExit\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnSignOutLabel != null : "fx:id=\"btnSignOutLabel\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnGoToAppts != null : "fx:id=\"btnGoToAppts\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbAppointmentAlert != null : "fx:id=\"lbAppointmentAlert\" was not injected: check your FXML file 'CustomerScene.fxml'.";
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
        assert tfCustomerID != null : "fx:id=\"tfCustomerID\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfName != null : "fx:id=\"tfName\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfAddress != null : "fx:id=\"tfAddress\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert cbCountry != null : "fx:id=\"cbCountry\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert cbState != null : "fx:id=\"cbState\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfPostalCode != null : "fx:id=\"tfPostalCode\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert tfPhoneNumber != null : "fx:id=\"tfPhoneNumber\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnUpdateCustomer != null : "fx:id=\"btnUpdateCustomer\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert btnAddCustomer != null : "fx:id=\"btnAddCustomer\" was not injected: check your FXML file 'CustomerScene.fxml'.";
        assert lbErrorMessage != null : "fx:id=\"lbErrorMessage\" was not injected: check your FXML file 'CustomerScene.fxml'.";

    }
}
