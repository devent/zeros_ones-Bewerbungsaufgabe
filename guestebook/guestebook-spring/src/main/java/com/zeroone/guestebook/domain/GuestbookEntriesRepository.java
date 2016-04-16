package com.zeroone.guestebook.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Access {@link GuestbookEntry}.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public interface GuestbookEntriesRepository extends
        CrudRepository<GuestbookEntry, Long> {

    Page<GuestbookEntry> findAll(Pageable pageable);

}
