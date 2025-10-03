package org.sysc4806.labs.lab5;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The BuddyInfoRepository interface helps spring handle CRUD operations.
 *
 * @author Marvel Adotse-ogah
 * @version 2025-09-22
 */
public interface BuddyInfoRepository extends CrudRepository<BuddyInfo, Long> {
    List<BuddyInfo> findByLastname(String lastname);
    List<BuddyInfo> findByFirstname(String firstname);
}
