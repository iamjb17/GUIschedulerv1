/**
 * @author Jessie Burton
 */
package Scheduler;

import java.sql.Timestamp;
import java.util.Calendar;

// Model for Appointments
public class Appointments {
    private int AppointmentID;
    private String title;
    private String description;
    private String Location;
    private String type;
    private Calendar start;
    private Calendar end;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private int customerID;
    private int userID;
    private int contactID;
    private String contactName;

    // Constructor for Appointments
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

    // return contact name
    public String getContactName() {
        return contactName;
    }

    // set contact name
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    // return appointment ID
    public int getAppointmentID() {
        return AppointmentID;
    }

    // set appointment ID
    public void setAppointmentID(int appointmentID) {
        AppointmentID = appointmentID;
    }

    // return appointment title
    public String getTitle() {
        return title;
    }

    // set appointment title
    public void setTitle(String title) {
        this.title = title;
    }

    // return appointment description
    public String getDescription() {
        return description;
    }

    // set appointment description
    public void setDescription(String description) {
        this.description = description;
    }

    // return appointment location
    public String getLocation() {
        return Location;
    }

    // set appointment location
    public void setLocation(String location) {
        Location = location;
    }

    // return appointment type
    public String getType() {
        return type;
    }

    // set appointment type
    public void setType(String type) {
        this.type = type;
    }

    // return appointment start time
    public Calendar getStart() {
        return start;
    }

    // set appointment start time
    public void setStart(Calendar start) {
        this.start = start;
    }

    // return appointment end time
    public Calendar getEnd() {
        return end;
    }

    // set appointment end time
    public void setEnd(Calendar end) {
        this.end = end;
    }

    // return appointment create date
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    // set appointment created by date
    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    // return appointment created by name
    public String getCreatedBy() {
        return createdBy;
    }

    // set appointment created by name
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    // return appointment last updated date
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    // set appointment last updated timestamep
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    // return appointment last updated by name
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    // set appointment last updated by name
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    // return appointment customer ID
    public int getCustomerID() {
        return customerID;
    }

    // set appointment customer ID
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    // return appointment user ID
    public int getUserID() {
        return userID;
    }

    // set appointment user ID
    public void setUserID(int userID) {
        this.userID = userID;
    }

    // return appointment contact ID
    public int getContactID() {
        return contactID;
    }

    // set appointment contact ID
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

}
