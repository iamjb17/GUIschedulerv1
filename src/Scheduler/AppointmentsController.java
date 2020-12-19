/**
 * @author Jessie Burton
 */
package Scheduler;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AppointmentsController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lbSignOut"
    private Label lbSignOut; // Value injected by FXMLLoader

    @FXML // fx:id="btnSignOut"
    private Button btnSignOut; // Value injected by FXMLLoader

    @FXML // fx:id="lbCustomers"
    private Label lbCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustomers"
    private Button btnCustomers; // Value injected by FXMLLoader

    @FXML // fx:id="lbReports"
    private Label lbReports; // Value injected by FXMLLoader

    @FXML // fx:id="btnReports"
    private Button btnReports; // Value injected by FXMLLoader

    @FXML // fx:id="lbAppointmentAlert"
    private Label lbAppointmentAlert; // Value injected by FXMLLoader

    @FXML // fx:id="rbApptByWeek"
    private RadioButton rbApptByWeek; // Value injected by FXMLLoader

    @FXML // fx:id="tgAppointments"
    private ToggleGroup tgAppointments; // Value injected by FXMLLoader

    @FXML // fx:id="rbApptByMonth"
    private RadioButton rbApptByMonth; // Value injected by FXMLLoader

    @FXML // fx:id="tvAppointments"
    private TableView<Appointments> tvAppointments; // Value injected by FXMLLoader

    @FXML // fx:id="tcID"
    private TableColumn<Appointments, Integer> tcID; // Value injected by FXMLLoader

    @FXML // fx:id="tcTitle"
    private TableColumn<Appointments, String> tcTitle; // Value injected by FXMLLoader

    @FXML // fx:id="tcDescription"
    private TableColumn<Appointments, String> tcDescription; // Value injected by FXMLLoader

    @FXML // fx:id="tcLocation"
    private TableColumn<Appointments, String> tcLocation; // Value injected by FXMLLoader

    @FXML // fx:id="tcContact"
    private TableColumn<Appointments, String> tcContact; // Value injected by FXMLLoader

    @FXML // fx:id="tcType"
    private TableColumn<Appointments, String> tcType; // Value injected by FXMLLoader

    @FXML // fx:id="tcStartDT"
    private TableColumn<Appointments, Calendar> tcStartDT; // Value injected by FXMLLoader

    @FXML // fx:id="tcEndDT"
    private TableColumn<Appointments, Calendar> tcEndDT; // Value injected by FXMLLoader

    @FXML // fx:id="tcCustomerID"
    private TableColumn<Appointments, Integer> tcCustomerID; // Value injected by FXMLLoader

    @FXML // fx:id="btnDeleteAppointment"
    private Button btnDeleteAppointment; // Value injected by FXMLLoader

    @FXML // fx:id="lbTableErrorMessage"
    private Label lbTableErrorMessage; // Value injected by FXMLLoader

    @FXML // fx:id="lbApptID"
    private Label lbApptID; // Value injected by FXMLLoader

    @FXML // fx:id="tfAppointmentID"
    private TextField tfAppointmentID; // Value injected by FXMLLoader

    @FXML // fx:id="lbContact"
    private Label lbContact; // Value injected by FXMLLoader

    @FXML // fx:id="cbContactPerson"
    private ComboBox<String> cbContactPerson; // Value injected by FXMLLoader

    @FXML // fx:id="lbTitle"
    private Label lbTitle; // Value injected by FXMLLoader

    @FXML // fx:id="tfTitle"
    private TextField tfTitle; // Value injected by FXMLLoader

    @FXML // fx:id="lbType"
    private Label lbType; // Value injected by FXMLLoader

    @FXML // fx:id="tfType"
    private TextField tfType; // Value injected by FXMLLoader

    @FXML // fx:id="lbLocation"
    private Label lbLocation; // Value injected by FXMLLoader

    @FXML // fx:id="tfLocation"
    private TextField tfLocation; // Value injected by FXMLLoader

    @FXML // fx:id="lbCustomerID"
    private Label lbCustomerID; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustomerID"
    private TextField tfCustomerID; // Value injected by FXMLLoader

    @FXML // fx:id="lbStart"
    private Label lbStart; // Value injected by FXMLLoader

    @FXML // fx:id="tfStartDT"
    private TextField tfStartDT; // Value injected by FXMLLoader

    @FXML // fx:id="lbEnd"
    private Label lbEnd; // Value injected by FXMLLoader

    @FXML // fx:id="tfEndDT"
    private TextField tfEndDT; // Value injected by FXMLLoader

    @FXML // fx:id="lbUserID"
    private Label lbUserID; // Value injected by FXMLLoader

    @FXML // fx:id="tfUserID"
    private TextField tfUserID; // Value injected by FXMLLoader

    @FXML // fx:id="btnUpdateAppointment"
    private Button btnUpdateAppointment; // Value injected by FXMLLoader

    @FXML // fx:id="btnAddAppointment"
    private Button btnAddAppointment; // Value injected by FXMLLoader

    @FXML // fx:id="btnClearForm"
    private Button btnClearForm; // Value injected by FXMLLoader

    @FXML // fx:id="lbFormErrorMessage"
    private Label lbFormErrorMessage; // Value injected by FXMLLoader

    @FXML // fx:id="lbDescription"
    private Label lbDescription; // Value injected by FXMLLoader

    @FXML // fx:id="tfDescription"
    private TextField tfDescription; // Value injected by FXMLLoader

    // Storage for form data
    private final HashMap<String, String> formData = new HashMap<>();

    // Current calendar instance
    public Calendar calendar = Calendar.getInstance();

    // Add data from the form to the storage hashmap
    private void addFormDataToMap() {
        formData.clear();
        formData.put("appointment ID", tfAppointmentID.getText());
        formData.put("title",tfTitle.getText());
        formData.put("location",tfLocation.getText());
        formData.put("type",tfType.getText());
        formData.put("start",tfStartDT.getText());
        formData.put("end",tfEndDT.getText());
        formData.put("customer ID",tfCustomerID.getText());
        formData.put("user ID", tfUserID.getText());
        formData.put("contact name", cbContactPerson.getValue());
        formData.put("description",tfDescription.getText());

    }

    // get index of selected item of the tableview once clicked
    private int getIndex(int ID, String toWhichCollection) {
        int currentIndex = -1;
        switch (toWhichCollection) {
            case "week":
                for (int i = 0; i < AppHelper.apptsByWeek.size(); i++) {
                    if (AppHelper.apptsByWeek.get(i).getAppointmentID() == ID) {
                        currentIndex = i;
                    }
                }
                break;
            case "month":
                for (int i = 0; i < AppHelper.apptsByMonth.size(); i++) {
                    if (AppHelper.apptsByMonth.get(i).getAppointmentID() == ID) {
                        currentIndex = i;
                    }
                }
                break;
            case "all" :
                for (int i = 0; i < AppHelper.appointments.size(); i++) {
                    if (AppHelper.appointments.get(i).getAppointmentID() == ID) {
                        currentIndex = i;
                    }
                }
                break;
            default:
                break;
        }
        return currentIndex;
    }

    // Get all contact names and put them in a list
    private ObservableList<String> getContactNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Contacts contact: AppHelper.contacts) {
            names.add(contact.getContactName());
        }
        return names;
    }

    // Once an appointment is clicked, add its data to the form for editing
    private void addDataToForm() {
        tfAppointmentID.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getAppointmentID()));
        tfTitle.setText(tvAppointments.getSelectionModel().getSelectedItem().getTitle());
        tfStartDT.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getStart().getTime()));
        tfEndDT.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getEnd().getTime()));
        tfType.setText(tvAppointments.getSelectionModel().getSelectedItem().getType());
        tfLocation.setText(tvAppointments.getSelectionModel().getSelectedItem().getLocation());
        tfUserID.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getUserID()));
        tfCustomerID.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getCustomerID()));
        cbContactPerson.getSelectionModel().select(tvAppointments.getSelectionModel().getSelectedItem().getContactName());

        tfDescription.setText(tvAppointments.getSelectionModel().getSelectedItem().getDescription());

    }

    // Clear all of the form data storage and text fields
    private void clearFormData() {
        formData.clear();
        // set the form buttons to default disablity position
        btnUpdateAppointment.setDisable(true);
        btnAddAppointment.setDisable(false);
        tfCustomerID.clear();
        tfUserID.clear();
        tfLocation.clear();
        tfType.clear();
        tfEndDT.clear();
        cbContactPerson.getSelectionModel().clearSelection();
        tfStartDT.clear();
        tfTitle.clear();
        tfDescription.clear();

        tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));
        tvAppointments.getSelectionModel().clearSelection();

    }

    // Get the next available number for the appointment ID field
    private int autoNextGenApptID() {
        TreeSet<Integer> temp = new TreeSet<>();
        int curr = 1;

        for (Appointments appt : tvAppointments.getItems()) {
            temp.add(tcID.getCellData(appt));
        }
        for (int i = 0; i < temp.size(); i++) {
            if (temp.contains(curr)) {
                curr +=1;
            } else {
                return curr;
            }
        }
        return curr;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set display text in proper language
        lbCustomers.setText(AppHelper.localeRB.getString("lbCustomers"));
        lbSignOut.setText(AppHelper.localeRB.getString("lbSignOut"));
        rbApptByWeek.setText(AppHelper.localeRB.getString("rbApptByWeek"));
        rbApptByMonth.setText(AppHelper.localeRB.getString("rbApptByMonth"));
        tcID.setText(AppHelper.localeRB.getString("ID"));
        tcTitle.setText(AppHelper.localeRB.getString("Title"));
        tcDescription.setText(AppHelper.localeRB.getString("Description"));
        tcLocation.setText(AppHelper.localeRB.getString("Location"));
        tcType.setText(AppHelper.localeRB.getString("Type"));
        tcStartDT.setText(AppHelper.localeRB.getString("Start"));
        tcEndDT.setText(AppHelper.localeRB.getString("End"));
        tcContact.setText(AppHelper.localeRB.getString("tcContact"));
        tcCustomerID.setText(AppHelper.localeRB.getString("CustomerID"));
        lbApptID.setText(AppHelper.localeRB.getString("lbApptID"));
        lbCustomerID.setText(AppHelper.localeRB.getString("CustomerID"));
        lbTitle.setText(AppHelper.localeRB.getString("Title"));
        lbContact.setText(AppHelper.localeRB.getString("lbContact"));
        lbLocation.setText(AppHelper.localeRB.getString("Location"));
        lbType.setText(AppHelper.localeRB.getString("Type"));
        lbStart.setText(AppHelper.localeRB.getString("Start"));
        lbEnd.setText(AppHelper.localeRB.getString("End"));
        lbDescription.setText(AppHelper.localeRB.getString("Description"));
        lbUserID.setText(AppHelper.localeRB.getString("UserID"));
        btnAddAppointment.setText(AppHelper.localeRB.getString("btnAddAppt"));
        btnClearForm.setText(AppHelper.localeRB.getString("btnClear"));
        btnUpdateAppointment.setText(AppHelper.localeRB.getString("btnUpdateAppt"));
        btnDeleteAppointment.setText(AppHelper.localeRB.getString("btnDeleteAppt"));

        // Clear form data, just in case
        formData.clear();

        // Get all initial data from data that will be used in this screen
        DBHelper.getAllAppointments();

        // Check for appointments that is within 15 min of login.
        if (AppHelper.apptIDSoon != -1) {
            lbAppointmentAlert.setText(AppHelper.localeRB.getString("altAppt") + AppHelper.apptIDSoon + " " +
                    AppHelper.localeRB.getString("altStartingSoon") + AppHelper.apptTDSoon.getTime().toString() + "!");
        }

        // set up table view by week
        tcID.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
        tcLocation.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
        tcContact.setCellValueFactory(new PropertyValueFactory<Appointments, String>("contactName"));
        tcType.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
        tcStartDT.setCellValueFactory(new PropertyValueFactory<Appointments, Calendar>("start"));
        tcEndDT.setCellValueFactory(new PropertyValueFactory<Appointments, Calendar>("end"));
        tcCustomerID.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));

        // Set the correct values for the start and end columns
        DateFormat dateFormat = DateFormat.getDateTimeInstance();
        tcStartDT.setCellFactory(col -> new TableCell<Appointments, Calendar>() {
            @Override
            protected void updateItem(Calendar date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {

                    setText(dateFormat.format(date.getTime()) + " " + date.getTimeZone().getDisplayName());
                }
            }
        });
        tcEndDT.setCellFactory(col -> new TableCell<Appointments, Calendar>() {
            @Override
            protected void updateItem(Calendar date, boolean empty) {
                super.updateItem(date, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(dateFormat.format(date.getTime()) + " " + date.getTimeZone().getDisplayName());
                }
            }
        });

        // Set the data for the contact person combobox
        cbContactPerson.setItems(getContactNames());

        // Put the correct next available appointment id in the correct field
        tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));

        // Set the appointments table with correct data depending on sort order of weeks and months
        if (rbApptByMonth.isSelected()) {
            if (!AppHelper.apptsByMonth.isEmpty()) {
                tvAppointments.setItems(AppHelper.apptsByMonth);
                tvAppointments.refresh();
            }
        } else if (rbApptByWeek.isSelected()){
            if (!AppHelper.apptsByWeek.isEmpty()) {
                tvAppointments.setItems(AppHelper.apptsByWeek);
                tvAppointments.refresh();
            }
        }

        // Set the appointments table with correct data depending on sort order of months
        rbApptByMonth.setOnAction(event -> {
            tvAppointments.setItems(AppHelper.apptsByMonth);
            tvAppointments.refresh();
        });

        // Set the appointments table with correct data depending on sort order of weeks
        rbApptByWeek.setOnAction(event -> {
            tvAppointments.setItems(AppHelper.apptsByWeek);
            tvAppointments.refresh();
        });

        // If the appointments table is clicked add the data to the form for editing and set form buttons and remove error messages
        tvAppointments.setOnMouseClicked(event -> {
            addDataToForm();
            btnAddAppointment.setDisable(true);
            btnUpdateAppointment.setDisable(false);
            lbFormErrorMessage.setVisible(false);
            lbTableErrorMessage.setVisible(false);
        });


        // Once add appointment button is clicked check the fields for correctness and attempt to add to database
        btnAddAppointment.setOnAction(event -> {
            addFormDataToMap();
            String status = AppHelper.checkFormCorrectness(formData);
            if (status.equals("correct")) {
                try {
                    AppHelper.startCalendar = AppHelper.createApptFromFormData(formData).getStart();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    AppHelper.endCalendar = AppHelper.createApptFromFormData(formData).getStart();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    DBHelper.addAppointment(AppHelper.createApptFromFormData(formData));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    AppHelper.appointments.add(AppHelper.createApptFromFormData(formData));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    AppHelper.apptsByMonth.add(AppHelper.createApptFromFormData(formData));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                    AppHelper.apptsByWeek.add(AppHelper.createApptFromFormData(formData));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DBHelper.getAllAppointments();
                tvAppointments.refresh();
                clearFormData();
                tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));
            } else {
                // if errors occur describe them
                lbFormErrorMessage.setText(status);
                lbFormErrorMessage.setVisible(true);
            }
            tvAppointments.refresh();
        });

        // Clear all form data and reset form
        btnClearForm.setOnAction(event -> {
            clearFormData();
            btnUpdateAppointment.setDisable(true);
            btnAddAppointment.setDisable(false);
        });

        // Once delete button clicked check if appointment selected and attempt to delete the appointment from database and reset form
        btnDeleteAppointment.setOnAction(event -> {
            if (!tvAppointments.getSelectionModel().isEmpty()) {
                boolean isDeleted = DBHelper.deleteAppointment(tvAppointments.getSelectionModel().getSelectedItem().getAppointmentID());
                if (isDeleted) {
                    // deletion success message
                    lbTableErrorMessage.setText(AppHelper.localeRB.getString("lbApptID") + ": " +
                            tvAppointments.getSelectionModel().getSelectedItem().getAppointmentID() + " " +
                            AppHelper.localeRB.getString("Type") + ": " + tvAppointments.getSelectionModel().getSelectedItem().getType() +
                            " " + AppHelper.localeRB.getString("erDeleteSuc"));
                    lbTableErrorMessage.setVisible(true);
                    AppHelper.apptsByMonth.remove(tvAppointments.getSelectionModel().getSelectedItem());
                    AppHelper.apptsByWeek.remove(tvAppointments.getSelectionModel().getSelectedItem());
                    AppHelper.appointments.remove(tvAppointments.getSelectionModel().getSelectedItem());
                    DBHelper.getAllAppointments();
                    tvAppointments.refresh();
                    clearFormData();
                    tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));
                } else if(!isDeleted) {
                    // deletion failed messaged
                    lbTableErrorMessage.setText("Unable To Delete Appointment");
                    lbTableErrorMessage.setVisible(true);
                }
            }
        });

        // Attempt the update the appointment in the data base with values from form
        btnUpdateAppointment.setOnAction(event -> {
            addFormDataToMap();
            String status = AppHelper.checkFormCorrectness(formData);
            if (status.equals("correct")) {
                try {
                    DBHelper.updateAppointment(AppHelper.createApptFromFormData(formData));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (rbApptByWeek.isSelected()){
                    Appointments selAppt = tvAppointments.getSelectionModel().getSelectedItem();
                    try {
                        AppHelper.apptsByWeek.set(tvAppointments.getSelectionModel().getSelectedIndex(), AppHelper.createApptFromFormData(formData));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        AppHelper.apptsByMonth.set(getIndex(selAppt.getAppointmentID(), "month"), AppHelper.createApptFromFormData(formData));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        AppHelper.appointments.set(getIndex(selAppt.getAppointmentID(), "all"), AppHelper.createApptFromFormData(formData));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (rbApptByMonth.isSelected()){
                    Appointments selAppt = tvAppointments.getSelectionModel().getSelectedItem();
                    try {
                        AppHelper.apptsByWeek.set(getIndex(selAppt.getAppointmentID(), "week"), AppHelper.createApptFromFormData(formData));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        AppHelper.apptsByMonth.set(tvAppointments.getSelectionModel().getSelectedIndex(), AppHelper.createApptFromFormData(formData));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        AppHelper.appointments.set(getIndex(selAppt.getAppointmentID(), "all"), AppHelper.createApptFromFormData(formData));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                tvAppointments.refresh();
                clearFormData();
                tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));
            } else {
                lbFormErrorMessage.setText(status);
                lbFormErrorMessage.setVisible(true);
            }
        });

        // Go to customer window and reset the data
        btnCustomers.setOnAction(event -> {
            AppHelper.resetData();
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("../Resources/CustomerScene.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(parent));
        });

        // Sign out of the app and clear session data
        btnSignOut.setOnAction(event -> {
            AppHelper.clearSessionData();
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("../Resources/SigninForm.fxml"));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(parent));
        });

        // Go to reports window and set the variable of backWhere to appointments
        btnReports.setOnAction(event -> {
            AppHelper.backWhere = "appointment";
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
        tfTitle.setOnMouseClicked(event -> { lbFormErrorMessage.setVisible(false); lbTableErrorMessage.setVisible(false);});
        tfStartDT.setOnMouseClicked(event -> { lbFormErrorMessage.setVisible(false); lbTableErrorMessage.setVisible(false);});
        tfEndDT.setOnMouseClicked(event -> { lbFormErrorMessage.setVisible(false); lbTableErrorMessage.setVisible(false);});
        tfType.setOnMouseClicked(event -> { lbFormErrorMessage.setVisible(false); lbTableErrorMessage.setVisible(false);});
        tfLocation.setOnMouseClicked(event -> { lbFormErrorMessage.setVisible(false); lbTableErrorMessage.setVisible(false);});
        tfUserID.setOnMouseClicked(event -> { lbFormErrorMessage.setVisible(false); lbTableErrorMessage.setVisible(false);});
        tfCustomerID.setOnMouseClicked(event -> { lbFormErrorMessage.setVisible(false); lbTableErrorMessage.setVisible(false);});
        cbContactPerson.setOnMouseClicked(event -> { lbFormErrorMessage.setVisible(false); lbTableErrorMessage.setVisible(false);});
        tfDescription.setOnMouseClicked(event -> { lbFormErrorMessage.setVisible(false); lbTableErrorMessage.setVisible(false);});

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert lbSignOut != null : "fx:id=\"lbSignOut\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnSignOut != null : "fx:id=\"btnSignOut\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbCustomers != null : "fx:id=\"lbCustomers\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbReports != null : "fx:id=\"lbReports\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnReports != null : "fx:id=\"btnReports\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbAppointmentAlert != null : "fx:id=\"lbAppointmentAlert\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert rbApptByWeek != null : "fx:id=\"rbApptByWeek\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tgAppointments != null : "fx:id=\"tgAppointments\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert rbApptByMonth != null : "fx:id=\"rbApptByMonth\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tvAppointments != null : "fx:id=\"tvAppointments\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tcID != null : "fx:id=\"tcID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tcTitle != null : "fx:id=\"tcTitle\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tcDescription != null : "fx:id=\"tcDescription\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tcLocation != null : "fx:id=\"tcLocation\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tcContact != null : "fx:id=\"tcContact\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tcType != null : "fx:id=\"tcType\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tcStartDT != null : "fx:id=\"tcStartDT\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tcEndDT != null : "fx:id=\"tcEndDT\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tcCustomerID != null : "fx:id=\"tcCustomerID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnDeleteAppointment != null : "fx:id=\"btnDeleteAppointment\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbTableErrorMessage != null : "fx:id=\"lbTableErrorMessage\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbApptID != null : "fx:id=\"lbApptID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfAppointmentID != null : "fx:id=\"tfAppointmentID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbContact != null : "fx:id=\"lbContact\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert cbContactPerson != null : "fx:id=\"cbContactPerson\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbTitle != null : "fx:id=\"lbTitle\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfTitle != null : "fx:id=\"tfTitle\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbType != null : "fx:id=\"lbType\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfType != null : "fx:id=\"tfType\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbLocation != null : "fx:id=\"lbLocation\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfLocation != null : "fx:id=\"tfLocation\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbCustomerID != null : "fx:id=\"lbCustomerID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfCustomerID != null : "fx:id=\"tfCustomerID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbStart != null : "fx:id=\"lbStart\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfStartDT != null : "fx:id=\"tfStartDT\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbEnd != null : "fx:id=\"lbEnd\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfEndDT != null : "fx:id=\"tfEndDT\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbUserID != null : "fx:id=\"lbUserID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfUserID != null : "fx:id=\"tfUserID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnUpdateAppointment != null : "fx:id=\"btnUpdateAppointment\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnAddAppointment != null : "fx:id=\"btnAddAppointment\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnClearForm != null : "fx:id=\"btnClearForm\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbFormErrorMessage != null : "fx:id=\"lbFormErrorMessage\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbDescription != null : "fx:id=\"lbDescription\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfDescription != null : "fx:id=\"tfDescription\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";

    }

}
