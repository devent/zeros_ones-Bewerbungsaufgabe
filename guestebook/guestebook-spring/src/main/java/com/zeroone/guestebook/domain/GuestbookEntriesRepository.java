package com.zeroone.guestebook.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Access {@link GuestbookEntry}.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@Repository
public interface GuestbookEntriesRepository extends
        CrudRepository<GuestbookEntry, Long> {

    Page<GuestbookEntry> findAll(Pageable pageable);

}
