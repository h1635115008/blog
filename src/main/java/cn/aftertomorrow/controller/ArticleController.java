package cn.aftertomorrow.controller;

import java.net.URLDecoder;
import java.util.List;

import cn.aftertomorrow.po.Page;
import cn.aftertomorrow.util.*;
import org.aopalliance.intercept.Interceptor;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.aftertomorrow.po.Article;
import cn.aftertomorrow.service.ArticleService;
import cn.aftertomorrow.service.TagService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/page/{current}", method = RequestMethod.GET)
    public String findBypage(@PathVariable Integer current, Model model) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("跳转主页");
        if (current == null) current = 1;
        Page page = new Page((current - 1) * 8, 8);
        List<Article> articles = articleService.findByPage(page);
        model.addAttribute("articles", articles);
        int size = articleService.size();
        Interceptor interceptor;
        Advisor advisor;
        MethodInterceptor methodInterceptor;
        int maxPage = size % 8 == 0 ? size / 8 : size / 8 + 1;
        if (maxPage == 0) maxPage = 1;
        model.addAttribute("maxPage", maxPage);
        model.addAttribute("path", "page");
        model.addAttribute("page", current);
        return "index";
    }

    @RequestMapping("/")
    public String redirect() throws Exception {
        // TODO Auto-generated method stub
        return "forward:page/1";
    }

    @RequestMapping("/file")
    public String findByTime(Model model) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("跳转归档");
        model.addAttribute("aticleOrderByYears", articleService.getArticleOrderByYears());
        return "file";
    }

    @RequestMapping("/tag")
    public String findByTag(Model model) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("跳转标签");
        model.addAttribute("aticleOrderByTags", articleService.getArticleOrderByTags());
        return "tag";
    }

    @RequestMapping(value = "/text/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable Integer id, Model model) throws Exception {
        // TODO Auto-generated method stub
        System.out.println("跳转全文");
        Article article = articleService.findArticleById(id);
        if (article == null) throw new NullPointerException();
        model.addAttribute("article", article);
        return "text";
    }


    @RequestMapping("/search")
    @ResponseBody
    public SerachResponse search(String keywords) throws Exception {
        SerachResponse serachResponse = new SerachResponse();
        URLDecoder urlDecoder = new URLDecoder();
        String deCode = urlDecoder.decode((keywords), "UTF-8");
        if (deCode != null && !deCode.equals("")) {
            serachResponse.setSearchItems(articleService.search(deCode));
        }
        return serachResponse;
    }
}
