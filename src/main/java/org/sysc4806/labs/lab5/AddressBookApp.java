package org.sysc4806.labs.lab5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The AddressBookApp class is an application class for the Spring framework.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-09-29
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class AddressBookApp {

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApp.class, args);
    }
}
