/**
 * @author Jessie Burton
 */
package Scheduler;

import java.sql.Timestamp;

/**
 * Model class for first division data
 */
public class FirstDivision {

    /**
     * Stores unique int value representing division ID
     */
    private final int divisionID;
    /**
     * Stores string value representing division name
     */
    private final String divisionName;
    /**
     * Stores timestamp value representing date division was created
     */
    private final Timestamp createdDate;
    /**
     * Stores string value representing who created the first division
     */
    private final String createdBy;
    /**
     * Stores timestamp value representing when the first division was last updated
     */
    private final Timestamp lastUpdated;
    /**
     * Stores string value of who last updated the first division
     */
    private final String lastUpdatedBy;
    /**
     * Stores int value representing the first divisions country ID
     */
    private final int countryID;

    /**
     * Constructor for first division model class
     * @param divisionID unique int value representing division ID
     * @param divisionName string value representing division name
     * @param createdDate timestamp value representing date division was created
     * @param createdBy string value representing who created the first division
     * @param lastUpdated timestamp value representing when the first division was last updated
     * @param lastUpdatedBy string value of who last updated the first division
     * @param countryID int value representing the first divisions country ID
     */
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

    /**
     * return division ID
     * @return return division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

    /**
     * return division name
     * @return return division name
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * return division created date
     * @return return division created date
     */
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    /**
     * return country ID of division
     * @return return country ID of division
     */
    public int getCountryID() {
        return countryID;
    }

}
