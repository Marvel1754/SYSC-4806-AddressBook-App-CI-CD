package org.sysc4806.labs.lab5;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuddyInfoTest {

    @Test
    public void testCreateBuddy() {
        assertThrows(IllegalArgumentException.class, () -> {new BuddyInfo("John", "Doe", 123456789L);});
        assertThrows(IllegalArgumentException.class, () -> {new BuddyInfo("John", "Doe", 123456789012L);});
        assertThrows(IllegalArgumentException.class, () -> {new BuddyInfo("John", "Doe", 42345678901L);});

        BuddyInfo buddyInfo1 = new BuddyInfo("John", "Doe", 1234567890L);
        assertEquals("John Doe", buddyInfo1.fullName());
        assertEquals("1234567890", buddyInfo1.getPhoneNumber());

        BuddyInfo buddyInfo2 = new BuddyInfo("John", "Doe", 12345678901L);
        assertEquals("John Doe", buddyInfo2.fullName());
        assertEquals("12345678901", buddyInfo2.getPhoneNumber());
    }

    @Test
    public void testToString() {
        BuddyInfo buddyInfo = new BuddyInfo("John", "Doe", 1234567890L);
        assertTrue(buddyInfo.toString().contains("John Doe"));
        assertTrue(buddyInfo.toString().contains("Name:"));
        assertTrue(buddyInfo.toString().contains("Number:"));
        assertTrue(buddyInfo.toString().contains("(123) 456-7890"));

        BuddyInfo buddyInfo2 = new BuddyInfo("Jane", "Doe", 12345678901L);
        assertTrue(buddyInfo2.toString().contains("Jane Doe"));
        assertTrue(buddyInfo2.toString().contains("Name:"));
        assertTrue(buddyInfo2.toString().contains("Number:"));
        assertTrue(buddyInfo2.toString().contains("+1 (234) 567-8901"));
    }
}