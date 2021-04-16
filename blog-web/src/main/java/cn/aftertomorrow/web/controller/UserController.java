package cn.aftertomorrow.web.controller;

import cn.aftertomorrow.common.request.dto.user.UserDTO;
import cn.aftertomorrow.service.UserService;
import cn.hutool.core.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * 用户管理控制类
 *
 * @author huangming
 * @date 2019/09/26
 */
@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String gotoLogin() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(UserDTO user, HttpSession session, HttpServletRequest request, Model model) {
        UserDTO realUser = userService.findUserByName(user.getUsername());
        if (ObjectUtil.isEmpty(realUser)) {
            model.addAttribute("message", "^_^用户不存在，请重新输入");
            return "login";
        }
        String path = request.getHeader("Referer").replace(request.getHeader("Origin"), "");
        if (null != realUser && user.getPassword().equals(realUser.getPassword())) {
            session.setAttribute("user", realUser);
            if (path.indexOf("/login") >= 0) {
                return "redirect:admin";
            } else {
                return "redirect:" + path;
            }
        }
        model.addAttribute("message", "^_^密码错误，请重新输入");
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
