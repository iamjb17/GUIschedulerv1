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

/**
 * Class to control all UI on the appointments scene
 */
public class AppointmentsController implements Initializable {

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
     * FXML value for the sign out label
     */
    @FXML // fx:id="lbSignOut"
    private Label lbSignOut; // Value injected by FXMLLoader

    /**
     * FXML value for the sign out label
     */
    @FXML // fx:id="btnSignOut"
    private Button btnSignOut; // Value injected by FXMLLoader

    /**
     * FXML value for the Customers label
     */
    @FXML // fx:id="lbCustomers"
    private Label lbCustomers; // Value injected by FXMLLoader

    /**
     * FXML value for the Customers scene button
     */
    @FXML // fx:id="btnCustomers"
    private Button btnCustomers; // Value injected by FXMLLoader

    /**
     * FXML value for the Reports label
     */
    @FXML // fx:id="lbReports"
    private Label lbReports; // Value injected by FXMLLoader

    /**
     * FXML value for the Reports scene button
     */
    @FXML // fx:id="btnReports"
    private Button btnReports; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment alert label
     */
    @FXML // fx:id="lbAppointmentAlert"
    private Label lbAppointmentAlert; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment by week radio button
     */
    @FXML // fx:id="rbApptByWeek"
    private RadioButton rbApptByWeek; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointments filter radio buttons toggle group
     */
    @FXML // fx:id="tgAppointments"
    private ToggleGroup tgAppointments; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment by month radio button
     */
    @FXML // fx:id="rbApptByMonth"
    private RadioButton rbApptByMonth; // Value injected by FXMLLoader

    /**
     * FXML value for the appointments table view
     */
    @FXML // fx:id="tvAppointments"
    private TableView<Appointments> tvAppointments; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment ID table column
     */
    @FXML // fx:id="tcID"
    private TableColumn<Appointments, Integer> tcID; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment title table column
     */
    @FXML // fx:id="tcTitle"
    private TableColumn<Appointments, String> tcTitle; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment description table column
     */
    @FXML // fx:id="tcDescription"
    private TableColumn<Appointments, String> tcDescription; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment location table column
     */
    @FXML // fx:id="tcLocation"
    private TableColumn<Appointments, String> tcLocation; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment contact table column
     */
    @FXML // fx:id="tcContact"
    private TableColumn<Appointments, String> tcContact; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment type column
     */
    @FXML // fx:id="tcType"
    private TableColumn<Appointments, String> tcType; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment start table column
     */
    @FXML // fx:id="tcStartDT"
    private TableColumn<Appointments, Calendar> tcStartDT; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment end table column
     */
    @FXML // fx:id="tcEndDT"
    private TableColumn<Appointments, Calendar> tcEndDT; // Value injected by FXMLLoader

    /**
     * FXML value for the Appointment customre ID table column
     */
    @FXML // fx:id="tcCustomerID"
    private TableColumn<Appointments, Integer> tcCustomerID; // Value injected by FXMLLoader

    /**
     * FXML value for the delete appointment button
     */
    @FXML // fx:id="btnDeleteAppointment"
    private Button btnDeleteAppointment; // Value injected by FXMLLoader

    /**
     * FXML value for the table error message label
     */
    @FXML // fx:id="lbTableErrorMessage"
    private Label lbTableErrorMessage; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment id form label
     */
    @FXML // fx:id="lbApptID"
    private Label lbApptID; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment id text field
     */
    @FXML // fx:id="tfAppointmentID"
    private TextField tfAppointmentID; // Value injected by FXMLLoader

    /**
     * FXML value for the contact form label
     */
    @FXML // fx:id="lbContact"
    private Label lbContact; // Value injected by FXMLLoader

    /**
     * FXML value for the contact person combo box
     */
    @FXML // fx:id="cbContactPerson"
    private ComboBox<String> cbContactPerson; // Value injected by FXMLLoader

    /**
     * FXML value for the title form label
     */
    @FXML // fx:id="lbTitle"
    private Label lbTitle; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment title text field
     */
    @FXML // fx:id="tfTitle"
    private TextField tfTitle; // Value injected by FXMLLoader

    /**
     * FXML value for the type form label
     */
    @FXML // fx:id="lbType"
    private Label lbType; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment type text field
     */
    @FXML // fx:id="tfType"
    private TextField tfType; // Value injected by FXMLLoader

