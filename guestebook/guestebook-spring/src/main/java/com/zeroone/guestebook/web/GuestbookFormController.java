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
package com.zeroone.guestebook.web;

import static org.springframework.util.Assert.notNull;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zeroone.guestebook.domain.GuestbookEntriesRepository;
import com.zeroone.guestebook.domain.GuestbookEntry;

/**
 * Handles the following web requests to create a new guestbook entry.
 * <ul>
 * <li>/new
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@Controller
public class GuestbookFormController {

    private final static Logger log = LoggerFactory
            .getLogger(GuestbookFormController.class);

    private final GuestbookEntriesRepository guestbook;

    @Autowired
    public GuestbookFormController(GuestbookEntriesRepository guestbook) {
        notNull(guestbook, "guestbook==null");
        this.guestbook = guestbook;
    }

    /**
     * "/new"
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    String newForm(Model model, GuestbookEntryForm form) {
        model.addAttribute("entries", guestbook.findAll());
        model.addAttribute("form", form);
        return "newform";
    }

    /**
     * "/new", adds a {@link GuestbookEntry} from the {@link GuestbookEntryForm}
     * via POST request.
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    String addEntry(@Valid GuestbookEntryForm form, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return newForm(model, form);
        }
        GuestbookEntry entry;
        entry = new GuestbookEntry(form.getAuthor(), form.getComment());
        log.trace("Saving entry: {}", entry);
        guestbook.save(entry);
        log.debug("Entry saved: {}", entry);
        return "redirect:/guestbook";
    }
}
