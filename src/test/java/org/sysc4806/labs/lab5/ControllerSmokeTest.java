package org.sysc4806.labs.lab5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {AddressBookGUIViewController.class, AddressBookController.class, AddressBookApp.class})
public class ControllerSmokeTest {
    @Autowired
    private AddressBookController controller;

    @Autowired
    private AddressBookGUIViewController guiController;

    @Test
    public void contextLoads() {
        assertThat(controller).isNotNull();
        assertThat(guiController).isNotNull();
    }
}
