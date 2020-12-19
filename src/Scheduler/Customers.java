/**
 * @author Jessie Burton
 */
package Scheduler;

import java.sql.Timestamp;

// Model Class for Customer data
public class Customers {
    private int customerID;
    private String name;
    private String address;
    private String postalCode;
    private String phone;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private int divisionID;
    private String state;
    private String country;

    // Constructor for customer model class
    public Customers(int customerID, String name, String address, String postalCode, String phone,
                     Timestamp createdDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy, int divisionID,
                     String state, String country) {
        this.customerID = customerID;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
        this.state = state;
        this.country = country;
    }

    // return state name of the customer
    public String getState() {
        return state;
    }

    // set state name of the customer
    public void setState(String state) {
        this.state = state;
    }

    // return country of the customer
    public String getCountry() {
        return country;
    }

    // set country of the customer
    public void setCountry(String country) {
        this.country = country;
    }

    // return customer ID
    public int getCustomerID() {
        return customerID;
    }

    // return customer name
    public String getName() {
        return name;
    }

    // set customer name
    public void setName(String name) {
        this.name = name;
    }

    // return address of customer
    public String getAddress() {
        return address;
    }

    // return postal code of customer
    public String getPostalCode() {
        return postalCode;
    }

    // set postal code
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    // return phone number of customer
    public String getPhone() {
        return phone;
    }

    // set phone number of customer
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // return create date
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    // return lasted updated date
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    // return last updated by name
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    // return division ID of custoemr
    public int getDivisionID() {
        return divisionID;
    }

    // return created by name
    public String getCreatedBy() {
        return createdBy;
    }

}
