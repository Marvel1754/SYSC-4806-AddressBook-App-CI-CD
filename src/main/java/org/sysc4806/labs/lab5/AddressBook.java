package org.sysc4806.labs.lab5;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import java.util.ArrayList;
import java.util.List;

/**
 * The AddressBook class holds the contact information for a list of buddies.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-09-13
 */
@Entity
public class AddressBook {
    private long id;
    private String name;
    private List<BuddyInfo> buddies;

    /**
     * Default constructor for the AddressBook.
     */
    public AddressBook() {
        name = "Default AddressBook";
        buddies = new ArrayList<>();
    }

    /**
     * Constructs a new AddressBook object.
     * @param name The name of the address book.
     */
    public AddressBook(String name) {
        this.name = name;
        this.buddies = new ArrayList<>();
    }

    /**
     * Adds new buddy information to the address book.
     * @param buddy The new buddy to add.
     */
    public void addBuddy(BuddyInfo buddy) {
        buddies.add(buddy);
    }

    /**
     * Gets the id of the AddressBook.
     * @return The id of the AddressBook.
     */
    @Id
    @GeneratedValue
    public long getId() {
        return id;
    }

    /**
     * Gets the name of the Address book.
     * @return The name of the Address book.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the list of buddies in the address book.
     * @return The list of buddies in the address book.
     */
    @OneToMany(cascade = CascadeType.ALL)
    public List<BuddyInfo> getBuddies() {
        return buddies;
    }

    /**
     * Sets the id of the AddressBook.
     * @param id The new id of the AddressBook.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Sets the name of the AddressBook.
     * @param name The new name of the AddressBook.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the list of buddies in the address book.
     * @param buddies The list of buddies to be set.
     */
    public void setBuddies(List<BuddyInfo> buddies) {
        this.buddies = buddies;
    }

    /**
     * Prints out the address book for visibility.
     */
    public void printBook() {
        for (BuddyInfo buddy : buddies) {
            System.out.println(buddy);
            System.out.println();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("AddressBook: " + name + "\n");
        for (BuddyInfo buddy : buddies) {
            str.append(buddy);
            str.append("\n");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook("AddressBook1");
        addressBook.addBuddy(new BuddyInfo("Marvel", "Adotse-ogah", 13432623063L));
        addressBook.addBuddy(new BuddyInfo("Peter", "Parker", 3232824453L));
        addressBook.addBuddy(new BuddyInfo("Peter", "Benson", 3232812453L));
        addressBook.printBook();
    }
}
