package org.sysc4806.labs.lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The AddressBookManagerService class handles all operations concerning managing an address book such
 * as making new buddy entries and creating and deleting address books.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-10-23
 */
@Service
public class AddressBookManagerService {
    private final AddressBookRepository repository;

    @Autowired
    public AddressBookManagerService(AddressBookRepository repository) {
        this.repository = repository;
    }

    /**
     * Gets an address book by its name.
     * @param name The name of the address book to retrieve.
     * @return The address book with the given name.
     */
    public AddressBook getAddressBook(String name) {
        AddressBook addressBook = repository.findByName(name).get(0);
        return addressBook;
    }

    /**
     * Gets all the address books available.
     * @return All the address books available.
     */
    public List<AddressBook> getAddressBooks() {
        return repository.findAll();
    }

    /**
     * Creates a new address book and returns the created address book.
     * @param name The name of the address book to be created.
     * @return The created address book.
     */
    public AddressBook createAddressBook(String name) {
        List<AddressBook> _addbkList = repository.findByName(name);
        if (_addbkList.isEmpty()) {
            AddressBook addressBook = new AddressBook(name);
            repository.save(addressBook);
        }
        List<AddressBook> createdAddressBook = repository.findByName(name);
        return createdAddressBook.get(0);
    }

    /**
     * Adds a buddy to an address book.
     * @param addressBookName The name of the address book to add the buddy.
     * @param firstname The first name of the buddy.
     * @param lastname The last name of the buddy.
     * @param contactNumber The phone number of the buddy.
     * @param address The address of the buddy.
     * @return The created buddy added to the address book.
     */
    public BuddyInfo addBuddyToAddressBook(String addressBookName,
                                           String firstname,
                                           String lastname,
                                           Long contactNumber,
                                           String address) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook = temp.get(0);
        BuddyInfo newBuddy = new BuddyInfo(firstname, lastname, contactNumber, address);
        addressBook.addBuddy(newBuddy);
        repository.save(addressBook);
        return newBuddy;
    }

    /**
     * Removes a buddy from an address book.
     * @param addressBookName The name of the address book to remove the buddy from.
     * @param id The id of the buddy to be removed.
     * @return The removed buddy.
     */
    public BuddyInfo removeBuddyFromAddressBook(String addressBookName, Long id) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook = temp.get(0);
        BuddyInfo removedBuddyInfo = new BuddyInfo();
        for (BuddyInfo buddy : addressBook.getBuddies()) {
            if (buddy.getId() == id) {
                removedBuddyInfo = buddy;
            }
        }
        addressBook.getBuddies().removeIf(p -> p.getId() == id);
        repository.save(addressBook);
        return removedBuddyInfo;
    }

    /**
     * Gets all the buddies in the address book given.
     * @param addressBookName The name of the address book to get the buddies from.
     * @return The buddies in the address book given.
     */
    public List<BuddyInfo> getBuddiesInAddressBook(String addressBookName) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook = temp.get(0);
        return addressBook.getBuddies();
    }
}
