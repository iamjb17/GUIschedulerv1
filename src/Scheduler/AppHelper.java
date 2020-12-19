/**
 * @author Jessie Burton
 */
package Scheduler;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Public class to help with movement of data and general functionality of the entire app.
public class AppHelper {

    // Create current time variables
    public static Calendar calendar = Calendar.getInstance();
    public static Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    // Variable for the current timezone of the user
    public static TimeZone currentTimeZone = calendar.getTimeZone();
    // Calendar variable the start and end Time and date
    public static Calendar startCalendar = Calendar.getInstance();
    public static Calendar endCalendar = Calendar.getInstance();

    // Variables for use in the appointment alert
    public static int apptIDSoon = -1;
    public static Calendar apptTDSoon;


    // Current logged in user language and Country information
    public static String systemLanguage = System.getProperty("user.language");
    public static String systemCountry = System.getProperty("user.country");

    // Variables for testing internationalization
    public static String tempLang = "fr";
    public static String tempCon = "CA";

    // Variable for keep track of what was the last scene the user was in
    public static String backWhere = "";

    // Create a local object for resource bundle use
    public static Locale locale = new Locale(systemLanguage, systemCountry);

    // Create resource bundle to parse all strings in app
    public static ResourceBundle localeRB = ResourceBundle.getBundle("Resources/TranslateBundle", locale);

    // Containers for the data from the database.
    // Stores all customers
    public static ObservableList<Customers> customers = FXCollections.observableArrayList();
    // Stores all appointments
    public static ObservableList<Appointments> appointments = FXCollections.observableArrayList();
    // Stores all states
    public static ObservableList<FirstDivision> firstDivisions = FXCollections.observableArrayList();
    // Stores all countries
    public static ObservableList<Country> countries = FXCollections.observableArrayList();
    // Stores all contacts
    public static ObservableList<Contacts> contacts = FXCollections.observableArrayList();
    // Stores all appointments sorted by week
    public static ObservableList<Appointments> apptsByWeek = FXCollections.observableArrayList();
    // Stores all appointments sorted by month
    public static ObservableList<Appointments> apptsByMonth = FXCollections.observableArrayList();
    // Stores all schedules of the contacts
    public static ObservableList<ObservableList> contactSched = FXCollections.observableArrayList();
    // Stores the number of customers for each location
    public static ObservableMap<String, Integer> numCustomerByLocation = FXCollections.observableHashMap();


    // User Information container
    public static ObservableList<String> user = FXCollections.observableArrayList();

    // Convert given date and time to local time of user
    public static Calendar convertTimeToLocal(Calendar ts) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss zzz");
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTimeZone(currentTimeZone);
        newCalendar.setTimeInMillis(ts.getTimeInMillis());

        simpleDateFormat.format(newCalendar.getTime());

