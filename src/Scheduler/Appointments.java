/**
 * @author Jessie Burton
 */
package Scheduler;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Model for Appointments objects
 */
public class Appointments {
    /**
     * Stores unique int value representing appointment ID
     */
    private int AppointmentID;
    /**
     * Stores String value representing appointment title
     */
    private String title;
    /**
     * Stores String value representing appointment description
     */
    private String description;
    /**
     * Stores String value representing appointment location
     */
    private String Location;
    /**
     * Stores String value representing appointment type
     */
    private String type;
    /**
     * Stores Calendar value representing appointment start time and date
     */
    private Calendar start;
    /**
     * Stores Calendar value representing appointment end time and date
     */
    private Calendar end;
    /**
     * Stores Timestamp value representing appointment create time and date
     */
    private Timestamp createdDate;
    /**
     * Stores String value representing who created the appointment
     */
    private String createdBy;
    /**
     * Stores Timestamp value representing when appointment was last updated
     */
    private Timestamp lastUpdated;
    /**
     * Stores String value representing who last updated the appointment data
     */
    private String lastUpdatedBy;
    /**
     * Stores Int value representing the appointments customer ID
     */
    private int customerID;
    /**
     * Stores int value representing the  appointments user ID
     */
    private int userID;
    /**
     * Stores int value representing the appointments contact ID
     */
    private int contactID;
    /**
     * Stores String value representing appointment contact name
     */
    private String contactName;

    /**
     * Constructor for Appointments
     * @param appointmentID unique int value representing appointment ID
     * @param title String value representing appointment title
     * @param description String value representing appointment description
     * @param location String value representing appointment location
     * @param type String value representing appointment type
     * @param start Calendar value representing appointment start time and date
     * @param end Calendar value representing appointment end time and date
     * @param createdDate Timestamp value representing appointment create time and date
     * @param createdBy String value representing who created the appointment
     * @param lastUpdated Timestamp value representing appointment last updated time and date
     * @param lastUpdatedBy String value representing who last updated the appointment
     * @param customerID Int value representing the appointments customer ID
     * @param userID Int value representing the appointments user ID
     * @param contactID Int value representing the appointments contact ID
     * @param contactName String value representing appointment contact name
     */
    public Appointments(int appointmentID, String title, String description, String location, String type, Calendar start, Calendar end, Timestamp createdDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int customerID, int userID, int contactID, String contactName) {
        AppointmentID = appointmentID;
        this.title = title;
        this.description = description;
        Location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
        this.contactName = contactName;
    }

    /**
     * return contact name
     * @return return contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * sets contact name
     * @param contactName sets contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * return appt ID
     * @return return appt ID
     */
    public int getAppointmentID() {
        return AppointmentID;
    }

    /**
     * sets appointment ID
     * @param appointmentID sets appointment ID
     */
    public void setAppointmentID(int appointmentID) {
        AppointmentID = appointmentID;
    }

    /**
     * return appointment title
     * @return return appointment title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set appointment title
     * @param title set appointment title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * return appointment description
     * @return return appointment description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set appointment description
     * @param description set appointment description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * return appointment location
     * @return return appointment location
     */
    public String getLocation() {
        return Location;
    }

    /**
     * set appointment location
     * @param location set appointment location
     */
    public void setLocation(String location) {
        Location = location;
    }

    /**
     * return appointment type
     * @return return appointment type
     */
    public String getType() {
        return type;
    }

    /**
     * set appointment type
     * @param type set appointment type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * return appointment start time
     * @return return appointment start time
     */
    public Calendar getStart() {
        return start;
    }

    /**
     * set appointment start time
     * @param start set appointment start time
     */
    public void setStart(Calendar start) {
        this.start = start;
    }

    /**
     * return appointment end time
     * @return return appointment end time
     */
    public Calendar getEnd() {
        return end;
    }

    /**
     * Calendar value to set appointment end time
     * @param end Calendar value to set appointment end time
     */
    public void setEnd(Calendar end) {
        this.end = end;
    }

    /**
     * return appointment create date
     * @return return appointment create date
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * Timestamp value to set appointment created by date
     * @param createdDate Timestamp value to set appointment created by date
     */
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * return appointment created by name
     * @return return appointment created by name
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * String value to set appointment created by name
     * @param createdBy String value to set appointment created by name
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * return appointment last updated date
     * @return return appointment last updated date
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * Timestamp value to set appointment last updated value
     * @param lastUpdated Timestamp value to set appointment last updated value
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * return appointment last updated by name
     * @return return appointment last updated by name
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * String value to set appointment last updated by name
     * @param lastUpdatedBy String value to set appointment last updated by name
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * return appointment customer ID
     * @return return appointment customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * int value to set appointment customer ID
     * @param customerID int value to set appointment customer ID
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * return appointment user ID
     * @return return appointment user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * int value to set appointment user ID
     * @param userID int value to set appointment user ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * return appointment contact ID
     * @return return appointment contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * int value to set appointment contact ID
     * @param contactID int value to set appointment contact ID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

}
