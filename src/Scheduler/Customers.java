/**
 * @author Jessie Burton
 */
package Scheduler;

import java.sql.Timestamp;

/**
 * Model Class for Customer data
 */
public class Customers {

    /**
     * Stores unique int value representing customer ID
     */
    private final int customerID;
    /**
     * Stores string value representing customer name
     */
    private String name;
    /**
     * Stores string value representing customer address
     */
    private final String address;
    /**
     * Stores string value representing customer postal code
     */
    private String postalCode;
    /**
     * Stores string value representing customer phone number
     */
    private String phone;
    /**
     * Stores timestamp value representing when customer was initially created
     */
    private final Timestamp createdDate;
    /**
     * Stores string value representing who initially created the customer object
     */
    private final String createdBy;
    /**
     * Stores timestamp value representing when the customer object was last updated
     */
    private final Timestamp lastUpdated;
    /**
     * Stores string value representing who last updated the customer object
     */
    private final String lastUpdatedBy;
    /**
     * Stores int value representing the division id of the customer object
     */
    private final int divisionID;
    /**
     * Stores string value representing customer residing state
     */
    private String state;
    /**
     * Stores string value representing customer residing country
     */
    private String country;

    /**
     * Constructor for customer model class
     * @param customerID unique int value representing customer ID
     * @param name string value representing customer name
     * @param address string value representing customer address
     * @param postalCode string value representing customer postal code
     * @param phone string value representing customer phone number
     * @param createdDate timestamp value representing when customer was initially created
     * @param createdBy string value representing who initially created the customer object
     * @param lastUpdated timestamp value representing when the customer object was last updated
     * @param lastUpdatedBy string value representing who last updated the customer object
     * @param divisionID int value representing the division id of the customer object
     * @param state string value representing customer residing state
     * @param country string value representing customer residing country
     */
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

    /**
     * return state name of the customer
     * @return return state name of the customer
     */
    public String getState() {
        return state;
    }

    /**
     * set state name of the customer
     * @param state set state name of the customer
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * return country of the customer
     * @return return country of the customer
     */
    public String getCountry() {
        return country;
    }

    /**
     * set country of the customer
     * @param country set country of the customer
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * return customer ID
     * @return return customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * return customer name
     * @return return customer name
     */
    public String getName() {
        return name;
    }

    /**
     * set customer name
     * @param name set customer name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * return address of customer
     * @return return address of customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * return postal code of customer
     * @return return postal code of customer
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * set postal code
     * @param postalCode set postal code
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * return phone number of customer
     * @return return phone number of customer
     */
    public String getPhone() {
        return phone;
    }

    /**
     * set phone number of customer
     * @param phone set phone number of customer
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * return create date
     * @return return create date
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * return lasted updated date
     * @return return lasted updated date
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * return last updated by name
     * @return return last updated by name
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * return division ID of customer
     * @return return division ID of customer
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * return created by name
     * @return return created by name
     */
    public String getCreatedBy() {
        return createdBy;
    }

}
