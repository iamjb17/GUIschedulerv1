/**
 * @author Jessie Burton
 */
package Scheduler;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Model class for User
 */
public class Users {

    /**
     * Stores unique int value representing user ID
     */
    private int userID;
    /**
     * Stores String value representing user name
     */
    private String userName;
    /**
     * Stores String value representing user password
     */
    private String password;
    /**
     * Stores LocalDateTime value representing user create date
     */
    private LocalDateTime createdDate;
    /**
     * Stores String value representing user create by
     */
    private String createdBy;
    /**
     * Stores timestamp value representing when user was last updated
     */
    private Timestamp lastUpdated;
    /**
     * Stores string value of who last updated the user
     */
    private String lastUpdatedBy;

    /**
     * Constructor for user model class
     * @param userID unique int value representing user ID
     * @param userName String value representing user name
     * @param password String value representing user password
     * @param createdDate LocalDateTime value representing user create date
     * @param createdBy String value representing user create by
     * @param lastUpdated timestamp value representing when user was last updated
     * @param lastUpdatedBy string value of who last updated the user
     */
    public Users(int userID, String userName, String password, LocalDateTime createdDate, String createdBy, Timestamp lastUpdated, String lastUpdatedBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.lastUpdated = lastUpdated;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * returns user ID
     * @return returns user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * sets user ID
     * @param userID sets user ID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * returns user name
     * @return returns user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets user name
     * @param userName sets user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * returns user password
     * @return returns user password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets user password
     * @param password sets user password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * returns user created date
     * @return returns user created date
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * sets user created date
     * @param createdDate sets user created date
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * returns who created the user
     * @return returns who created the user
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * sets who created the user
     * @param createdBy sets who created the user
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * returns last updated timestamp
     * @return returns last updated timestamp
     */
    public Timestamp getLastUpdated() {
        return lastUpdated;
    }

    /**
     * sets last updated time
     * @param lastUpdated sets last updated time
     */
    public void setLastUpdated(Timestamp lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     * returns who last updated the user
     * @return returns who last updated the user
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * sets who last updated the user
     * @param lastUpdatedBy sets who last updated the user
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }
}
