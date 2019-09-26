package cn.aftertomorrow.controller;

import cn.aftertomorrow.po.Article;
import cn.aftertomorrow.po.GuestMessage;
import cn.aftertomorrow.service.ArticleService;
import cn.aftertomorrow.service.GuestMessageService;
import cn.aftertomorrow.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;

@Controller
public class AdminController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private GuestMessageService guestMessageService;

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/admin/welcome")
    public String welcome() {
        return "admin/welcome";
    }

    @RequestMapping("/admin/articleManager")
    public String articleManager(Model model) {
        List<Article> articles = articleService.listAll();
        model.addAttribute("articles", articles);
        return "admin/articleManager";
    }

    @RequestMapping("/admin/messageManager")
    public String messageManager(Model model) {
        List<GuestMessage> messages = guestMessageService.listAll();
        model.addAttribute("messages", messages);
        return "admin/messageManager";
    }

    @RequestMapping(value = "/admin/addArticle", method = RequestMethod.GET)
    public String goToAdd(Model model) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("跳转写博客");
        model.addAttribute("tagOrderByKinds", tagService.getTagOrderByKinds());
        return "admin/addArticle";
    }

    @RequestMapping(value = "/admin/addArticle", method = RequestMethod.POST)
    @ResponseBody
    public Object addArticle(Article article) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("添加文章");
        Map<String, String> map = new HashMap<String, String>();
        if (articleService.addArticle(article) > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "failure");
        }
        return map;
    }

    @RequestMapping("/admin/deleteArticle")
    @ResponseBody
    public Object deleteArticle(@RequestParam(name = "ids[]") Integer[] ids) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("删除文章");
        System.out.println(Arrays.toString(ids));
        Map<String, String> map = new HashMap<String, String>();
        if (articleService.deleteArticle(ids) > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "failure");
        }
        return map;
    }

    @RequestMapping(value = "/admin/editArticle/{id}", method = RequestMethod.GET)
    public String goToEdit(@PathVariable Integer id, Model model) {
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        model.addAttribute("tagOrderByKinds", tagService.getTagOrderByKinds());
        return "admin/editArticle";
    }

    @RequestMapping(value = "/admin/editArticle", method = RequestMethod.POST)
    @ResponseBody
    public Object editArticle(Article article) {
        System.out.println(article);
        Map<String, String> map = new HashMap<>();
        if (articleService.editArticle(article) > 0) {
            map.put("msg", "success");
        } else {
            map.put("msg", "failure");
        }
        return map;
    }

    @RequestMapping(value = "/admin/viewArticle/{id}", method = RequestMethod.GET)
    public String goToView(@PathVariable Integer id, Model model) {
        Article article = articleService.findArticleById(id);
        model.addAttribute("article", article);
        return "admin/viewArticle";
    }
}