
package Scheduler;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     *
     * @param primaryStage
     * @throws Exception
     */

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../Resources/SignInForm.fxml"));
        primaryStage.setTitle("Appointments Scheduler");
        primaryStage.setScene(new Scene(root, 350, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}

/**
 * View of the DB structure
 * appointments TABLE_NAME,  Appointment_ID,Title,Description,Location,Type,Start,End,Create_Date,Created_By,Last_Update,Last_Updated_By,Customer_ID,User_ID,Contact_ID GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * contacts TABLE_NAME,  Contact_ID,Contact_Name,Email GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * countries TABLE_NAME,  Country_ID,Country,Create_Date,Created_By,Last_Update,Last_Updated_By GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * customers TABLE_NAME,  Customer_ID,Customer_Name,Address,Postal_Code,Phone,Create_Date,Created_By,Last_Update,Last_Updated_By,Division_ID GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * first_level_divisions TABLE_NAME,  Division_ID,Division,Create_Date,Created_By,Last_Update,Last_Updated_By,COUNTRY_ID GROUP_CONCAT(column_name ORDER BY ordinal_position)
 * users TABLE_NAME,  User_ID,User_Name,Password,Create_Date,Created_By,Last_Update,Last_Updated_By GROUP_CONCAT(column_name ORDER BY ordinal_position)
 */