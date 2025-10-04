package org.sysc4806.labs.lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;;

import java.util.List;

/**
 * The AddressBookController class handles the HTTP requests.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-09-29
 */
@RestController
public class AddressBookController {
    private AddressBookRepository repository;

    @Autowired
    public AddressBookController(AddressBookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "This is an exploration of the RESTful capabilities of spring.";
    }

    @GetMapping("/addressbooks")
    public List<AddressBook> addressBooks() {
        return repository.findAll();
    }

    @GetMapping("/create")
    public AddressBook create(@RequestParam(defaultValue = "New AddressBook") String name) {
        AddressBook addressBook = new AddressBook(name);
        repository.save(addressBook);
        return addressBook;
    }

    @GetMapping("/add")
    public AddressBook add(@RequestParam String addressBookName, @RequestParam String firstname, @RequestParam String lastname, @RequestParam Long contactNumber, @RequestParam(defaultValue = "No Address", required = false) String address) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook;
        if (temp.size() == 1) {
            addressBook = temp.get(0);
            addressBook.addBuddy(new BuddyInfo(firstname, lastname, contactNumber, address));
            repository.save(addressBook);
        } else {
            return null;
        }
        return addressBook;
    }

    @GetMapping("/remove")
    public AddressBook remove(@RequestParam String addressBookName, @RequestParam() Long id) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook;
        if (temp.size() == 1) {
            addressBook = temp.get(0);
        } else {
            return null;
        }
        addressBook.getBuddies().removeIf(p -> p.getId() == id);
        repository.save(addressBook);
        return addressBook;
    }
}
