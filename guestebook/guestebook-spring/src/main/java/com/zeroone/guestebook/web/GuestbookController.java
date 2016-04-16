package com.zeroone.guestebook.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GuestbookController {

    @RequestMapping("/")
    public String greeting(Model model) {
        return "guestbook";
    }

}