    /**
     * FXML value for the location form label
     */
    @FXML // fx:id="lbLocation"
    private Label lbLocation; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment location text field
     */
    @FXML // fx:id="tfLocation"
    private TextField tfLocation; // Value injected by FXMLLoader

    /**
     * FXML value for the customer ID form label
     */
    @FXML // fx:id="lbCustomerID"
    private Label lbCustomerID; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment customer ID text field
     */
    @FXML // fx:id="tfCustomerID"
    private TextField tfCustomerID; // Value injected by FXMLLoader

    /**
     * FXML value for the start form label
     */
    @FXML // fx:id="lbStart"
    private Label lbStart; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment start time text field
     */
    @FXML // fx:id="tfStartDT"
    private TextField tfStartDT; // Value injected by FXMLLoader

    /**
     * FXML value for the end form label
     */
    @FXML // fx:id="lbEnd"
    private Label lbEnd; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment end time text field
     */
    @FXML // fx:id="tfEndDT"
    private TextField tfEndDT; // Value injected by FXMLLoader

    /**
     * FXML value for the user ID form label
     */
    @FXML // fx:id="lbUserID"
    private Label lbUserID; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment user ID text field
     */
    @FXML // fx:id="tfUserID"
    private TextField tfUserID; // Value injected by FXMLLoader

    /**
     * FXML value for the update appointments button
     */
    @FXML // fx:id="btnUpdateAppointment"
    private Button btnUpdateAppointment; // Value injected by FXMLLoader

    /**
     * FXML value for the add appointments button
     */
    @FXML // fx:id="btnAddAppointment"
    private Button btnAddAppointment; // Value injected by FXMLLoader

    /**
     * FXML value for the clear form button
     */
    @FXML // fx:id="btnClearForm"
    private Button btnClearForm; // Value injected by FXMLLoader

    /**
     * FXML value for the form error message label
     */
    @FXML // fx:id="lbFormErrorMessage"
    private Label lbFormErrorMessage; // Value injected by FXMLLoader

    /**
     * FXML value for the description form label
     */
    @FXML // fx:id="lbDescription"
    private Label lbDescription; // Value injected by FXMLLoader

    /**
     * FXML value for the appointment description text field
     */
    @FXML // fx:id="tfDescription"
    private TextField tfDescription; // Value injected by FXMLLoader


    /**
     * Hash map storage for form data to map proper text field with proper value
     */
    private final HashMap<String, String> formData = new HashMap<>();


    /**
     * Current calendar instance of the current logged in user
     */
    public Calendar calendar = Calendar.getInstance();

    /**
     * Add data from the form to the storage hashmap, mapping values to proper keys
     */
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

    /**
     * get index of selected item of the tableview once clicked
     * @param ID int value of appointment ID
     * @param toWhichCollection string value representing how the appointments table view is currently filtered
     * @return int value of the current index within table view
     */
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

