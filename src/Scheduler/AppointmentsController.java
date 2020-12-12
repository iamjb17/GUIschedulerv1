/**
 * @author Jessie Burton
 */
package Scheduler;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    @FXML // fx:id="btnSignOut"
    private Button btnSignOut; // Value injected by FXMLLoader

    @FXML // fx:id="btnCustomers"
    private Button btnCustomers; // Value injected by FXMLLoader

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
    private TableColumn<Appointments, Timestamp> tcStartDT; // Value injected by FXMLLoader

    @FXML // fx:id="tcEndDT"
    private TableColumn<Appointments, Timestamp> tcEndDT; // Value injected by FXMLLoader

    @FXML // fx:id="tcCustomerID"
    private TableColumn<Appointments, Integer> tcCustomerID; // Value injected by FXMLLoader

    @FXML // fx:id="btnDeleteAppointment"
    private Button btnDeleteAppointment; // Value injected by FXMLLoader

    @FXML // fx:id="lbTableErrorMessage"
    private Label lbTableErrorMessage; // Value injected by FXMLLoader

    @FXML // fx:id="tfAppointmentID"
    private TextField tfAppointmentID; // Value injected by FXMLLoader

    @FXML // fx:id="cbContactPerson"
    private ComboBox<String> cbContactPerson; // Value injected by FXMLLoader

    @FXML // fx:id="tfTitle"
    private TextField tfTitle; // Value injected by FXMLLoader

    @FXML // fx:id="tfType"
    private TextField tfType; // Value injected by FXMLLoader

    @FXML // fx:id="tfLocation"
    private TextField tfLocation; // Value injected by FXMLLoader

    @FXML // fx:id="tfCustomerID"
    private TextField tfCustomerID; // Value injected by FXMLLoader

    @FXML // fx:id="tfStartDT"
    private TextField tfStartDT; // Value injected by FXMLLoader

    @FXML // fx:id="tfEndDT"
    private TextField tfEndDT; // Value injected by FXMLLoader

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

    @FXML // fx:id="tfDescription"
    private TextField tfDescription; // Value injected by FXMLLoader

    private final HashMap<String, String> formData = new HashMap<>();

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

    // get index of selected item
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

    // Sort appointments container
    private ObservableList<Appointments> sortContainerByWeek() {
        // TODO you can sort week and month in sql. take appt out of the big method and make one for here
        AppHelper.appointments.sort(Comparator.comparing(Appointments::getTitle));
        return null;
    }

    private String getContactName(int ID) {
        for (Contacts contacts : AppHelper.contacts) {
            if (contacts.getContactID() == ID) {
                return contacts.getContactName();
            }
            return "";
        }
        return "";
    }

    private ObservableList<String> getContactNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Contacts contact: AppHelper.contacts) {
            names.add(contact.getContactName());
        }
        return names;
    }

    private void addDataToForm() {
        tfAppointmentID.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getAppointmentID()));
        tfTitle.setText(tvAppointments.getSelectionModel().getSelectedItem().getTitle());
        tfStartDT.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getStart()));
        tfEndDT.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getEnd()));
        tfType.setText(tvAppointments.getSelectionModel().getSelectedItem().getType());
        tfLocation.setText(tvAppointments.getSelectionModel().getSelectedItem().getLocation());
        tfUserID.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getUserID()));
        tfCustomerID.setText(String.valueOf(tvAppointments.getSelectionModel().getSelectedItem().getCustomerID()));
        cbContactPerson.getSelectionModel().select(tvAppointments.getSelectionModel().getSelectedItem().getContactName());

        System.out.println(tvAppointments.getSelectionModel().getSelectedItem().getDescription());
        tfDescription.setText(tvAppointments.getSelectionModel().getSelectedItem().getDescription());

