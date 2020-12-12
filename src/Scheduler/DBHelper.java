package Scheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DBHelper {

    public static void getConnection() {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
            reader.getString("UserName"), reader.getString("Password"))) {

                System.out.println("connection successful");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean signInUser(HashMap<String, String> data) {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
            reader.getString("UserName"), reader.getString("Password"))) {

                System.out.println("connection successful");
                boolean userCorrect = false;

                String sqlUserLoginSelect = "SELECT User_ID, User_Name FROM users WHERE User_Name=? AND Password=?";
                PreparedStatement userLoginSelect = connection.prepareStatement(sqlUserLoginSelect);
                userLoginSelect.setString(1, data.get("user name"));
                userLoginSelect.setString(2, data.get("password"));
                ResultSet rs = userLoginSelect.executeQuery();
                while(rs.next()) {
                    AppHelper.user.add(String.valueOf(rs.getObject("User_ID")));
                    AppHelper.user.add(String.valueOf(rs.getObject("User_Name")));
                    userCorrect = true;
//                    AppHelper.user.add(rs.getObject())
                }
//                rs.beforeFirst();
                return userCorrect;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public static boolean showAllDB() {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
            reader.getString("UserName"), reader.getString("Password"))) {

                String sql = "SELECT table_name,GROUP_CONCAT(column_name ORDER BY ordinal_position) FROM information_schema.columns WHERE table_schema = DATABASE() GROUP BY table_name ORDER BY table_name";
                PreparedStatement allData = connection.prepareStatement(sql);
                ResultSet rs = allData.executeQuery(sql);
                ResultSetMetaData rsmd = rs.getMetaData();

                int columnsNumber = rsmd.getColumnCount();
                while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    }
                System.out.println();
                    }
                System.out.println("connection successful");
                return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // Get the initial customer, appointments, state, contacts, and countries data for use in the app.
    public static void setInitDataFromDB() {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
                reader.getString("UserName"), reader.getString("Password"))) {

            System.out.println("connection successful");

            // SQL for getting state country ID from specified first level divisions table and countries table
            String firstDivisionSql = "SELECT Division, Country_ID FROM first_level_divisions WHERE Division_ID=?";
            String countrySql = "SELECT Country FROM countries WHERE Country_ID=?";

            // SQL for getting contact name from specified appointment
            String contactSql = "SELECT Contact_Name FROM contacts WHERE contact_ID=?";

            // Get all columns from customers, first level divisions, countries, contacts, and appointments tables
            String customerSql = "SELECT * FROM customers";
            String allFirstDivisions = "SELECT * FROM first_level_divisions";
            String allCountries = "SELECT * FROM countries";
            String allContacts = "SELECT * FROM contacts";
            String allAppointments = "SELECT * FROM appointments";

            // Create prepared statements for all customers, states, countries, contacts, and appointments sql statements
            PreparedStatement allFirstDivisionPS = connection.prepareStatement(allFirstDivisions);
            PreparedStatement allCountriesPS = connection.prepareStatement(allCountries);
            PreparedStatement allAppointmentsPS = connection.prepareStatement(allAppointments);
            PreparedStatement allContactsPS = connection.prepareStatement(allContacts);
            PreparedStatement customerPreStatement = connection.prepareStatement(customerSql);

            // Fill a result set with the data
            ResultSet allCustomersRS = customerPreStatement.executeQuery();
            ResultSet allFirstDivisionRS = allFirstDivisionPS.executeQuery();
            ResultSet allCountriesRS = allCountriesPS.executeQuery();
            ResultSet allContactsRS = allContactsPS.executeQuery();
            ResultSet allAppointmentsRS = allAppointmentsPS.executeQuery();

            // Iterate through all sates results set
            while (allFirstDivisionRS.next()) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                //Iterate Column
                for(int i = 1; i <= 7; i++){
                    // Add data to observable array list for easier use
                    row.add(allFirstDivisionRS.getObject(i));
//                    System.out.println(i + " "+allCustomersRS.getObject(i));
                }

                FirstDivision firstDivision = new FirstDivision((int)row.get(0), (String)row.get(1),
                        (Timestamp) row.get(2), (String)row.get(3), (Timestamp)row.get(4), (String)row.get(5),
                        (int)row.get(6));
                AppHelper.firstDivisions.add(firstDivision);
            }

            // Iterate through all countries results set
            while(allCountriesRS.next()) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                //Iterate Column
                for(int i = 1; i <= 6; i++){
                    // Add data to observable array list for easier use
                    row.add(allCountriesRS.getObject(i));
//                    System.out.println(i + " "+allCustomersRS.getObject(i));
                }

                Country countries = new Country((int)row.get(0), (String)row.get(1),
                        (Timestamp) row.get(2), (String)row.get(3), (Timestamp)row.get(4), (String)row.get(5));
                AppHelper.countries.add(countries);
            }

            // Iterate through all contacts results set
            while (allContactsRS.next()) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                //Iterate Column
                for(int i = 1; i <= 3; i++){
                    // Add data to observable array list for easier use
                    row.add(allContactsRS.getObject(i));
//                    System.out.println(i + " "+allCustomersRS.getObject(i));
                }

                Contacts contacts = new Contacts((int)row.get(0), (String)row.get(1), (String) row.get(2));
                AppHelper.contacts.add(contacts);
            }

            // Iterate through all appointments results set
            while(allAppointmentsRS.next()) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                //Iterate Column
                for(int i = 1; i <= 14; i++){
                    // Add data to observable array list for easier use
                    row.add(allAppointmentsRS.getObject(i));
                    System.out.println(i + " "+allAppointmentsRS.getObject(i));
                }

                PreparedStatement contactSqlPS = connection.prepareStatement(contactSql);
                contactSqlPS.setInt(1, (int)row.get(13));

                String contactName = "";

                ResultSet resultSet = contactSqlPS.executeQuery();
                resultSet.next();
                contactName = resultSet.getString(1);

                Appointments appointments = new Appointments((int)row.get(0), (String)row.get(1),
                        (String) row.get(2), (String)row.get(3), (String)row.get(4), (Timestamp) row.get(5),
                        (Timestamp) row.get(6), (Timestamp) row.get(7), (String)row.get(8),
                        (Timestamp) row.get(9), (String)row.get(10), (int)row.get(11), (int)row.get(12),
                        (int)row.get(13), contactName);
                AppHelper.appointments.add(appointments);
            }

            // Iterate all customers result set row
            while (allCustomersRS.next()) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                //Iterate Column
                for(int i = 1; i <= 10; i++){
                    // Add data to observable array list for easier use
                    row.add(allCustomersRS.getObject(i));
//                    System.out.println(i + " "+allCustomersRS.getObject(i));
                }
                System.out.println("Row [1] added "+row );

                // Create a prepared statement from state and country sql.
                PreparedStatement getStateSql = connection.prepareStatement(firstDivisionSql);
                PreparedStatement getCountrySql = connection.prepareStatement(countrySql);

                // Set the variable in the state sql statement and get results
                getStateSql.setInt(1,(int)row.get(9));
                ResultSet rs2 = getStateSql.executeQuery();

                // variables to store data from results
                String currentState ="";
                String currentCountry ="";
                int currentCountryID =-1;

                // Set the state and country ID from results
                while(rs2.next()) {
                    currentState = rs2.getString(1);
                    currentCountryID = rs2.getInt(2);
                }

                // Get and set the country from results from specified sql statement
                getCountrySql.setInt(1, currentCountryID);
                ResultSet rs3 = getCountrySql.executeQuery();
                rs3.next();
                currentCountry = rs3.getString(1);

                // Create new customer and initialize the date and add those customers to an observable list
                Customers customer = new Customers((int)row.get(0), (String)row.get(1), (String)row.get(2),
                        (String)row.get(3), (String)row.get(4), (Timestamp)row.get(5), (String)row.get(6),
                        (Timestamp)row.get(7), (String)row.get(8), (int)row.get(9), currentState, currentCountry);
                AppHelper.customers.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void addCustomer(Customers customer) {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
                reader.getString("UserName"), reader.getString("Password"))) {

            String sql = "INSERT INTO customers VALUES(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setTimestamp(6, customer.getCreatedDate());
            preparedStatement.setString(7, customer.getCreatedBy());
            preparedStatement.setTimestamp(8, customer.getLastUpdated());
            preparedStatement.setString(9, customer.getLastUpdatedBy());
            preparedStatement.setInt(10, customer.getDivisionID());


            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateCustomer(Customers customer) {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
                reader.getString("UserName"), reader.getString("Password"))) {

            String updateSql = "UPDATE customers SET Customer_ID=?,Customer_Name=?,Address=?,Postal_Code=?,Phone=?,Last_Update=?,Last_Updated_By=?,Division_ID=? WHERE Customer_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, customer.getCustomerID());
            preparedStatement.setString(2, customer.getName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getPostalCode());
            preparedStatement.setString(5, customer.getPhone());
            preparedStatement.setTimestamp(6, customer.getLastUpdated());
            preparedStatement.setString(7, customer.getLastUpdatedBy());
            preparedStatement.setInt(8, customer.getDivisionID());
            preparedStatement.setInt(9, customer.getCustomerID());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean deleteCustomer(int customerID) {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
                reader.getString("UserName"), reader.getString("Password"))) {

            System.out.println("connection successful");
            String checkApptSql = "SELECT Customer_ID FROM appointments WHERE Customer_ID=?";
            PreparedStatement checkApptPS = connection.prepareStatement(checkApptSql);
            checkApptPS.setInt(1, customerID);
            ResultSet resultSet = checkApptPS.executeQuery();

            if (!resultSet.next()){
                String deleteSql = "DELETE FROM customers WHERE Customer_ID=?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
                preparedStatement.setInt(1, customerID);
                preparedStatement.execute();
                return true;
            }
            return false;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    public static void getAllAppointments() {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
                reader.getString("UserName"), reader.getString("Password"))) {

            System.out.println("connection successful");
            // SQL for getting contact name from specified appointment
            String contactSql = "SELECT Contact_Name FROM contacts WHERE contact_ID=?";

            String apptByWeekSql = "SELECT * FROM appointments ORDER BY WEEK(Start)";
            String apptByMonthSql = "SELECT * FROM appointments ORDER BY Month(Start)";
            PreparedStatement byWeekPS = connection.prepareStatement(apptByWeekSql);
            PreparedStatement byMonthPS = connection.prepareStatement(apptByMonthSql);

            ResultSet rsByWeek = byWeekPS.executeQuery();
            ResultSet rsByMonth = byMonthPS.executeQuery();

            // Clear the list to reset the values
            AppHelper.apptsByMonth.clear();
            AppHelper.apptsByWeek.clear();

            while(rsByWeek.next()) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                //Iterate Column
                for(int i = 1; i <= 14; i++){
                    // Add data to observable array list for easier use
                    row.add(rsByWeek.getObject(i));
                    System.out.println(i + " "+rsByWeek.getObject(i));
                }

                PreparedStatement contactSqlPS = connection.prepareStatement(contactSql);
                contactSqlPS.setInt(1, (int)row.get(13));

                String contactName = "";

                ResultSet resultSet = contactSqlPS.executeQuery();
                resultSet.next();
                contactName = resultSet.getString(1);

                Appointments appointments = new Appointments((int)row.get(0), (String)row.get(1),
                        (String) row.get(2), (String)row.get(3), (String)row.get(4), (Timestamp) row.get(5),
                        (Timestamp) row.get(6), (Timestamp) row.get(7), (String)row.get(8),
                        (Timestamp) row.get(9), (String)row.get(10), (int)row.get(11), (int)row.get(12),
                        (int)row.get(13), contactName);
                AppHelper.apptsByWeek.add(appointments);
            }

            while(rsByMonth.next()) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                //Iterate Column
                for(int i = 1; i <= 14; i++){
                    // Add data to observable array list for easier use
                    row.add(rsByMonth.getObject(i));
                    //System.out.println(i + " "+rsByMonth.getObject(i));
                }

                PreparedStatement contactSqlPS = connection.prepareStatement(contactSql);
                contactSqlPS.setInt(1, (int)row.get(13));

                String contactName = "";

                ResultSet resultSet = contactSqlPS.executeQuery();
                resultSet.next();
                contactName = resultSet.getString(1);

                Appointments appointments = new Appointments((int)row.get(0), (String)row.get(1),
                        (String) row.get(2), (String)row.get(3), (String)row.get(4), (Timestamp) row.get(5),
                        (Timestamp) row.get(6), (Timestamp) row.get(7), (String)row.get(8),
                        (Timestamp) row.get(9), (String)row.get(10), (int)row.get(11), (int)row.get(12),
                        (int)row.get(13), contactName);
                AppHelper.apptsByMonth.add(appointments);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static void addAppointment(Appointments appointment) {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
                reader.getString("UserName"), reader.getString("Password"))) {

            String sql = "INSERT INTO appointments VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, appointment.getAppointmentID());
            preparedStatement.setString(2, appointment.getTitle());
            preparedStatement.setString(3, appointment.getDescription());
            preparedStatement.setString(4, appointment.getLocation());
            preparedStatement.setString(5, appointment.getType());
            preparedStatement.setTimestamp(6, appointment.getStart());
            preparedStatement.setTimestamp(7, appointment.getEnd());
            preparedStatement.setTimestamp(8, appointment.getCreatedDate());
            preparedStatement.setString(9, appointment.getCreatedBy());
            preparedStatement.setTimestamp(10, appointment.getLastUpdated());
            preparedStatement.setString(11, appointment.getLastUpdatedBy());
            preparedStatement.setInt(12, appointment.getCustomerID());
            preparedStatement.setInt(13, appointment.getUserID());
            preparedStatement.setInt(14, appointment.getContactID());

            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void updateAppointment(Appointments appointment) {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
                reader.getString("UserName"), reader.getString("Password"))) {

            String updateSql = "UPDATE appointments SET Appointment_ID=?,Title=?,Description=?,Location=?,Type=?,Start=?,End=?,Create_Date=?,Created_By=?,Last_Update=?,Last_Updated_By=?,Customer_ID=?,User_ID=?,Contact_ID=? WHERE Appointment_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setInt(1, appointment.getAppointmentID());
            preparedStatement.setString(2, appointment.getTitle());
            preparedStatement.setString(3, appointment.getDescription());
            preparedStatement.setString(4, appointment.getLocation());
            preparedStatement.setString(5, appointment.getType());
            preparedStatement.setTimestamp(6, appointment.getStart());
            preparedStatement.setTimestamp(7, appointment.getEnd());
            preparedStatement.setTimestamp(8, appointment.getCreatedDate());
            preparedStatement.setString(9, appointment.getCreatedBy());
            preparedStatement.setTimestamp(10, appointment.getLastUpdated());
            preparedStatement.setString(11, appointment.getLastUpdatedBy());
            preparedStatement.setInt(12, appointment.getCustomerID());
            preparedStatement.setInt(13, appointment.getUserID());
            preparedStatement.setInt(14, appointment.getContactID());
            preparedStatement.setInt(15, appointment.getAppointmentID());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static boolean deleteAppointment(int appointmentID) {
        ResourceBundle reader = ResourceBundle.getBundle("Resources/dbconfig");
        try (Connection connection = DriverManager.getConnection(reader.getString("db.url"),
                reader.getString("UserName"), reader.getString("Password"))) {

            System.out.println("connection successful");

            String deleteSql = "DELETE FROM appointments WHERE Appointment_ID=?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setInt(1, appointmentID);

            preparedStatement.execute();
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    // TODO Delete old appointments code if not needed
    // TODO Check For Appointment
}
