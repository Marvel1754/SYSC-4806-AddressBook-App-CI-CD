package org.sysc4806.labs.lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * The AddressBookGUIViewController class responds with viable html content.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-09-29
 */
@Controller
@RequestMapping("/view")
public class AddressBookGUIViewController {
    private AddressBookRepository repository;

    @Autowired
    public AddressBookGUIViewController(AddressBookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/addressbooks")
    public String addressBooks(Model model) {
        List<AddressBook> addressBookList = repository.findAll();
        model.addAttribute("addressBooks", addressBookList);
        return "addressbooks";
    }

    @GetMapping("/create-addressbook-form")
    public String createAddressBookForm(Model model) {
        return "create-addressbook-form";
    }

    @GetMapping("/create")
    public String createAddressBook(@RequestParam(defaultValue = "New AddressBook") String name, Model model) {
        List<AddressBook> _addbkList = repository.findByName(name);
        if (!_addbkList.isEmpty()) {
            List<AddressBook> addressBookList = repository.findAll();
            model.addAttribute("addressBooks", addressBookList);
            return "addressbooks";
        }

        AddressBook addressBook = new AddressBook(name);
        repository.save(addressBook);

        List<AddressBook> addressBookList = repository.findAll();
        model.addAttribute("addressBooks", addressBookList);
        return "addressbooks";
    }

    @GetMapping("/add-buddy-form")
    public String addBuddyForm(Model model) {
        model.addAttribute("addressbookNames", repository.findAll());
        return "add-buddy-form";
    }

    @GetMapping("/add")
    public String add(@RequestParam String addressBookName,
                      @RequestParam String firstname,
                      @RequestParam String lastname,
                      @RequestParam Long contactNumber,
                      @RequestParam(defaultValue = "No Address", required = false) String address, Model model) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook;
        if (temp.size() == 1) {
            addressBook = temp.get(0);
            addressBook.addBuddy(new BuddyInfo(firstname, lastname, contactNumber, address));
            repository.save(addressBook);
        } else {
            return null;
        }
        model.addAttribute("addressBook", addressBook.getName());
        model.addAttribute("buddies", addressBook.getBuddies());
        return "buddies";
    }

    @GetMapping("/remove-buddy-form")
    public  String removeBuddyForm(Model model) {
        return "remove-buddy-form";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String addressBookName, @RequestParam Long id, Model model) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook;
        if (temp.size() == 1) {
            addressBook = temp.get(0);
        } else {
            return null;
        }
        addressBook.getBuddies().removeIf(p -> p.getId() == id);
        repository.save(addressBook);
        model.addAttribute("addressBook", addressBook.getName());
        model.addAttribute("buddies", addressBook.getBuddies());
        return "buddies";
    }

    @GetMapping("/buddies-form")
    public String buddiesForm(Model model) {
        model.addAttribute("addressbookNames", repository.findAll());
        return "buddies-form";
    }

    @GetMapping("/buddies")
    public String buddies(@RequestParam String addressBookName, Model model) {
        List<AddressBook> temp = repository.findByName(addressBookName);
        AddressBook addressBook;
        if (temp.size() == 1) {
            addressBook = temp.get(0);
        } else {
            return null;
        }
        model.addAttribute("addressBook", addressBook.getName());
        model.addAttribute("buddies", addressBook.getBuddies());
        return "buddies";
    }
}
