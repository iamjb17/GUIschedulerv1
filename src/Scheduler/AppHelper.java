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
import java.time.format.TextStyle;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Public class to help with movement of data and general functionality of the entire app.
 */
public class AppHelper {

    /**
     * Create current time variable for to check database data against
     */
    public static Calendar calendar = Calendar.getInstance();
    /**
     * Create current time variable for to check database data against
     */
    public static Timestamp currentTime = new Timestamp(System.currentTimeMillis());
    /**
     * Variable for the current timezone of the user
     */
    public static TimeZone currentTimeZone = calendar.getTimeZone();
    /**
     * Calendar variable the start and end Time and date
     */
    public static Calendar startCalendar = Calendar.getInstance();
    /**
     * Calendar variable the start and end Time and date
     */
    public static Calendar endCalendar = Calendar.getInstance();

    /**
     * Variables for use in the appointment alert
     * stores appointment ID
     */
    public static int apptIDSoon = -1;
    /**
     * Variables for use in the appointment alert
     * stores appointment time and date
     */
    public static Calendar apptTDSoon;


    /**
     * Current logged in user language and Country information
     */
    public static String systemLanguage = System.getProperty("user.language");
    /**
     * Current logged in user language and Country information
     */
    public static String systemCountry = System.getProperty("user.country");

    // Variables for testing internationalization
    public static String tempLang = "fr";
    public static String tempCon = "CA";


    /**
     * Variable for keep track of what was the last scene the user was in
     */
    public static String backWhere = "";


    /**
     * Create a local object for resource bundle use throughout the application
     */
    public static Locale locale = new Locale(systemLanguage, systemCountry);


    /**
     * Create resource bundle to parse all strings in app
     * Internationalization
     */
    public static ResourceBundle localeRB = ResourceBundle.getBundle("Resources/TranslateBundle", locale);

    // Containers for the data from the database.

    /**
     *  Stores all customers
     */
    public static ObservableList<Customers> customers = FXCollections.observableArrayList();

    /**
     * Stores all appointments
     */
    public static ObservableList<Appointments> appointments = FXCollections.observableArrayList();

    /**
     * Stores all states
     */
    public static ObservableList<FirstDivision> firstDivisions = FXCollections.observableArrayList();
    /**
     * Stores all countries
     */
    public static ObservableList<Country> countries = FXCollections.observableArrayList();
    /**
     * Stores all contacts
     */
    public static ObservableList<Contacts> contacts = FXCollections.observableArrayList();
    /**
     * Stores all appointments filtered by current week
     */
    public static ObservableList<Appointments> apptsByWeek = FXCollections.observableArrayList();
    /**
     * Stores all appointments filtered by current month
     */
    public static ObservableList<Appointments> apptsByMonth = FXCollections.observableArrayList();

    /**
     * Stores all schedules of the contacts
     */
    public static ObservableList<ObservableList> contactSched = FXCollections.observableArrayList();
    /**
     * Stores the number of customers for each location
     */
    public static ObservableMap<String, Integer> numCustomerByLocation = FXCollections.observableHashMap();

    /**
     * User Information container
     */
    public static ObservableList<String> user = FXCollections.observableArrayList();

    /**
     * Convert given date and time to local time of user
     * @param ts timestamp string in UTC format
     * @return returns calendar object in local timezone format
     */
    public static Calendar convertTimeToLocal(Calendar ts) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss zzz");
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTimeZone(currentTimeZone);
        newCalendar.setTimeInMillis(ts.getTimeInMillis());

        simpleDateFormat.format(newCalendar.getTime());

