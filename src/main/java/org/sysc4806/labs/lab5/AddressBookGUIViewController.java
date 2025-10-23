package org.sysc4806.labs.lab5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The AddressBookGUIViewController class responds with viable html content.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-10-23
 */
@Controller
@RequestMapping("/view")
public class AddressBookGUIViewController {
    private AddressBookManagerService aBkManagerService;

    @Autowired
    public AddressBookGUIViewController(AddressBookManagerService addressBookManagerService) {
        this.aBkManagerService = addressBookManagerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/addressbooks")
    public String addressBooks(Model model) {
        model.addAttribute("addressBooks", aBkManagerService.getAddressBooks());
        return "addressbooks";
    }

    @GetMapping("/create-addressbook-form")
    public String createAddressBookForm(Model model) {
        return "create-addressbook-form";
    }

    @GetMapping("/create")
    public String createAddressBook(@RequestParam(defaultValue = "New AddressBook") String name, Model model) {
        aBkManagerService.createAddressBook(name);
        model.addAttribute("addressBooks", aBkManagerService.getAddressBooks());
        return "addressbooks";
    }

    @GetMapping("/add-buddy-form")
    public String addBuddyForm(Model model) {
        model.addAttribute("addressbookNames", aBkManagerService.getAddressBooks());
        return "add-buddy-form";
    }

    @GetMapping("/add")
    public String add(@RequestParam String addressBookName,
                      @RequestParam String firstname,
                      @RequestParam String lastname,
                      @RequestParam Long contactNumber,
                      @RequestParam(defaultValue = "No Address", required = false) String address, Model model) {
        aBkManagerService.addBuddyToAddressBook(addressBookName, firstname, lastname, contactNumber, address);
        AddressBook addressBook = aBkManagerService.getAddressBook(addressBookName);
        model.addAttribute("addressBook", addressBook.getName());
        model.addAttribute("buddies", addressBook.getBuddies());
        return "buddies";
    }

    @GetMapping("/remove-buddy-form")
    public  String removeBuddyForm(Model model) {
        model.addAttribute("addressbookNames", aBkManagerService.getAddressBooks());
        return "remove-buddy-form";
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String addressBookName, @RequestParam Long id, Model model) {
        aBkManagerService.removeBuddyFromAddressBook(addressBookName, id);
        AddressBook addressBook = aBkManagerService.getAddressBook(addressBookName);
        model.addAttribute("addressBook", addressBook.getName());
        model.addAttribute("buddies", addressBook.getBuddies());
        return "buddies";
    }

    @GetMapping("/buddies-form")
    public String buddiesForm(Model model) {
        model.addAttribute("addressbookNames", aBkManagerService.getAddressBooks());
        return "buddies-form";
    }

    @GetMapping("/buddies")
    public String buddies(@RequestParam String addressBookName, Model model) {
        AddressBook addressBook = aBkManagerService.getAddressBook(addressBookName);
        model.addAttribute("addressBook", addressBook.getName());
        model.addAttribute("buddies", addressBook.getBuddies());
        return "buddies";
    }
}
