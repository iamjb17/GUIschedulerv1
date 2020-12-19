/**
 * @author Jessie Burton
 */
package Scheduler;

import java.sql.Timestamp;

// Model Class for Country data
public class Country {
    private int countryID;
    private String countryName;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;

    // Contructor for Country model class
    public Country(int countryID, String countryName, Timestamp createdDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy) {
        this.countryID = countryID;
        this.countryName = countryName;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    // return Country ID
    public int getCountryID() {
        return countryID;
    }

    // Return Country Name
    public String getCountryName() {
        return countryName;
    }

    // Return created date
    public Timestamp getCreatedDate() {
        return createdDate;
    }

}
