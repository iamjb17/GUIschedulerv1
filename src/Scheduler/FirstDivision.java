/**
 * @author Jessie Burton
 */
package Scheduler;

import java.sql.Timestamp;

// Model class for first division data
public class FirstDivision {
    private int divisionID;
    private String divisionName;
    private Timestamp createdDate;
    private String createdBy;
    private Timestamp lastUpdated;
    private String lastUpdatedBy;
    private int countryID;

    // Constructor for first division model class
    public FirstDivision(int divisionID, String divisionName, Timestamp createdDate, String createdBy,
                         Timestamp lastUpdated, String lastUpdatedBy, int countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    // return division ID
    public int getDivisionID() {
        return divisionID;
    }

    // return division name
    public String getDivisionName() {
        return divisionName;
    }

    // return division created date
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    // return country ID of division
    public int getCountryID() {
        return countryID;
    }

}
