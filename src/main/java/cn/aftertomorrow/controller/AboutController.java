package cn.aftertomorrow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {
    @Autowired
    private ApplicationContext applicationContext;
    @RequestMapping("/about")
    public String goToAbout() {
        return "about";
    }
}
