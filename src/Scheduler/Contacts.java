/**
 * @author Jessie Burton
 */
package Scheduler;

/**
 * Model class for contacts
 */
public class Contacts {
    /**
     * Stores unique int value representing contact ID
     */
    private int contactID;
    /**
     * Stores string value representing contacts name
     */
    private String contactName;
    /**
     * Stores string value representing contacts email address
     */
    private final String email;

    /**
     * Constructor for contact model class
     * @param contactID unique int value representing contact ID
     * @param contactName string value representing contacts name
     * @param email string value representing contacts email address
     */
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    /**
     * return contact ID
     * @return return contact ID
     */
    public int getContactID() {
        return contactID;
    }

    /**
     * set Contact ID
     * @param contactID to set Contact ID
     */
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    /**
     * return contact name
     * @return return contact name
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * set contact name
     * @param contactName to set contact name
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
