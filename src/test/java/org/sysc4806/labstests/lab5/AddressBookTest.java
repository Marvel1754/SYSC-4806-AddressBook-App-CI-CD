package org.sysc4806.labstests.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

import org.sysc4806.labs.lab5.AddressBook;
import org.sysc4806.labs.lab5.BuddyInfo;

public class AddressBookTest {
    @Test
    public void testAddBuddy() {
        AddressBook addressBook = new AddressBook("TestAddressBook1");
        BuddyInfo buddyInfo1 = new BuddyInfo("John", "Doe", 6134567890L);
        BuddyInfo buddyInfo2 = new BuddyInfo("Jane", "Doe", 16134547890L);

        addressBook.addBuddy(buddyInfo1);
        assertEquals(1, addressBook.getBuddies().size());
        addressBook.addBuddy(buddyInfo2);
        assertEquals(2, addressBook.getBuddies().size());
    }
}