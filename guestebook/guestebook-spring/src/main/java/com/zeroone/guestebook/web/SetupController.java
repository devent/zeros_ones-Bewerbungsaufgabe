package com.zeroone.guestebook.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Configures the database for the Guestbook.
 * 
 * @author Erwin Müller, erwin.mueller@deventm.de
 * @since 1.0
 */
@Controller
public class SetupController {

    @RequestMapping("/setup")
    public String setup(Model model) {
        return "setup";
    }

}
