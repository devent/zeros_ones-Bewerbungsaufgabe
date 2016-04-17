package com.zeroone.guestebook.web;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Guestbook entry form.
 * 
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
interface GuestbookEntryForm {

	@NotBlank
	String getAuthor();

	@NotBlank
	String getComment();
}
