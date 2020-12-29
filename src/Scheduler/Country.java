/**
 * @author Jessie Burton
 */
package Scheduler;

import java.sql.Timestamp;

/**
 * Model Class for Country data
 */
public class Country {
    /**
     * Stores unique int value representing country ID
     */
    private final int countryID;
    /**
     * Stores string value representing country name
     */
    private final String countryName;
    /**
     * Stores timestamp value representing country object was first created
     */
    private final Timestamp createdDate;
    /**
     * Stores string value representing country created by name
     */
    private final String createdBy;
    /**
     * Stores timestamp value representing when country object was last updated
     */
    private final Timestamp lastUpdated;
    /**
     * Stores string value representing who last updated the country object
     */
    private final String lastUpdatedBy;

    /**
     * Constructor for Country model class
     * @param countryID unique int value representing country ID
     * @param countryName string value representing country name
     * @param createdDate timestamp value representing country object was first created
     * @param createdBy string value representing country created by name
     * @param lastUpdated timestamp value representing when country object was last updated
     * @param lastUpdatedBy string value representing who last updated the country object
     */
    public Country(int countryID, String countryName, Timestamp createdDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * return Country ID
     * @return return Country ID
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * Return Country Name
     * @return Return Country Name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Return created date
     * @return Return created date
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

}
