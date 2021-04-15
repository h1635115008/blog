//package cn.aftertomorrow.web.controller;
//
//import cn.aftertomorrow.service.ArticleService;
//import cn.aftertomorrow.service.GuestMessageService;
//import cn.aftertomorrow.service.TagService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.*;
//
//@RequestMapping("/admin")
//@Controller
//public class AdminController {
//    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
//
//    @Autowired
//    private ArticleService articleService;
//    @Autowired
//    private TagService tagService;
//    @Autowired
//    private GuestMessageService guestMessageService;
//
//    @RequestMapping("/")
//    public String admin() {
//        return "admin";
//    }
//
//    @RequestMapping("/welcome")
//    public String welcome() {
//        return "admin/welcome";
//    }
//
//    @RequestMapping("/articleManager")
//    public String articleManager(Model model) {
//        List<Article> articles = articleService.listAll();
//        model.addAttribute("articles", articles);
//        return "admin/articleManager";
//    }
//
//    @RequestMapping("/messageManager")
//    public String messageManager(Model model) {
//        List<GuestMessage> messages = guestMessageService.listAll();
//        model.addAttribute("messages", messages);
//        return "admin/messageManager";
//    }
//
//    @RequestMapping("/deleteMessage")
//    @ResponseBody
//    public Object deleteMessage (@RequestParam(name = "ids[]") Integer[] ids) throws Exception {
//        logger.info("删除留言");
//        logger.info(Arrays.toString(ids));
//        Map<String, String> map = new HashMap<String, String>();
//        if (guestMessageService.deleteMessage(ids) > 0) {
//            map.put("msg", "success");
//        } else {
//            map.put("msg", "failure");
//        }
//        return map;
//    }
//
//    @RequestMapping(value = "/addArticle", method = RequestMethod.GET)
//    public String goToAdd(Model model) throws Exception {
//        logger.info("跳转写博客");
//        model.addAttribute("tagOrderByKinds", tagService.getTagOrderByKinds());
//        return "admin/addArticle";
//    }
//
//    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
//    @ResponseBody
//    public Object addArticle(Article article) throws Exception {
//        logger.info("添加文章");
//        Map<String, String> map = new HashMap<String, String>();
//        if (articleService.addArticle(article) > 0) {
//            map.put("msg", "success");
//        } else {
//            map.put("msg", "failure");
//        }
//        return map;
//    }
//
//    @RequestMapping("/deleteArticle")
//    @ResponseBody
//    public Object deleteArticle(@RequestParam(name = "ids[]") Integer[] ids) throws Exception {
//        logger.info("删除文章");
//        logger.info(Arrays.toString(ids));
//        Map<String, String> map = new HashMap<>();
//        if (articleService.deleteArticle(ids) > 0) {
//            map.put("msg", "success");
//        } else {
//            map.put("msg", "failure");
//        }
//        return map;
//    }
//
//    @RequestMapping(value = "/editArticle/{id}", method = RequestMethod.GET)
//    public String goToEdit(@PathVariable Integer id, Model model) {
//        Article article = articleService.findArticleById(id);
//        model.addAttribute("article", article);
//        model.addAttribute("tagOrderByKinds", tagService.getTagOrderByKinds());
//        return "admin/editArticle";
//    }
//
//    @RequestMapping(value = "/editArticle", method = RequestMethod.POST)
//    @ResponseBody
//    public Object editArticle(Article article) {
//        logger.info(article.toString());
//        Map<String, String> map = new HashMap<>();
//        if (articleService.editArticle(article) > 0) {
//            map.put("msg", "success");
//        } else {
//            map.put("msg", "failure");
//        }
//        return map;
//    }
//
//    @RequestMapping(value = "/viewArticle/{id}", method = RequestMethod.GET)
//    public String goToView(@PathVariable Integer id, Model model) {
//        Article article = articleService.findArticleById(id);
//        model.addAttribute("article", article);
//        return "admin/viewArticle";
//    }
//}