package cn.aftertomorrow.controller;

import cn.aftertomorrow.po.User;
import cn.aftertomorrow.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String gotoLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(User user, HttpSession session, HttpServletRequest request, Model model) {
        User u = userService.findUserById(user.getUsername());
        String path = request.getHeader("Referer").replace(request.getHeader("Origin"), "");
        System.out.println(path);
        if (u != null && u.getPassword().equals(user.getPassword())) {
            session.setAttribute("user", u);
            if (path.indexOf("/login") >= 0) {
                return "redirect:admin";
            } else {
                return "redirect:" + path;
            }
        }
        model.addAttribute("message", "^_^用户名或密码错误请重新输入");
        return "login";
    }

    @RequestMapping(value = "/logout")
    @ResponseBody
    public Object logout(HttpSession session) {
        Map<String, String> map = new HashMap<>();
        if (session.getAttribute("user") != null) {
            session.removeAttribute("user");
            map.put("msg", "退出登录成功");
        } else {
            map.put("msg", "你还没有登录");
        }
        return map;
    }
}