//        switch (whichSort) {
//            case "week":
//                tfAppointmentID.setText(String.valueOf(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getAppointmentID()));
//                tfTitle.setText(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getTitle());
//                tfStartDT.setText(String.valueOf(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getStart()));
//                tfEndDT.setText(String.valueOf(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getEnd()));
//                tfType.setText(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getType());
//                tfLocation.setText(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getLocation());
//                tfUserID.setText(String.valueOf(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getUserID()));
//                tfCustomerID.setText(String.valueOf(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getCustomerID()));
//                cbContactPerson.getSelectionModel().select(getContactName(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getContactID()));
//                break;
//            case "month":
//                tfAppointmentID.setText(String.valueOf(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getAppointmentID()));
//                tfTitle.setText(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getTitle());
//                tfStartDT.setText(String.valueOf(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getStart()));
//                tfEndDT.setText(String.valueOf(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getEnd()));
//                tfType.setText(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getType());
//                tfLocation.setText(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getLocation());
//                tfUserID.setText(String.valueOf(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getUserID()));
//                tfCustomerID.setText(String.valueOf(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getCustomerID()));
//                cbContactPerson.getSelectionModel().select(getContactName(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getContactID()));
//                break;
//        }
    }

    private void clearFormData() {
        formData.clear();
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
        System.out.println(temp);
        return curr;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        formData.clear();
        // TODO rework some of the this classes code. fix desc, simplify the appointments
        DBHelper.getAllAppointments();

        // set up table view by week
        tcID.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
        tcDescription.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
        tcLocation.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
        tcContact.setCellValueFactory(new PropertyValueFactory<Appointments, String>("contactName"));
        tcType.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
        tcStartDT.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));
        tcEndDT.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("end"));
        tcCustomerID.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));

        // setup table view by month
//        tcIDByMonth.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("appointmentID"));
//        tcTitleByMonth.setCellValueFactory(new PropertyValueFactory<Appointments, String>("title"));
//        tcDescriptionByMonth.setCellValueFactory(new PropertyValueFactory<Appointments, String>("description"));
//        tcLocationByMonth.setCellValueFactory(new PropertyValueFactory<Appointments, String>("location"));
//        tcContactByMonth.setCellValueFactory(new PropertyValueFactory<Appointments, String>("contactID"));
//        tcTypeByMonth.setCellValueFactory(new PropertyValueFactory<Appointments, String>("type"));
//        tcStartDTByMonth.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("start"));
//        tcEndDTByMonth.setCellValueFactory(new PropertyValueFactory<Appointments, Timestamp>("end"));
//        tcCustomerIDByMonth.setCellValueFactory(new PropertyValueFactory<Appointments, Integer>("customerID"));

//        if (!AppHelper.apptsByWeek.isEmpty()) {
//            if (rbApptByMonth.isSelected()) {
//                tvAppointments.setItems(AppHelper.apptsByMonth);
//            } else {
//                tvAppointments.setItems(AppHelper.apptsByWeek);
//            }
//        }

        if (rbApptByMonth.isSelected()) {
            if (!AppHelper.apptsByMonth.isEmpty()) {
                tvAppointments.setItems(AppHelper.apptsByMonth);
            }
        } else if (rbApptByWeek.isSelected()){
            if (!AppHelper.apptsByWeek.isEmpty()) {
                tvAppointments.setItems(AppHelper.apptsByWeek);
            }
        }

        rbApptByMonth.setOnAction(event -> {
            tvAppointments.setItems(AppHelper.apptsByMonth);
            tvAppointments.refresh();
        });

        rbApptByWeek.setOnAction(event -> {
            tvAppointments.setItems(AppHelper.apptsByWeek);
            tvAppointments.refresh();
        });

        tvAppointments.setOnMouseClicked(event -> {
            addDataToForm();
            btnAddAppointment.setDisable(true);
            btnUpdateAppointment.setDisable(false);
            lbFormErrorMessage.setVisible(false);
            lbTableErrorMessage.setVisible(false);
        });
