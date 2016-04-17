package com.zeroone.guestebook.web;

import static org.springframework.util.Assert.notNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    private final GuestbookEntriesRepository guestbook;

    @Autowired
    public GuestbookController(GuestbookEntriesRepository guestbook) {
        notNull(guestbook, "guestbook==null");
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
     * "/guestbook"
     */
    @RequestMapping(value = "/guestbook", method = RequestMethod.GET)
    String guestbook(Model model, GuestbookEntryForm form) {
        model.addAttribute("entries", guestbook.findAll());
        model.addAttribute("form", form);
        return "guestbook";
    }

    @RequestMapping(value = "/pages/{pageNumber}", method = RequestMethod.GET)
    public String getRunbookPage(@PathVariable Integer pageNumber, Model model) {
        Page<GuestbookEntry> page = guestbook.findAll(new PageRequest(
                pageNumber, 20));
        model.addAttribute("page", page);
        return "guesbookpage";
    }
    /**
     * "/guestbook", removes a {@link GuestbookEntry} specified by the ID.
     *
     * @param id
     *            the {@link GuestbookEntry} ID.
     */
    @RequestMapping(value = "/guestbook/{id}", method = RequestMethod.DELETE)
    String removeEntry(@PathVariable Long id) {
        guestbook.delete(id);
        return "redirect:/guestbook";
    }
}
