/**
 * @author Jessie Burton
 */
package Scheduler;

// Model class for contacts
public class Contacts {
    private int contactID;
    private String contactName;
    private String email;

    // Constructor for contact model class
    public Contacts(int contactID, String contactName, String email) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.email = email;
    }

    // return contact ID
    public int getContactID() {
        return contactID;
    }

    // set Contact ID
    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    // return contact name
    public String getContactName() {
        return contactName;
    }

    // set contact name
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
}
