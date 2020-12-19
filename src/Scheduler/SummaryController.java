package Scheduler;

/**
 * @author Jessie Burton
 */


import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

// Class for handling the summary scene
public class SummaryController implements Initializable {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="lbSummaryReport"
    private Label lbSummaryReport; // Value injected by FXMLLoader

    @FXML // fx:id="lbCustomerApptRt1"
    private Label lbCustomerApptRt1; // Value injected by FXMLLoader

    @FXML // fx:id="lbCustomerApptRTLB"
    private Label lbCustomerApptRTLB; // Value injected by FXMLLoader

    @FXML // fx:id="lbCustomerApptRt"
    private Label lbCustomerApptRt; // Value injected by FXMLLoader

    @FXML // fx:id="lbContactApptRtLB"
    private Label lbContactApptRtLB; // Value injected by FXMLLoader

    @FXML // fx:id="lbContactApptRt"
    private Label lbContactApptRt; // Value injected by FXMLLoader

    @FXML // fx:id="lbCustomerByLocationLB"
    private Label lbCustomerByLocationLB; // Value injected by FXMLLoader

    @FXML // fx:id="lbCustomerByLocation"
    private Label lbCustomerByLocation; // Value injected by FXMLLoader

    @FXML // fx:id="btBackRt"
    private Button btBackRt; // Value injected by FXMLLoader

    // Storage variables for total appointments by month and type
    private final HashMap<String, Integer> totalByType = new HashMap<>();
    private final HashMap<String, Integer> totalByMonth = new HashMap<>();

    // Method for getting total # of appts by type
    private void getTotalByType() {
        for (Appointments appts: AppHelper.appointments) {
            int total = 1;
            for (int i = 1; i < AppHelper.appointments.size(); i++) {
                if (appts.getType() == AppHelper.appointments.get(i).getType()) {
                    total +=1;
                }
            }
            totalByType.put(appts.getType(), total);
        }
    }

    // Method for getting total # of appts by month
    private void getTotalByMonth() {
        for (Appointments appts: AppHelper.appointments) {
            int total = 1;
            String monthName = new SimpleDateFormat("MMMM").format(appts.getStart().getTime());
            for (int i = 1; i < AppHelper.appointments.size(); i++) {
                if (appts.getStart().get(Calendar.MONTH) == AppHelper.appointments.get(i).getStart().get(Calendar.MONTH)) {
                    total +=1;
                }
            }
            totalByMonth.put(monthName, total);
        }
    }

    // Method for returning all contact schedules
    private String getContactSched() {
        DBHelper.getCustomerSched();
        StringBuilder fullString = new StringBuilder();
        for (Contacts contact : AppHelper.contacts) {
            fullString.append(contact.getContactName());
            StringBuilder strBuilder = new StringBuilder();
            for (ObservableList obs: AppHelper.contactSched) {
                if ((int)obs.get(7) == contact.getContactID()) {
                    strBuilder.append(System.lineSeparator() + obs.get(0) + ": (" + AppHelper.localeRB.getString("lbApptID") + ")    " );
                    strBuilder.append(obs.get(1) + " (" + AppHelper.localeRB.getString("Title") + ")    ");
                    strBuilder.append(obs.get(2) + " "+ obs.get(3) + " (" + AppHelper.localeRB.getString("Type") +" & " + AppHelper.localeRB.getString("Description") + ")   ");
                    strBuilder.append(obs.get(4) + " (" + AppHelper.localeRB.getString("StartDT") + ")   ");
                    strBuilder.append(obs.get(5) + " (" + AppHelper.localeRB.getString("EndDT") + ")     ");
                    strBuilder.append(obs.get(6) + " (" + AppHelper.localeRB.getString("CustomerID") + ")    ");
                }
            }

            fullString.append(strBuilder + System.lineSeparator() + System.lineSeparator());
        }
        return fullString.toString();
    }

    // Method for getting total number customers by location
    private void getTotalCustomersByLocation() {
        for (Customers customer : AppHelper.customers) {

            if(AppHelper.numCustomerByLocation.containsKey(customer.getCountry())) {
                int currNumb = AppHelper.numCustomerByLocation.get(customer.getCountry());
                AppHelper.numCustomerByLocation.replace(customer.getCountry(), currNumb+=1);
            } else {
                AppHelper.numCustomerByLocation.put(customer.getCountry(), 1);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Set the text for Internationalization
        lbSummaryReport.setText(AppHelper.localeRB.getString("SummaryReport"));
        lbCustomerApptRTLB.setText(AppHelper.localeRB.getString("TotalApptByTM"));
        lbContactApptRtLB.setText(AppHelper.localeRB.getString("ContactSched"));
        lbCustomerByLocationLB.setText(AppHelper.localeRB.getString("CustomersByLocation"));
        btBackRt.setText(AppHelper.localeRB.getString("GoBack"));

        // Clear storage variables before use
        totalByType.clear();
        totalByMonth.clear();

        // Put data in total number of appointments by type and month storage variables
        getTotalByType();
        getTotalByMonth();

        // Show total # of appt by type Show total # of appts by month
        lbCustomerApptRt.setText(totalByType.toString() + System.lineSeparator());
        lbCustomerApptRt1.setText(totalByMonth.toString());

        // Show Contact Schedule
        lbContactApptRt.setText(getContactSched());

        // Show number of customers by location
        AppHelper.numCustomerByLocation.clear();
        getTotalCustomersByLocation();

        // set the number of customer by country label
        lbCustomerByLocation.setText(AppHelper.numCustomerByLocation.toString());

        // Go back to the last scene according to backWhere variable
        btBackRt.setOnAction(event -> {
            Parent parent = null;
            try {
                if (AppHelper.backWhere == "customer") {
                    parent = FXMLLoader.load(getClass().getResource("../Resources/CustomerScene.fxml"));
                } else {
                    parent = FXMLLoader.load(getClass().getResource("../Resources/AppointmentsScene.fxml"));
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            Stage primaryStage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            primaryStage.setScene(new Scene(parent));
        });

    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert lbSummaryReport != null : "fx:id=\"lbSummaryReport\" was not injected: check your FXML file 'SummaryReport.fxml'.";
        assert lbCustomerApptRTLB != null : "fx:id=\"lbCustomerApptRTLB\" was not injected: check your FXML file 'SummaryReport.fxml'.";
        assert lbCustomerApptRt != null : "fx:id=\"lbCustomerApptRt\" was not injected: check your FXML file 'SummaryReport.fxml'.";
        assert lbCustomerApptRt1 != null : "fx:id=\"lbCustomerApptRt1\" was not injected: check your FXML file 'SummaryReport.fxml'.";
        assert lbContactApptRtLB != null : "fx:id=\"lbContactApptRtLB\" was not injected: check your FXML file 'SummaryReport.fxml'.";
        assert lbContactApptRt != null : "fx:id=\"lbContactApptRt\" was not injected: check your FXML file 'SummaryReport.fxml'.";
        assert lbCustomerByLocationLB != null : "fx:id=\"lbCustomerByLocationLB\" was not injected: check your FXML file 'SummaryReport.fxml'.";
        assert lbCustomerByLocation != null : "fx:id=\"lbCustomerByLocation\" was not injected: check your FXML file 'SummaryReport.fxml'.";
        assert btBackRt != null : "fx:id=\"btBackRt\" was not injected: check your FXML file 'SummaryReport.fxml'.";

    }
}
