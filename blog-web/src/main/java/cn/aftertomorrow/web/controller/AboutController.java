package cn.aftertomorrow.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 关于页面控制类
 *
 * @author huangming
 * @date 2019/09/26
 */
@Controller
public class AboutController {
    @Autowired
    private ApplicationContext applicationContext;
    @RequestMapping("/about")
    public String goToAbout() {
        return "about";
    }
}
