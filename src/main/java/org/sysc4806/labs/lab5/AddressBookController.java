package org.sysc4806.labs.lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The AddressBookController class handles the HTTP requests.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-10-23
 */
@RestController
public class AddressBookController {
    private AddressBookManagerService aBkManagerService;

    @Autowired
    public AddressBookController(AddressBookManagerService addressBookManagerService) {
        this.aBkManagerService = addressBookManagerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "This is an exploration of the RESTful capabilities of spring.";
    }

    @GetMapping("/addressbooks")
    public List<AddressBook> addressBooks() {
        return aBkManagerService.getAddressBooks();
    }

    @GetMapping("/create")
    public AddressBook create(@RequestParam(defaultValue = "New AddressBook") String name) {
        return aBkManagerService.createAddressBook(name);
    }

    @GetMapping("/add")
    public BuddyInfo add(@RequestParam String addressBookName,
                           @RequestParam String firstname,
                           @RequestParam String lastname,
                           @RequestParam Long contactNumber,
                           @RequestParam(defaultValue = "No Address", required = false) String address) {
        return aBkManagerService.addBuddyToAddressBook(addressBookName, firstname, lastname, contactNumber, address);
    }

    @GetMapping("/remove")
    public BuddyInfo remove(@RequestParam String addressBookName, @RequestParam() Long id) {
        return aBkManagerService.removeBuddyFromAddressBook(addressBookName, id);
    }

    @GetMapping("/buddies")
    public List<BuddyInfo> buddies(@RequestParam String addressBookName) {
        return aBkManagerService.getBuddiesInAddressBook(addressBookName);
    }
}
