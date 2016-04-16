package com.zeroone.guestebook.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

@SuppressWarnings("serial")
@Entity
public class GuestbookEntry implements Serializable, IGuestbookEntry {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private Long number;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String comment;

    @Override
    public Date getDate() {
        return date;
    }

    @Override
    public Long getNumber() {
        return number;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
