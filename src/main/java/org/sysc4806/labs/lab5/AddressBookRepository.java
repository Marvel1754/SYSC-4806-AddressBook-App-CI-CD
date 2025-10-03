package org.sysc4806.labs.lab5;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The AddressBookRepository interface helps spring handle CRUD operations.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-09-22
 */
public interface AddressBookRepository extends CrudRepository<AddressBook, Long> {
    List<AddressBook> findByName(String name);
    List<AddressBook> findAll();

}
