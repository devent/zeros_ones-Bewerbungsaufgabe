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

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.Assert.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.zeroone.guestebook.domain.GuestbookEntriesRepository;
import com.zeroone.guestebook.domain.GuestbookEntry;

/**
 * Handles the following web requests:
 * <ul>
 * <li>/
 * <li>/questbook
 *
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@Controller
public class GuestbookController {

    /**
     * The entries per page.
     */
    private static final int PAGE_SIZE = 20;

    /**
     * Ajax header.
     */
    private static final String AJAX_HEADER = "X-Requested-With=XMLHttpRequest";

    private final GuestbookEntriesRepository guestbook;

    @Autowired
    public GuestbookController(GuestbookEntriesRepository guestbook) {
        notNull(guestbook, "guestbook is null");
        this.guestbook = guestbook;
    }

    /**
     * Application root, redirects to "/guestbook".
     */
    @RequestMapping("/")
    public String index() {
        return "redirect:/guestbook";
    }

    /**
     * "/guestbook", shows the page 0.
     */
    @RequestMapping(value = "/guestbook", method = RequestMethod.GET)
    public String guestbook(Model model) {
        Page<GuestbookEntry> page = getPage(0);
        model.addAttribute("firstActive", false);
        model.addAttribute("prevActive", false);
        model.addAttribute("nextActive", page.getTotalPages() - 1 > 0);
        model.addAttribute("lastActive", page.getTotalPages() - 1 > 0);
        model.addAttribute("page", page);
        return "guestbook";
    }

    /**
     * "/guestbook", shows the page with the specified page number.
     *
     * @param pageNumber
     *            the page number to show, starting with 0.
     */
    @RequestMapping(value = "/guestbook/{pageNumber}", method = RequestMethod.GET)
    public String getGuestbookPage(@PathVariable Integer pageNumber, Model model) {
        Page<GuestbookEntry> page = getPage(pageNumber);
        calculateButtonsActive(pageNumber, model, page);
        model.addAttribute("page", page);
        return "guestbook";
    }

    /**
     * "/guestbook", shows the page with the specified page number per Ajax
     * request.
     *
     * @param pageNumber
     *            the page number to show, starting with 0.
     */
    @RequestMapping(value = "/guestbook", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE, headers = AJAX_HEADER)
    public String getGuestbookPageAjax(
            @RequestParam("pageNumber") int pageNumber, Model model) {
        Page<GuestbookEntry> page = getPage(pageNumber);
        calculateButtonsActive(pageNumber, model, page);
        model.addAttribute("page", page);
        return "guestbook :: div#guestbook";
    }

    /**
     * "/guestbook", removes a {@link GuestbookEntry} specified by the ID.
     *
     * @param id
     *            the {@link GuestbookEntry} ID.
     */
    @RequestMapping(value = "/guestbook/{id}", method = RequestMethod.DELETE)
    public String removeEntry(@PathVariable Long id) {
        guestbook.delete(id);
        return "redirect:/guestbook";
    }

    private Page<GuestbookEntry> getPage(int pageNumber) {
        return guestbook.findAll(new PageRequest(pageNumber, PAGE_SIZE,
                Sort.Direction.DESC, "id"));
    }

    private Model calculateButtonsActive(Integer pageNumber, Model model,
            Page<GuestbookEntry> page) {
        model.addAttribute("firstActive", pageNumber > 0);
        model.addAttribute("prevActive", pageNumber > 0);
        model.addAttribute("nextActive", page.getTotalPages()
                - (pageNumber + 1) > 0);
        model.addAttribute("lastActive", page.getTotalPages()
                - (pageNumber + 1) > 0);
        return model;
    }

}
