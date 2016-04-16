package com.zeroone.guestebook.domain;

import java.util.Date;

public interface IGuestbookEntry {

    Date getDate();

    Long getNumber();

    String getAuthor();

    String getComment();
}