//        tvAppointmentsByWeek.setOnMouseClicked(event -> {
//            addDataToForm("week");
//            btnAddAppointment.setDisable(true);
//            btnUpdateAppointment.setDisable(false);
//            lbFormErrorMessage.setVisible(false);
//            lbTableErrorMessage.setVisible(false);
//        });

        cbContactPerson.setItems(getContactNames());
        tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));

        btnAddAppointment.setOnAction(event -> {
            addFormDataToMap();
            String status = AppHelper.checkFormCorrectness(formData);
            if (status.equals("correct")) {
                DBHelper.addAppointment(AppHelper.createApptFromFormData(formData));
                AppHelper.appointments.add(AppHelper.createApptFromFormData(formData));
                AppHelper.apptsByMonth.add(AppHelper.createApptFromFormData(formData));
                AppHelper.apptsByWeek.add(AppHelper.createApptFromFormData(formData));
                DBHelper.getAllAppointments();
                tvAppointments.refresh();
                clearFormData();
                tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));
            } else {
                lbFormErrorMessage.setText(status);
                lbFormErrorMessage.setVisible(true);
            }
            tvAppointments.refresh();
        });

        btnClearForm.setOnAction(event -> {
            clearFormData();
            btnUpdateAppointment.setDisable(true);
            btnAddAppointment.setDisable(false);
        });

        // TODO rework this method put the tabs at the top of the if it think
        btnDeleteAppointment.setOnAction(event -> {
            if (!tvAppointments.getSelectionModel().isEmpty()) {
                boolean isDeleted = DBHelper.deleteAppointment(tvAppointments.getSelectionModel().getSelectedItem().getAppointmentID());
                if (isDeleted) {
                    lbTableErrorMessage.setText("Appointment Deleted Successfully");
                    lbTableErrorMessage.setVisible(true);
                    AppHelper.apptsByMonth.remove(tvAppointments.getSelectionModel().getSelectedItem());
                    AppHelper.apptsByWeek.remove(tvAppointments.getSelectionModel().getSelectedItem());
                    AppHelper.appointments.remove(tvAppointments.getSelectionModel().getSelectedItem());
                    DBHelper.getAllAppointments();
                    tvAppointments.refresh();
                    clearFormData();
                    tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));
                } else if(!isDeleted) {
                    lbTableErrorMessage.setText("Unable To Delete Appointment");
                    lbTableErrorMessage.setVisible(true);
                }
            }
//            if(rbApptByMonth.isSelected()) {
//                if (!tvAppointments.getSelectionModel().isEmpty()) {
//                    boolean isDeleted = DBHelper.deleteAppointment(tvAppointments.getSelectionModel().getSelectedItem().getAppointmentID());
//                    if (isDeleted) {
//                        AppHelper.apptsByMonth.remove(tvAppointments.getSelectionModel().getSelectedItem());
//                        AppHelper.apptsByWeek.remove(tvAppointments.getSelectionModel().getSelectedItem());
//                        AppHelper.appointments.remove(tvAppointments.getSelectionModel().getSelectedItem());
//                        clearFormData();
//                        lbTableErrorMessage.setText("Appointment Deleted Successfully");
//                        lbTableErrorMessage.setVisible(true);
//                    } else {
//                        lbTableErrorMessage.setText("Unable To Delete Appointment");
//                        lbTableErrorMessage.setVisible(true);
//                    }
//                } else {
//                    if (!tvAppointments.getSelectionModel().isEmpty()) {
//                        if (DBHelper.deleteAppointment(tvAppointments.getSelectionModel().getSelectedItem().getAppointmentID())) {
//                            AppHelper.apptsByWeek.remove(tvAppointments.getSelectionModel().getSelectedItem());
//                            clearFormData();
//                            lbTableErrorMessage.setText("Appointment Deleted Successfully");
//                            lbTableErrorMessage.setVisible(true);
//                        } else {
//                            lbTableErrorMessage.setText("Unable To Delete Appointment");
//                            lbTableErrorMessage.setVisible(true);
//                        }
//                    }
//                }
//            }
//            lbTableErrorMessage.setText("Unable To Delete Appointment");
//            lbTableErrorMessage.setVisible(true);