        return newCalendar;
    }

    /**
     * Convert date and time back to utc for database storage
     * @param ts Given calendar object in local timezone format
     * @return return calendar object in UTC format for database storage
     */
    public static Calendar convertTimeBackToUTC(Calendar ts) {
        Calendar newCalendar = Calendar.getInstance();
        newCalendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        newCalendar.setTimeInMillis(ts.getTimeInMillis());

        return newCalendar;
    }

    /**
     * Convert Timestamp to calendar
     * @param ts Given timestamp to be converted
     * @return Calendar object built from timestamp
     */
    public static Calendar convertTSToCalendar(Timestamp ts) {
        Calendar newCal = Calendar.getInstance();
        newCal.setTimeInMillis(ts.getTime());
        return newCal;
    }


    /**
     * Check if there is an appointment within 15 min of logging in
     * to set the proper alert state
     */
    public static void apptWithinTimeFrame() {
       Instant now = Instant.now();
       Instant then = now.plusMillis(15 * 60 * 1000);
       int size = appointments.size();
       for (int i = 0; i < size; i++) {
            Instant thisInstant = Instant.ofEpochMilli(appointments.get(i).getStart().getTimeInMillis());

            if (!thisInstant.isBefore(now) && thisInstant.isBefore(then)) {
                apptIDSoon = appointments.get(i).getAppointmentID();
                apptTDSoon = convertTimeToLocal(appointments.get(i).getStart());
            }
        }
    }

    /**
     * Check if appt time is within business hours
     * @param ts A string in the form of a timestamp
     * @return boolean value of if the given timestamp string is within business operating hours
     */
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

    /**
     * Check for overlapping appointments times
     * @param startTS A string in the form of a timestamp representing the start time
     * @param endTS A string in the form of a timestamp representing the end time
     * @return boolean value of if the given timestamp string is overlaps with another appointment time
     */
    public static boolean overlapAppt(String startTS, String endTS) {
        AtomicBoolean withinRange = new AtomicBoolean(true);
        DateTimeFormatter formatterAP = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");
        TemporalAccessor d1 = formatterAP.parse(startTS);
        TemporalAccessor d2 = formatterAP.parse(endTS);

        LocalDateTime currStart = LocalDateTime.from(d1);
        LocalDateTime currEnd = LocalDateTime.from(d2);


        //   S(----)E

            for (Appointments currAppt : appointments) {
                if ((currStart.compareTo(LocalDateTime.ofInstant(currAppt.getStart().toInstant(), currAppt.getStart().getTimeZone().toZoneId())) < 0 &&
                        currEnd.compareTo(LocalDateTime.ofInstant(currAppt.getStart().toInstant(), currAppt.getStart().getTimeZone().toZoneId())) < 0) ||
                        currStart.compareTo(LocalDateTime.ofInstant(currAppt.getEnd().toInstant(), currAppt.getEnd().getTimeZone().toZoneId())) > 0 &&
                            currEnd.compareTo(LocalDateTime.ofInstant(currAppt.getEnd().toInstant(), currAppt.getEnd().getTimeZone().toZoneId())) > 0) {
                    withinRange.set(false);
                } else {
                    withinRange.set(true);
                    return true;
                }
            }
            return withinRange.get();


//        for (Appointments currAppt : appointments) {
//            if ((currTime.compareTo(LocalDateTime.ofInstant(currAppt.getStart().toInstant(), currAppt.getStart().getTimeZone().toZoneId())) < 0 &&
//                    currTime.compareTo(LocalDateTime.ofInstant(currAppt.getEnd().toInstant(), currAppt.getEnd().getTimeZone().toZoneId())) < 0) ||
//                    currTime.compareTo(LocalDateTime.ofInstant(currAppt.getStart().toInstant(), currAppt.getStart().getTimeZone().toZoneId())) > 0 &&
//                            currTime.compareTo(LocalDateTime.ofInstant(currAppt.getEnd().toInstant(), currAppt.getEnd().getTimeZone().toZoneId())) > 0) {
//                withinRange.set(true);
//            } else {
//                withinRange.set(false);
//                return false;
//            }
//        }
//        return withinRange.get();
    }

    /**
     * Clear data for new Session
     */
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

    /**
     * Reset certain data so as not to append data to containers
     */
    public static void resetData() {
        customers.clear();
        appointments.clear();
        contacts.clear();
        firstDivisions.clear();
        countries.clear();
        apptsByWeek.clear();
        apptsByMonth.clear();
    }

    /**
     * Get first division from given name
     * @param name String name representing a first division
     * @return First division with param name or null if not found
     */
    public static FirstDivision getFirstDivision(String name) {
        for (FirstDivision state: firstDivisions) {
            if (state.getDivisionName() == name) {
                return state;
            }
        }
        return null;
    }

    /**
     * Get Contact from given name
     * @param name String name representing a contact
     * @return Contact with given name or null if not found
     */
    public static Contacts getContact(String name) {
        for (Contacts contacts: contacts) {
            if (contacts.getContactName() == name) {
                return contacts;
            }
        }
        return null;
    }

    /**
     * Get Country from given name
     * @param name String name representing a country
     * @return Country with given name or null if not found
     */
    public static Country getCountry(String name) {
        for (Country country: countries) {
            if (country.getCountryName() == name) {
                return country;
            }
        }
        return null;
    }

    /**
     * Convert string representing time to calendar
     * @param timeString String representing time and date
     * @return returns calendar object from the input string
     * @throws ParseException throws exception if the given string cannot be parsed using proper formatter
     */
    public static  Calendar convertFromStringToCal(String timeString) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a").parse(timeString);
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeZone(TimeZone.getTimeZone("UTC"));
        cal1.setTime(date1);

        return cal1;
    }

    /**
     * Create customer from given form data
     * @param customerFormData A hashmap that stores form data from customer scene
     * @return Customer object from given form data
     */
    public static Customers createCustomerFromFormData(HashMap<String, String> customerFormData) {
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        return new Customers(Integer.parseInt(customerFormData.get("customer ID")), customerFormData.get("customer name"),
            customerFormData.get("address"), customerFormData.get("postal code"), customerFormData.get("phone number"),
                currentTimestamp, user.get(1), currentTimestamp,
                user.get(1), getFirstDivision(customerFormData.get("state")).getDivisionID(), customerFormData.get("state"), customerFormData.get("country"));
    }

    /**
     * Create appointment from given form data
     * @param appointmentsFormData A hashmap that stores form data from appointments scene
     * @return Appointment object from given form data
     * @throws ParseException Thrown if timestamp string not in the correct format
     */
    public static Appointments createApptFromFormData(HashMap<String, String> appointmentsFormData) throws ParseException {
        Date now = calendar.getTime();
        Timestamp currentTimestamp = new Timestamp(now.getTime());

        return new Appointments(Integer.parseInt(appointmentsFormData.get("appointment ID")), appointmentsFormData.get("title"),
                appointmentsFormData.get("description"), appointmentsFormData.get("location"), appointmentsFormData.get("type"),
                convertFromStringToCal(appointmentsFormData.get("start")), convertFromStringToCal(appointmentsFormData.get("end")), currentTimestamp, user.get(1),
                currentTimestamp, user.get(1), Integer.parseInt(appointmentsFormData.get("customer ID")),
                Integer.parseInt(appointmentsFormData.get("user ID")), getContact(appointmentsFormData.get("contact name")).getContactID(), appointmentsFormData.get("contact name"));
    }

    /**
     * Check given form data for basic correctness and error checking
     * @param formData1 Hash map of form data from appointment scene or customer scene
     * @return String representing the status of if all the form data is in the proper format
     */
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
//                    if(overlapAppt(key ,value)) {
//
//                    } else {
//                        formStatus.append(localeRB.getString("erOverlap"));
//                    }
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

    /**
     * return the proper description of certain keys for internationalizaion purposes
     * @param key Input string from key of the form data
     * @return String representing key in the proper format
     */
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

    /**
     * Return timezone of current user
     * @return Return timezone of current user
     */
    public static String getCurrentLocal() {
        return calendar.getTimeZone().toZoneId().getDisplayName(TextStyle.FULL, Locale.getDefault());
    }
}