    /**
     * Get all contact names and put them in a list
     * @return list of all contact names only
     */
    private ObservableList<String> getContactNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Contacts contact: AppHelper.contacts) {
            names.add(contact.getContactName());
        }
        return names;
    }

    /**
     * Once an appointment is clicked, add its data to the form for editing
     */
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

    /**
     * Clear all of the form data storage and text fields
     */
    private void clearFormData() {
        formData.clear();
        // set the form buttons to default disability position
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

    /**
     * Get the next available number for the appointment ID field
     * @return int value of the next available number for the appointment ID field
     */
    private int autoNextGenApptID() {
        TreeSet<Integer> temp = new TreeSet<>();
        int curr = 1;

        for (Appointments appt : AppHelper.appointments) {
            temp.add(appt.getAppointmentID());
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

    /**
     * Filter appointments by current Week
     */
    private void filterByWeek() {
        AppHelper.apptsByWeek.clear();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);

        for (Appointments appt : AppHelper.appointments) {
            if (appt.getStart().get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                if (appt.getStart().get(Calendar.WEEK_OF_YEAR) == currentWeek) {
                    AppHelper.apptsByWeek.add(appt);
                }
            }
        }
    }

    /**
     * Filter appointments by current Month
     */
    private void filterByMonth() {
        AppHelper.apptsByMonth.clear();
        int currentMonth = calendar.get(Calendar.MONTH);

        for (Appointments appt : AppHelper.appointments) {
            if (appt.getStart().get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                if (appt.getStart().get(Calendar.MONTH) == currentMonth) {
                    AppHelper.apptsByMonth.add(appt);
                }
            }
        }
    }


    /**
     * FXML Override; This method is called by the FXMLLoader when initialization is complete. Handles
     * all scene control action events and form data control
     *
     * {@link #btnClearForm} The lambda expression btnClearForm.setOnAction(... was used because it it only needed to be called during
     * after an action had taken place and had a very simple job to do. call 1 method to clear the form and change
     * a couple of button options. Makes for very concise and readable code.
     *
     * {@link #btnReports} The lambda expression btnReports.setOnAction(... was used because its purpose was to only change the scene
     * once clicked, and that is functional and atomic. Makes it very concise and readable.
     *
     * @param url URL location of the FXML file that was given to the FXMLLoader
     * @param resourceBundle ResourceBundle that was given to the FXMLLoader
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        /**
         * Set display text in proper language
         */
        lbCustomers.setText(AppHelper.localeRB.getString("lbCustomers"));
        /**
         * Set display text in proper language
         */
        lbSignOut.setText(AppHelper.localeRB.getString("lbSignOut"));
        /**
         * Set display text in proper language
         */
        rbApptByWeek.setText(AppHelper.localeRB.getString("rbApptByWeek"));
        /**
         * Set display text in proper language
         */
        rbApptByMonth.setText(AppHelper.localeRB.getString("rbApptByMonth"));
        /**
         * Set display text in proper language
         */
        tcID.setText(AppHelper.localeRB.getString("ID"));
        /**
         * Set display text in proper language
         */
        tcTitle.setText(AppHelper.localeRB.getString("Title"));
        /**
         * Set display text in proper language
         */
        tcDescription.setText(AppHelper.localeRB.getString("Description"));
        /**
         * Set display text in proper language
         */
        tcLocation.setText(AppHelper.localeRB.getString("Location"));
        /**
         * Set display text in proper language
         */
        tcType.setText(AppHelper.localeRB.getString("Type"));
        /**
         * Set display text in proper language
         */
        tcStartDT.setText(AppHelper.localeRB.getString("StartDT"));
        /**
         * Set display text in proper language
         */
        tcEndDT.setText(AppHelper.localeRB.getString("EndDT"));
        /**
         * Set display text in proper language
         */
        tcContact.setText(AppHelper.localeRB.getString("tcContact"));
        /**
         * Set display text in proper language
         */
        tcCustomerID.setText(AppHelper.localeRB.getString("CustomerID"));
        /**
         * Set display text in proper language
         */
        lbApptID.setText(AppHelper.localeRB.getString("lbApptID"));
        /**
         * Set display text in proper language
         */
        lbCustomerID.setText(AppHelper.localeRB.getString("CustomerID"));
        /**
         * Set display text in proper language
         */
        lbTitle.setText(AppHelper.localeRB.getString("Title"));
        /**
         * Set display text in proper language
         */
        lbContact.setText(AppHelper.localeRB.getString("lbContact"));
        /**
         * Set display text in proper language
         */
        lbLocation.setText(AppHelper.localeRB.getString("Location"));
        /**
         * Set display text in proper language
         */
        lbType.setText(AppHelper.localeRB.getString("Type"));
        /**
         * Set display text in proper language
         */
        lbStart.setText(AppHelper.localeRB.getString("Start"));
        /**
         * Set display text in proper language
         */
        lbEnd.setText(AppHelper.localeRB.getString("End"));
        /**
         * Set display text in proper language
         */
        lbDescription.setText(AppHelper.localeRB.getString("Description"));
        /**
         * Set display text in proper language
         */
        lbUserID.setText(AppHelper.localeRB.getString("UserID"));
        /**
         * Set display text in proper language
         */
        btnAddAppointment.setText(AppHelper.localeRB.getString("btnAddAppt"));
        /**
         * Set display text in proper language
         */
        btnClearForm.setText(AppHelper.localeRB.getString("btnClear"));
        /**
         * Set display text in proper language
         */
        btnUpdateAppointment.setText(AppHelper.localeRB.getString("btnUpdateAppt"));
        /**
         * Set display text in proper language
         */
        btnDeleteAppointment.setText(AppHelper.localeRB.getString("btnDeleteAppt"));

        // Clear form data, just in case
        formData.clear();

        // Get all initial data from data that will be used in this screen
//        DBHelper.getAllAppointments();
        filterByWeek();
        filterByMonth();

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

        /**
         * Lamda Expression
         * Set the appointments table with correct data depending on filter order of months
         */
        rbApptByMonth.setOnAction(event -> {
            tvAppointments.setItems(AppHelper.apptsByMonth);
            tvAppointments.refresh();
        });

        /**
         * Lamda Expression
         * Set the appointments table with correct data depending on sort order of weeks
         */
        rbApptByWeek.setOnAction(event -> {
            tvAppointments.setItems(AppHelper.apptsByWeek);
            tvAppointments.refresh();
        });


        /**
         * Lamda Expression
         * If the appointments table is clicked add the data to the form for editing and set form buttons and remove error messages
         */
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
            StringBuilder status = new StringBuilder();
            status.append(AppHelper.checkFormCorrectness(formData));
            if (status.toString().equals("correct")) {
                if (AppHelper.overlapAppt(formData.get("start"), formData.get("end"))) {
                    status.replace(0, status.length(), AppHelper.localeRB.getString("erOverlap"));
                    lbFormErrorMessage.setText(String.valueOf(status));
                    lbFormErrorMessage.setVisible(true);
                } else {
                    try {
                        AppHelper.startCalendar = AppHelper.createApptFromFormData(formData).getStart();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        AppHelper.endCalendar = AppHelper.createApptFromFormData(formData).getEnd();
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
                    filterByWeek();
                    filterByWeek();
                    tvAppointments.refresh();
                    clearFormData();
                    tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));
                }
            } else {
                // if errors occur describe them
                lbFormErrorMessage.setText(String.valueOf(status));
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
                    filterByWeek();
                    filterByWeek();
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
            StringBuilder status = new StringBuilder();
            status.append(AppHelper.checkFormCorrectness(formData));
            if (String.valueOf(status).equals("correct")) {
                if (AppHelper.overlapAppt(formData.get("start"), formData.get("end"))) {
                    status.replace(0, status.length(), AppHelper.localeRB.getString("erOverlap"));
                    lbFormErrorMessage.setText(status.toString());
                    lbFormErrorMessage.setVisible(true);
                } else {
                    try {
                        DBHelper.updateAppointment(AppHelper.createApptFromFormData(formData));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    if (rbApptByWeek.isSelected()) {
                        Appointments selAppt = tvAppointments.getSelectionModel().getSelectedItem();
                        try {
                            AppHelper.apptsByWeek.set(tvAppointments.getSelectionModel().getSelectedIndex(), AppHelper.createApptFromFormData(formData));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
//                        try {
//                            AppHelper.apptsByMonth.set(getIndex(selAppt.getAppointmentID(), "month"), AppHelper.createApptFromFormData(formData));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            AppHelper.appointments.set(getIndex(selAppt.getAppointmentID(), "all"), AppHelper.createApptFromFormData(formData));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
                    } else if (rbApptByMonth.isSelected()) {
                        Appointments selAppt = tvAppointments.getSelectionModel().getSelectedItem();
//                        try {
//                            AppHelper.apptsByWeek.set(getIndex(selAppt.getAppointmentID(), "week"), AppHelper.createApptFromFormData(formData));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
                        try {
                            AppHelper.apptsByMonth.set(tvAppointments.getSelectionModel().getSelectedIndex(), AppHelper.createApptFromFormData(formData));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
//                        try {
//                            AppHelper.appointments.set(getIndex(selAppt.getAppointmentID(), "all"), AppHelper.createApptFromFormData(formData));
//                        } catch (ParseException e) {
//                            e.printStackTrace();
//                        }
                    }
                    DBHelper.getAllAppointments();
                    filterByWeek();
                    filterByWeek();
                    tvAppointments.refresh();
                    clearFormData();
                    tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));
                }
            } else {
                // 2020-12-21 09:30:00 AM
                String statusStr = String.valueOf(status);
                lbFormErrorMessage.setText(statusStr);
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
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
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

    /**
     * FXML This method is called by the FXMLLoader when initialization is complete
     */
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