//            if(!tvAppointmentsByWeek.getSelectionModel().isEmpty()) {
//                if(tabByMonth.isSelected()) {
//                    if(DBHelper.deleteAppointment(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getAppointmentID())) {
//                        lbTableErrorMessage.setText("Appointment Deleted Successfully");
//                    } else {
//                        lbTableErrorMessage.setText("Unable To Delete Appointment");
//                        lbTableErrorMessage.setVisible(true);
//                    }
//                } else if (tabByWeek.isSelected()) {
//                    if(DBHelper.deleteAppointment(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getAppointmentID())) {
//                        lbTableErrorMessage.setText("Appointment Deleted Successfully");
//                        lbTableErrorMessage.setVisible(true);
//                    } else {
//                        lbTableErrorMessage.setText("Unable To Delete Appointment");
//                        lbTableErrorMessage.setVisible(true);
//                    }
//                } else{
//                    lbTableErrorMessage.setText("No Appointment Selected");
//                    lbTableErrorMessage.setVisible(true);
//                }
//            } else {
//                if(!tvAppointmentsByMonth.getSelectionModel().isEmpty()) {
//                    if(tabByMonth.isSelected()){
//                        if(DBHelper.deleteAppointment(tvAppointmentsByMonth.getSelectionModel().getSelectedItem().getAppointmentID())) {
//                            lbTableErrorMessage.setText("Appointment Deleted Successfully");
//                        } else {
//                            lbTableErrorMessage.setText("Unable To Delete Appointment");
//                            lbTableErrorMessage.setVisible(true);
//                        }
//                    } else if (tabByWeek.isSelected()) {
//                        if(DBHelper.deleteAppointment(tvAppointmentsByWeek.getSelectionModel().getSelectedItem().getAppointmentID())) {
//                            lbTableErrorMessage.setText("Appointment Deleted Successfully");
//                            lbTableErrorMessage.setVisible(true);
//                        } else {
//                            lbTableErrorMessage.setText("Unable To Delete Appointment");
//                            lbTableErrorMessage.setVisible(true);
//                        }
//                    } else{
//                        lbTableErrorMessage.setText("No Appointment Selected");
//                        lbTableErrorMessage.setVisible(true);
//                    }
//                } else {
//                    lbTableErrorMessage.setText("No Appointment Selected");
//                    lbTableErrorMessage.setVisible(true);
//                }
//            }
        });

        btnUpdateAppointment.setOnAction(event -> {
            addFormDataToMap();
            String status = AppHelper.checkFormCorrectness(formData);
            if (status.equals("correct")) {
                DBHelper.updateAppointment(AppHelper.createApptFromFormData(formData));
                if (rbApptByWeek.isSelected()){
                    Appointments selAppt = tvAppointments.getSelectionModel().getSelectedItem();
                    AppHelper.apptsByWeek.set(tvAppointments.getSelectionModel().getSelectedIndex(), AppHelper.createApptFromFormData(formData));
                    AppHelper.apptsByMonth.set(getIndex(selAppt.getAppointmentID(), "month"), AppHelper.createApptFromFormData(formData));
                    AppHelper.appointments.set(getIndex(selAppt.getAppointmentID(), "all"), AppHelper.createApptFromFormData(formData));
                } else if (rbApptByMonth.isSelected()){
                    Appointments selAppt = tvAppointments.getSelectionModel().getSelectedItem();
                    AppHelper.apptsByWeek.set(getIndex(selAppt.getAppointmentID(), "week"), AppHelper.createApptFromFormData(formData));
                    AppHelper.apptsByMonth.set(tvAppointments.getSelectionModel().getSelectedIndex(), AppHelper.createApptFromFormData(formData));
                    AppHelper.appointments.set(getIndex(selAppt.getAppointmentID(), "all"), AppHelper.createApptFromFormData(formData));
                }
//                AppHelper.apptsByWeek.set(tvAppointments.getSelectionModel().getFocusedIndex(), AppHelper.createApptFromFormData(formData));
//                AppHelper.apptsByMonth.set(Integer.parseInt(formData.get("appointment ID"))-1, AppHelper.createApptFromFormData(formData));
//                AppHelper.appointments.set(Integer.parseInt(formData.get("appointment ID"))-1, AppHelper.createApptFromFormData(formData));
                tvAppointments.refresh();
                clearFormData();
                tfAppointmentID.setText(String.valueOf(autoNextGenApptID()));
            } else {
                lbFormErrorMessage.setText(status);
                lbFormErrorMessage.setVisible(true);
            }
        });

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
        assert btnSignOut != null : "fx:id=\"btnSignOut\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnCustomers != null : "fx:id=\"btnCustomers\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
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
        assert tfAppointmentID != null : "fx:id=\"tfAppointmentID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert cbContactPerson != null : "fx:id=\"cbContactPerson\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfTitle != null : "fx:id=\"tfTitle\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfType != null : "fx:id=\"tfType\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfLocation != null : "fx:id=\"tfLocation\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfCustomerID != null : "fx:id=\"tfCustomerID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfStartDT != null : "fx:id=\"tfStartDT\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfEndDT != null : "fx:id=\"tfEndDT\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfUserID != null : "fx:id=\"tfUserID\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnUpdateAppointment != null : "fx:id=\"btnUpdateAppointment\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnAddAppointment != null : "fx:id=\"btnAddAppointment\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert btnClearForm != null : "fx:id=\"btnClearForm\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert lbFormErrorMessage != null : "fx:id=\"lbFormErrorMessage\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";
        assert tfDescription != null : "fx:id=\"tfDescription\" was not injected: check your FXML file 'AppointmentsScene.fxml'.";

    }

}
