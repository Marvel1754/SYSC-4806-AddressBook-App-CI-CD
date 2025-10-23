package org.sysc4806.labs.lab5;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

/**
 * The BuddyInfo class holds the information for a buddy.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-09-13
 */
@Entity
public class BuddyInfo {
    private long id;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String address;

    /**
     * Default constructor for the BuddyInfo class.
     */
    public BuddyInfo() {
        this.firstname = "John";
        this.lastname = "Doe";
        this.phoneNumber = verifyNumber(1234567890L);
        this.address = "Default address";
    }

    /**
     * The constructor of the BuddyInfo class.
     * @param firstname The first name of the Buddy.
     * @param lastname The last name of the Buddy.
     * @param phoneNumber The phone number of the Buddy.
     */
    public BuddyInfo(String firstname, String lastname, long phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = verifyNumber(phoneNumber);
        this.address = "No Address";
    }

    /**
     * The constructor of the BuddyInfo class
     * @param firstname The first name of the Buddy.
     * @param lastname The last name of the Buddy.
     * @param phoneNumber The phone number of the Buddy.
     * @param address The address of the Buddy.
     */
    public BuddyInfo(String firstname, String lastname, long phoneNumber, String address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = verifyNumber(phoneNumber);
        this.address = address;
    }

    /**
     * Verifies the phone number of the Buddy.
     * @param number The phone number of the Buddy.
     * @return The phone number of the Buddy.
     */
    private String verifyNumber(long number) {
        String temp = Long.toString(number);
        if (temp.length() < 10 || temp.length() > 11) {
            throw new IllegalArgumentException("Number must be between 10 and 11 numbers: no space and/or special characters");
        } else if (temp.length() == 11 && temp.charAt(0) != '1') {
            throw new IllegalArgumentException("11 digit Canadian number must start with '1'");
        }
        return temp;
    }

    /**
     * Gets the name of the Buddy.
     * @return The name of the Buddy.
     */
    public String fullName() {
        return firstname + " " + lastname;
    }

    /**
     * Gets the firstname of the Buddy.
     * @return The firstname of the Buddy.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Gets the lastname of the Buddy.
     * @return The lastname of the Buddy.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Gets the phone number of the Buddy.
     * @return The phone number of the Buddy.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Gets the address of the Buddy.
     * @return The address of the Buddy.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the id of the buddy.
     * @return The id of the buddy.
     */
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    /**
     * Sets the id of the buddy.
     * @param id The new id of the buddy.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the firstname of the Buddy.
     * @param firstname The firstname of the Buddy.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Set the lastname of the Buddy.
     * @param lastname The lastname of the Buddy.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Sets the phone number of the Buddy.
     * @param phoneNumber The phone number of the Buddy.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets the address of the Buddy.
     * @param address The address of the Buddy.
     */
    public void setAddress(String address) {this.address = address;}

    public String toString() {
        String temp;
        if (phoneNumber.length() == 11) {
            temp = String.format("+%s (%s) %s-%s", phoneNumber.charAt(0), phoneNumber.substring(1, 4), phoneNumber.substring(4, 7), phoneNumber.substring(7, 11));
        } else {
            temp = String.format("(%s) %s-%s", phoneNumber.substring(0, 3), phoneNumber.substring(3, 6), phoneNumber.substring(6, 10));
        }
        return String.format("  Name: %s\nNumber: %s", fullName(), temp);
    }
}
