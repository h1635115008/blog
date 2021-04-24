package cn.aftertomorrow.web.controller;

import cn.aftertomorrow.common.enumeration.ResultCodeEnums;
import cn.aftertomorrow.common.request.dto.message.GuestMessageDTO;
import cn.aftertomorrow.common.response.Result;
import cn.aftertomorrow.common.response.vo.message.GuestMessageVO;
import cn.aftertomorrow.common.util.JavaBeanUtils;
import cn.aftertomorrow.common.util.ResultUtils;
import cn.aftertomorrow.common.util.SecurityUtils;
import cn.aftertomorrow.common.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.aftertomorrow.service.GuestMessageService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 留言处理类
 *
 * @author huangming
 * @date 2019/09/26
 */
@Controller
@RequestMapping("message")
public class MessageController {
    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    private GuestMessageService guestMessageService;


    @RequestMapping("")
    public String viewMessage(Model model) {
        List<GuestMessageDTO> guestMessages = guestMessageService.listAll();
        model.addAttribute("guestMessages", guestMessages);
        return "message";
    }

    @RequestMapping("/addMessage")
    @ResponseBody
    public Result<GuestMessageVO> addMessage(GuestMessageDTO guestMessage) {
        if (!StringUtils.notEmpty(guestMessage.getName(), guestMessage.getEmail(), guestMessage.getWords())) {
            return ResultUtils.createFailResult(ResultCodeEnums.ARGS_ERROR, "用户名、邮箱和内容不能为空，或者存在非法内容");
        }
        // 过滤XSS攻击代码
        guestMessage.setName(SecurityUtils.XSSFilter(guestMessage.getName()));
        guestMessage.setEmail(SecurityUtils.XSSFilter(guestMessage.getEmail()));
        guestMessage.setWords(SecurityUtils.XSSFilter(guestMessage.getWords()));

        return ResultUtils.createSuccessResult(JavaBeanUtils.copyPropertiesToObject(guestMessageService.addGuestMessage(guestMessage), GuestMessageVO.class));
    }
}
