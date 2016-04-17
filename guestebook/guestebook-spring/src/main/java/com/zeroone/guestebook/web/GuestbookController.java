package com.zeroone.guestebook.web;

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

    private static final int PAGE_SIZE = 20;

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
     * "/guestbook", shows the page 0.
     */
    @RequestMapping(value = "/guestbook", method = RequestMethod.GET)
    String guestbook(Model model, GuestbookEntryForm form) {
        Page<GuestbookEntry> page = guestbook.findAll(new PageRequest(0,
                PAGE_SIZE, Sort.Direction.DESC, "id"));
        model.addAttribute("prevActive", false);
        model.addAttribute("nextActive", page.getTotalPages() - 1 > 0);
        model.addAttribute("lastActive", page.getTotalPages() - 1 > 0);
        model.addAttribute("page", page);
        model.addAttribute("form", form);
        return "guestbook";
    }

    /**
     * "/guestbook", shows the page with the specificed page number.
     *
     * @param pageNumber
     *            the page number to show, starting with 0.
     */
    @RequestMapping(value = "/guestbook/{pageNumber}", method = RequestMethod.GET)
    public String getRunbookPage(@PathVariable Integer pageNumber, Model model,
            GuestbookEntryForm form) {
        Page<GuestbookEntry> page = guestbook.findAll(new PageRequest(
                pageNumber, PAGE_SIZE, Sort.Direction.DESC, "id"));
        model.addAttribute("prevActive", pageNumber > 0);
        model.addAttribute("nextActive", page.getTotalPages()
                - (pageNumber + 1) > 0);
        model.addAttribute("lastActive", page.getTotalPages()
                - (pageNumber + 1) > 0);
        model.addAttribute("page", page);
        model.addAttribute("form", form);
        return "guestbook";
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
