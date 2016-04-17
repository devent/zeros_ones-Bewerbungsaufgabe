/*
 * Copyright 2016 Erwin Müller <erwin.mueller@deventm.org>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zeroone.guestebook.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * A guestbook entry.
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@SuppressWarnings("serial")
@Entity
public class GuestbookEntry implements Serializable, IGuestbookEntry {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private final Date date;

    @Column(nullable = false)
    private final String author;

    @Column(nullable = false)
    private final String comment;

    public GuestbookEntry(String author, String comment) {
        this.date = new Date();
        this.author = author;
        this.comment = comment;
    }

    /**
     * Default ctor for Pojo.
     */
    @SuppressWarnings("unused")
    private GuestbookEntry() {
        this.date = new Date();
        this.author = null;
        this.comment = null;
    }

    @Override
    public Date getDate() {
        return date;
    }

    public Long getId() {
        return id;
    }

    @Override
    public Long getNumber() {
        return id;
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
