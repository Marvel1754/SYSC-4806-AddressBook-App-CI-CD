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
 * @version 2025-10-22
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
        List<AddressBook> _addbkList = repository.findByName(name);
        if (!_addbkList.isEmpty()) {
            AddressBook addressBook = new AddressBook(name);
            repository.save(addressBook);
        }
        List<AddressBook> createdAddressBook = repository.findByName(name);
        return createdAddressBook.get(0);
    }

    @GetMapping("/add")
    public BuddyInfo add(@RequestParam String addressBookName,
                           @RequestParam String firstname,
                           @RequestParam String lastname,
                           @RequestParam Long contactNumber,
                           @RequestParam(defaultValue = "No Address", required = false) String address) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook = temp.get(0);
        BuddyInfo newBuddy = new BuddyInfo(firstname, lastname, contactNumber, address);
        addressBook.addBuddy(newBuddy);
        repository.save(addressBook);
        return newBuddy;
    }

    @GetMapping("/remove")
    public BuddyInfo remove(@RequestParam String addressBookName, @RequestParam() Long id) {
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

    @GetMapping("/buddies")
    public List<BuddyInfo> buddies(@RequestParam String addressBookName) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook = temp.get(0);
        return addressBook.getBuddies();
    }
}