        return newCalendar;
    }

    // Convert date and time back to utc for database storage
    public static Calendar convertTimeBackToUTC(Calendar ts) {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        newCalendar.setTimeInMillis(ts.getTimeInMillis());

        return newCalendar;
    }

    // Convert Timestamp to calendar
    public static Calendar convertTSToCalendar(Timestamp ts) {
        Calendar newCal = Calendar.getInstance();
        newCal.setTimeInMillis(ts.getTime());
        return newCal;
    }


    // Check if there is an appointment within 15 min of logging in
    public static void apptWithinTimeFrame() {
       Instant now = Instant.now();
       Instant then = now.plusMillis(15 * 60 * 1000);
       int size = appointments.size();
       for (int i = 0; i < size; i++) {
            Instant thisInstant = Instant.ofEpochMilli(appointments.get(i).getStart().getTimeInMillis());

            if (!thisInstant.isBefore(now) && thisInstant.isBefore(then)) {
                apptIDSoon = appointments.get(i).getAppointmentID();
                apptTDSoon = appointments.get(i).getStart();
            }
        }
    }

    // Check if appt time is within business hours
    public static boolean withinBusinessHours(String ts) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        DateTimeFormatter formatterAP = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        TemporalAccessor d1 = formatterAP.parse(ts);


        LocalTime currTime = LocalTime.from(d1);

        // Models for open and close times of the business
        LocalTime startTime = LocalTime.parse("2018-03-01 08:00:00", formatter);
        LocalTime closeTime = LocalTime.parse("2018-03-01 22:00:00", formatter);

        return currTime.isBefore(closeTime) && currTime.isAfter(startTime);
    }

    // Check for overlapping appts
    public static boolean overlapAppt(String ts) {
        AtomicBoolean withinRange = new AtomicBoolean(true);
        DateTimeFormatter formatterAP = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        TemporalAccessor d1 = formatterAP.parse(ts);

        LocalDateTime currTime = LocalDateTime.from(d1);

        for (Appointments currAppt : appointments) {
            if ((currTime.compareTo(LocalDateTime.ofInstant(currAppt.getStart().toInstant(), currAppt.getStart().getTimeZone().toZoneId())) < 0 &&
                    currTime.compareTo(LocalDateTime.ofInstant(currAppt.getEnd().toInstant(), currAppt.getEnd().getTimeZone().toZoneId())) < 0) ||
                    currTime.compareTo(LocalDateTime.ofInstant(currAppt.getStart().toInstant(), currAppt.getStart().getTimeZone().toZoneId())) > 0 &&
                            currTime.compareTo(LocalDateTime.ofInstant(currAppt.getEnd().toInstant(), currAppt.getEnd().getTimeZone().toZoneId())) > 0) {
                withinRange.set(true);
            } else {
                withinRange.set(false);
                return false;
            }
        }
        return withinRange.get();
    }

    // Clear data for new Session
    public static void clearSessionData() {
        customers.clear();
        user.clear();
        appointments.clear();
        contacts.clear();
        firstDivisions.clear();
        countries.clear();
        apptsByWeek.clear();
        apptsByMonth.clear();
    }

    // Reset certain data so as not to append data to containers
    public static void resetData() {
        customers.clear();
        appointments.clear();
        contacts.clear();
        firstDivisions.clear();
        countries.clear();
        apptsByWeek.clear();
        apptsByMonth.clear();
    }

    // Get first division from given name
    public static FirstDivision getFirstDivision(String name) {
        for (FirstDivision state: firstDivisions) {
            if (state.getDivisionName() == name) {
                return state;
            }
        }
        return null;
    }

    // Get Contact from given name
    public static Contacts getContact(String name) {
        for (Contacts contacts: contacts) {
            if (contacts.getContactName() == name) {
                return contacts;
            }
        }
        return null;
    }

    // Get Country from given name
    public static Country getCountry(String name) {
        for (Country country: countries) {
            if (country.getCountryName() == name) {
                return country;
            }
        }
        return null;
    }

    // Convert string representing time to calendar
    public static  Calendar convertFromStringToCal(String timeString) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").parse(timeString);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal1.setTime(date1);

        return cal1;
    }

    // Create customer from given form data
    public static Customers createCustomerFromFormData(HashMap<String, String> customerFormData) {
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        return new Customers(Integer.parseInt(customerFormData.get("customer ID")), customerFormData.get("customer name"),
            customerFormData.get("address"), customerFormData.get("postal code"), customerFormData.get("phone number"),
                currentTimestamp, user.get(1), currentTimestamp,
                user.get(1), getFirstDivision(customerFormData.get("state")).getDivisionID(), customerFormData.get("state"), customerFormData.get("country"));
    }

    // Create appointment from given form data
    public static Appointments createApptFromFormData(HashMap<String, String> appointmentsFormData) throws ParseException {
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        return new Appointments(Integer.parseInt(appointmentsFormData.get("appointment ID")), appointmentsFormData.get("title"),
                appointmentsFormData.get("description"), appointmentsFormData.get("location"), appointmentsFormData.get("type"),
                convertFromStringToCal(appointmentsFormData.get("start")), convertFromStringToCal(appointmentsFormData.get("end")), currentTimestamp, user.get(1),
                currentTimestamp, user.get(1), Integer.parseInt(appointmentsFormData.get("customer ID")),
                Integer.parseInt(appointmentsFormData.get("user ID")), getContact(appointmentsFormData.get("contact name")).getContactID(), appointmentsFormData.get("contact name"));
    }

    // Check given form data for basic correctness and error checking
    public static String checkFormCorrectness(HashMap<String, String> formData1) {
        String numberRegex = "[0-9]+";
        Pattern pattern = Pattern.compile(numberRegex);
        StringBuilder formStatus = new StringBuilder();
        if (formData1.values().stream().anyMatch(Objects::isNull)){
            return localeRB.getString("erAllFieldsReq");
        }
        if (formData1.size() < 2) {
            return localeRB.getString("erAllFieldsReq");
        }
        if (!formData1.values().stream().anyMatch(currentValue -> currentValue.isBlank())) {
            /**
             * Variables to check:
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
                        String tempKey = testKey(key);
                        if (tempKey == key) {
                            String tempStatus = localeRB.getString(key) + " " + localeRB.getString("erIsNotCorrect");
                        }
                        String tempStatus = tempKey + " " + localeRB.getString("erIsNotCorrect");
                        formStatus.append(tempStatus);
                    }
                } else if(key == "address") {
                    if (value.length() >= 100) {
                        String tempStatus = localeRB.getString("Address") + " " + localeRB.getString("erIsNotCorrect");
                        formStatus.append(tempStatus);
                    }
                } else if(key == "customer ID" || key == "userID") {
                    if(value.length() < 10){
                        Matcher matcher = pattern.matcher(value);
                        String tempKey = "UserID";
                        if (key == "customer ID"){
                            tempKey = "CustomerID";
                        }
                        if (!matcher.matches()) {
                            String tempStatus = localeRB.getString(tempKey) + " " + localeRB.getString("erIsNotCorrect");
                            formStatus.append(tempStatus);
                        }
                    } else {
                        String tempStatus = key + " " + localeRB.getString("erIsNotCorrect");
                        formStatus.append(tempStatus);
                    }
                } else if(key == "phone number") {
                    if(value.length() < 50){
                        Matcher matcher = pattern.matcher(value);
                        if (!matcher.matches()) {
                            String tempStatus = localeRB.getString("Phone") + " " + localeRB.getString("erIsNotCorrect");
                            formStatus.append(tempStatus);
                        }
                    } else {
                        String tempStatus = key + " " + localeRB.getString("erIsNotCorrect");
                        formStatus.append(tempStatus);
                    }
                } else if (key == "start" || key == "end") {
                    // Datetime formatter to parse a string
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
                    try {
                        dateTimeFormatter.parse(value);
                    } catch (DateTimeParseException exception) {
                        formStatus.append(localeRB.getString("erDTFormatting"));
                    }
                    if (withinBusinessHours(value)) {

                    } else {
                        formStatus.append(localeRB.getString("erStoreClosed"));
                    }
                    if(overlapAppt(value)) {

                    } else {
                        formStatus.append(localeRB.getString("erOverlap"));
                    }
                }
            });
            if (formStatus.isEmpty()) {
                return "correct";
            }
            return String.valueOf(formStatus);
        } else if (formData1.values().stream().anyMatch(currentValue -> currentValue.isBlank())) {
            return localeRB.getString("erAllFieldsReq");
        }
        return "correct";
    }

    // return the proper description of certain keys for internationalizaion purposes
    public static String testKey(String key) {
        switch (key) {
            case "user name":
                return localeRB.getString("userName");
            case "customer name":
                return localeRB.getString("kyCustomerName");
            case "phone number":
                return localeRB.getString("Phone");
            case "postal code":
                return localeRB.getString("Postal");
            default:
                return key;
        }
    }

    // Return timezone of current user
    public static String getCurrentLocal() {
        return calendar.getTimeZone().toZoneId().toString();
    }
}
