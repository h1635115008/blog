package cn.aftertomorrow.web.controller;

import java.net.URLDecoder;
import java.util.List;

import cn.aftertomorrow.common.request.dto.article.ArticleDTO;
import cn.aftertomorrow.common.response.Result;
import cn.aftertomorrow.common.response.vo.ArticleVO;
import cn.aftertomorrow.common.util.POJOUtils;
import cn.aftertomorrow.common.util.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.aftertomorrow.service.ArticleService;
import cn.aftertomorrow.service.TagService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 文章控制类
 *
 * @author huangming
 * @date 2019/09/26
 */
@Controller
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private ArticleService articleService;
    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/page/{pageNum}", method = RequestMethod.GET)
    public String findByPageSize(@PathVariable Integer pageNum, Model model) throws Exception {
        logger.info("跳转主页");
        if (pageNum == null) {
            pageNum = 1;
        }
        PageInfo<ArticleDTO> pageInfo = articleService.findByPage(pageNum, 8);
        model.addAttribute("articles", POJOUtils.copyPropertiesToList(pageInfo.getList(), ArticleVO.class));
        model.addAttribute("maxPage", pageInfo.getPages());
        model.addAttribute("path", "page");
        model.addAttribute("page", pageNum);
        return "index";
    }

    @RequestMapping("/")
    public String redirect() throws Exception {
        return "forward:page/1";
    }

    @RequestMapping("/file")
    public String findByTime(Model model) throws Exception {
        logger.info("跳转归档");
        model.addAttribute("aticleOrderByYears", articleService.getArticleCollectionByYear());
        return "file";
    }

    @RequestMapping("/tag")
    public String findByTag(Model model) throws Exception {
        logger.info("跳转标签");
        model.addAttribute("aticleOrderByTags", articleService.getArticleCollectionByTag());
        return "tag";
    }

    @RequestMapping(value = "/text/{id}", method = RequestMethod.GET)
    public String findById(@PathVariable Integer id, Model model) throws Exception {
        logger.info("跳转全文");
        ArticleDTO articleDTO = articleService.findArticleById(id);
        if (articleDTO == null) {
            throw new NullPointerException();
        }
        model.addAttribute("article", POJOUtils.copyPropertiesToObject(articleDTO, ArticleVO.class));
        return "text";
    }


    @RequestMapping("/search")
    @ResponseBody
    public Result<List<ArticleVO>> search(String keywords) throws Exception {
        String deCodeStr = URLDecoder.decode((keywords), "UTF-8");
        if (deCodeStr != null && !deCodeStr.equals("")) {
            List<ArticleDTO> articleDTOList = articleService.searchByKeywords(deCodeStr);
            List<ArticleVO> articleVOList = POJOUtils.copyPropertiesToList(articleDTOList, ArticleVO.class);
            return ResultUtils.createSuccessResult(articleVOList);
        }
        return ResultUtils.createSuccessResult(null);
    }
}
