package Scheduler;

import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.util.Duration;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppHelper {
    public static Calendar calendar = Calendar.getInstance();


    // Current logged in user language information
    public static String language = "";

    // Containers for the data from the database.
    public static ObservableList<Customers> customers = FXCollections.observableArrayList();
    public static ObservableList<Appointments> appointments = FXCollections.observableArrayList();
    public static ObservableList<FirstDivision> firstDivisions = FXCollections.observableArrayList();
    public static ObservableList<Country> countries = FXCollections.observableArrayList();
    public static ObservableList<Contacts> contacts = FXCollections.observableArrayList();
    public static ObservableList<Appointments> apptsByWeek = FXCollections.observableArrayList();
    public static ObservableList<Appointments> apptsByMonth = FXCollections.observableArrayList();



    // User Information container
    public static ObservableList<String> user = FXCollections.observableArrayList();


    // Check if there is an appointment within 15 min
    public static void checkForAppt() {

    }

    // Clear data for new Session
    public static void clearSessionData() {
        customers.clear();
        user.clear();
        appointments.clear();
        contacts.clear();
        firstDivisions.clear();
        countries.clear();
    }

    public static void resetData() {
        customers.clear();
        appointments.clear();
        contacts.clear();
        firstDivisions.clear();
        countries.clear();
        apptsByWeek.clear();
        apptsByMonth.clear();
    }

    public static FirstDivision getFirstDivision(String name) {
        for (FirstDivision state: firstDivisions) {
            if (state.getDivisionName() == name) {
                return state;
            }
        }
        return null;
    }

    public static Contacts getContact(String name) {
        for (Contacts contacts: contacts) {
            if (contacts.getContactName() == name) {
                return contacts;
            }
        }
        return null;
    }

    public static Country getCountry(String name) {
        for (Country country: countries) {
            if (country.getCountryName() == name) {
                return country;
            }
        }
        return null;
    }

    public static Customers createCustomerFromFormData(HashMap<String, String> customerFormData) {
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        return new Customers(Integer.parseInt(customerFormData.get("customer ID")), customerFormData.get("customer name"),
            customerFormData.get("address"), customerFormData.get("postal code"), customerFormData.get("phone number"),
                currentTimestamp, user.get(1), currentTimestamp,
                user.get(1), getFirstDivision(customerFormData.get("state")).getDivisionID(), customerFormData.get("state"), customerFormData.get("country"));
    }

    public static Appointments createApptFromFormData(HashMap<String, String> appointmentsFormData) {
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        return new Appointments(Integer.parseInt(appointmentsFormData.get("appointment ID")), appointmentsFormData.get("title"),
                appointmentsFormData.get("description"), appointmentsFormData.get("location"), appointmentsFormData.get("type"),
                Timestamp.valueOf(appointmentsFormData.get("start")), Timestamp.valueOf(appointmentsFormData.get("end")), currentTimestamp, user.get(1),
                currentTimestamp, user.get(1), Integer.parseInt(appointmentsFormData.get("customer ID")),
                Integer.parseInt(appointmentsFormData.get("user ID")), getContact(appointmentsFormData.get("contact name")).getContactID(), appointmentsFormData.get("contact name"));
    }

    public static String checkFormCorrectness(HashMap<String, String> formData1) {
        String numberRegex = "[0-9]+";
        Pattern pattern = Pattern.compile(numberRegex);
        StringBuilder formStatus = new StringBuilder();
        if (formData1.values().stream().anyMatch(Objects::isNull)){
            return "All Fields Required";
        }
        if (formData1.size() < 2) {
            return "All Fields Required";
        }
        if (!formData1.values().stream().anyMatch(currentValue -> currentValue.isBlank())) {
            /**
             * (var50)username,name,state,country,postalcode,title,description,location,contact,type,
             * password(text)
             * address(var100)
             * (int10)customer_id,user_id
             * phone(var/num50)
             * (datetime)start,end
             */
            formData1.forEach((key, value) -> {
                if(key == "user name" || key == "customer name" || key == "state" || key == "country" || key == "postal Code"
                || key == "title" || key == "description" || key == "location" || key == "contact" || key == "type") {
                    if(value.length() >= 50) {
                        String tempStatus = key + " " + "is not correct .";
                        formStatus.append(tempStatus);
                    }
                } else if(key == "address") {
                    if (value.length() >= 100) {
                        String tempStatus = key + " " + "is not correct .";
                        formStatus.append(tempStatus);
                    }
                } else if(key == "customer ID" || key == "userID") {
                    if(value.length() < 10){
                        Matcher matcher = pattern.matcher(value);
                        if (!matcher.matches()) {
                            String tempStatus = key + " " + "is not correct .";
                            formStatus.append(tempStatus);
                        }
                    } else {
                        String tempStatus = key + " " + "is not correct .";
                        formStatus.append(tempStatus);
                    }
                } else if(key == "phone number") {
                    if(value.length() < 50){
                        Matcher matcher = pattern.matcher(value);
                        if (!matcher.matches()) {
                            String tempStatus = key + " " + "is not correct .";
                            formStatus.append(tempStatus);
                        }
                    } else {
                        String tempStatus = key + " " + "is not correct .";
                        formStatus.append(tempStatus);
                    }
                } else if (key == "start" || key == "end") {
                    // Datetime formatter to parse a string
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
                    try {
                        dateTimeFormatter.parse(value);
                    } catch (DateTimeParseException exception) {
                        formStatus.append("Correct Format for Dates and Time is yyyy-mm-dd hh:mm:ss ");
                    }
                }
            });
            if (formStatus.isEmpty()) {
                return "correct";
            }
            return String.valueOf(formStatus);
        } else if (formData1.values().stream().anyMatch(currentValue -> currentValue.isBlank())) {
            return "All Fields Required";
        }
        return "correct";
    }

    public static LocalDateTime getCurrentDateTime() {
        return null;
    }

    public static String getCurrentLocal() {
        return Locale.getDefault().getDisplayCountry();
    }

}
