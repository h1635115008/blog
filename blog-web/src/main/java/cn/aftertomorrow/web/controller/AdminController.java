package cn.aftertomorrow.web.controller;

import cn.aftertomorrow.common.enumeration.ResultCodeEnums;
import cn.aftertomorrow.common.request.dto.article.ArticleDTO;
import cn.aftertomorrow.common.request.dto.message.GuestMessageDTO;
import cn.aftertomorrow.common.response.Result;
import cn.aftertomorrow.common.util.ResultUtils;
import cn.aftertomorrow.service.ArticleService;
import cn.aftertomorrow.service.GuestMessageService;
import cn.aftertomorrow.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 后台管理控制类
 *
 * @author huangming
 * @date 2019/09/26
 */
@RequestMapping("/admin")
@Controller
public class AdminController {
    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;
    @Autowired
    private GuestMessageService guestMessageService;

    @RequestMapping("")
    public String admin() {
        return "admin";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "admin/welcome";
    }

    @RequestMapping("/articleManager")
    public String articleManager(Model model) {
        List<ArticleDTO> articles = articleService.listAll();
        model.addAttribute("articles", articles);
        return "admin/articleManager";
    }

    @RequestMapping("/messageManager")
    public String messageManager(Model model) {
        List<GuestMessageDTO> messages = guestMessageService.listAll();
        model.addAttribute("messages", messages);
        return "admin/messageManager";
    }

    @RequestMapping("/deleteMessage")
    @ResponseBody
    public Result<Object> deleteMessage(@RequestParam(name = "ids[]") Integer[] ids) throws Exception {
        logger.info("删除留言");
        logger.info(Arrays.toString(ids));
        if (guestMessageService.deleteMessage(ids) > 0) {
            return ResultUtils.createSuccessResult(null);
        }
        return ResultUtils.createFailResult(ResultCodeEnums.BIZ_ERROR, "删除留言失败");
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.GET)
    public String goToAdd(Model model) throws Exception {
        logger.info("跳转写博客");
        model.addAttribute("tagCollectionByKind", tagService.getTagCollectionByKind());
        return "admin/addArticle";
    }

    @RequestMapping(value = "/addArticle", method = RequestMethod.POST)
    @ResponseBody
    public Object addArticle(ArticleDTO article) throws Exception {
        logger.info("添加文章");
        if (articleService.addArticle(article) > 0) {
            return ResultUtils.createSuccessResult(null);
        }
        return ResultUtils.createFailResult(ResultCodeEnums.BIZ_ERROR, "添加文章失败");
    }

    @RequestMapping("/deleteArticle")
    @ResponseBody
    public Object deleteArticle(@RequestParam(name = "ids[]") Integer[] ids) throws Exception {
        logger.info("删除文章");
        logger.info(Arrays.toString(ids));
        Map<String, String> map = new HashMap<>(16);
        if (articleService.deleteArticle(ids) > 0) {
            return ResultUtils.createSuccessResult(null);
        }
        return ResultUtils.createFailResult(ResultCodeEnums.BIZ_ERROR, "删除文章失败");
    }

    @RequestMapping(value = "/editArticle/{id}", method = RequestMethod.GET)
    public String goToEdit(@PathVariable Integer id, Model model) {
        ArticleDTO article = articleService.findArticleById(id, false);
        model.addAttribute("article", article);
        model.addAttribute("tagCollectionByKind", tagService.getTagCollectionByKind());
        return "admin/editArticle";
    }

    @RequestMapping(value = "/editArticle", method = RequestMethod.POST)
    @ResponseBody
    public Object editArticle(ArticleDTO article) {
        logger.info(article.toString());
        if (articleService.editArticle(article) > 0) {
            return ResultUtils.createSuccessResult(null);
        }
        return ResultUtils.createFailResult(ResultCodeEnums.BIZ_ERROR, "编辑文章失败");
    }

    @RequestMapping(value = "/viewArticle/{id}", method = RequestMethod.GET)
    public String goToView(@PathVariable Integer id, Model model) {
        ArticleDTO article = articleService.findArticleById(id, false);
        model.addAttribute("article", article);
        return "admin/viewArticle";
    }
}