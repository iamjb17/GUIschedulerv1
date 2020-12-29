/**
 * @author Jessie Burton
 */
package Scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class that will house the entry point into the application
 */
public class Main extends Application {

    /**
     * Method used to get and show the first fxml file
     * @param primaryStage the initial stage to set the scene onto
     * @throws Exception thrown if unable to find fxml file
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Resources/SignInForm.fxml"));
        primaryStage.setTitle("Appointments Scheduler");
        primaryStage.setScene(new Scene(root, 350, 400));
        primaryStage.show();
    }

    /**
     * Java application entry point
     * @param args array of strings arguments to launch application with
     */
    public static void main(String[] args) {
        launch(args);
    }
}

/*
TODO ERRORS
A1 X The application includes a log-in form and error control message in English. The functionality to automatically
translate the log-in form and error control message into the French language based on the user's location was not observed.

A2 X  The application includes functionality to add and delete customer records. The functionality to update a customer's
country and first-level division was not observed. -- Allowed all countries and states to be visible and not filtered

A3a X The application includes functionality to add and delete appointments. When an appointment is opened for
modifications, the start date and time display in the end date and time field. The functionality to update appointments
will be better assessed when this is complete.

A3b X The application includes a weekly and monthly calendar view. The views did not appear to filter out the
appointments for the current week and the current month. The appointment end time displays the same date and time as
the appointment start time.

A3c  This aspect will be better assessed when aspects 'A3a' and 'A3b' are complete. (Timezones)

A3d X The application includes the validation check for scheduling appointments outside of business hours. The invalid
username and password validation check will be better assessed when aspect 'A1' is complete. The check to prevent
scheduling overlapping appointments was not observed when adding and updating appointments.

A3f X The application includes functionality to generate three reports. The appointment start date and time displays in
the end date and time field in the consultant schedule report.

D ?(how to document lambda expression) The submission includes an index.html with the supporting files. Comments were observed in the code but not in the
Javadoc comments. The comments justifying the use of each lambda expression must be in the method header comments where
they occur in order to export correctly to Javadocs.

F  This aspect will be better assessed when all aspects are complete. (Professional Communication)
 */

/**
 * View of the DB structure
 * appointments TABLE_NAME,  Appointment_ID,Title,Description,Location,Type,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * contacts TABLE_NAME,  Contact_ID,Contact_Name,Email GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * countries TABLE_NAME,  Country_ID,Country,Create_Date,Created_By,Last_Update,Last_Updated_By GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * customers TABLE_NAME,  Customer_ID,Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * first_level_divisions TABLE_NAME,  Division_ID,Division,Create_Date,Created_By,Last_Update,Last_Updated_By,COUNTRY_ID GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * users TABLE_NAME,  User_ID,User_Name,Password,Create_Date,Created_By,Last_Update,Last_Updated_By GROUP_CONCAT(column_name ORDER BY ordinal_position)
 */