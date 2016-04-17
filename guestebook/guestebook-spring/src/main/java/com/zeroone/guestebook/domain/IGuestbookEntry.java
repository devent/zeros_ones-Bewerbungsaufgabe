package com.zeroone.guestebook.domain;

import java.util.Date;

/**
 * Guestbook entry fields.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
public interface IGuestbookEntry {

    Date getDate();

    Long getNumber();

    String getAuthor();

    String getComment();
}
