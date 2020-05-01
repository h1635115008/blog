package cn.aftertomorrow.controller;

import java.util.Date;
import java.util.List;

import cn.aftertomorrow.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.aftertomorrow.po.GuestMessage;
import cn.aftertomorrow.po.MessageReply;
import cn.aftertomorrow.service.GuestMessageService;
import cn.aftertomorrow.service.MessageReplyService;

@Controller
public class MessageController {
    @Autowired
    private GuestMessageService guestMessageService;
    @Autowired
    private MessageReplyService messageReplyService;

    @RequestMapping("/message")
    public String findBypage(Model model) throws Exception {
        List<GuestMessage> guestMessages = guestMessageService.listAll();
        System.out.println(guestMessages);
        model.addAttribute("guestMessages", guestMessages);
        return "message";
    }

    @RequestMapping("/addMessage")
    public String addMessage(GuestMessage guestMessage) throws Exception {
        System.out.println("addMessage");
        guestMessage.setName(Util.XSSFilter(guestMessage.getName()));
        guestMessage.setEmail(Util.XSSFilter(guestMessage.getEmail()));
        guestMessage.setWords(Util.XSSFilter(guestMessage.getWords()));
        String words = guestMessage.getWords();
        if (!words.startsWith(">> F")) {
            guestMessage.setTime(new Date());
            if (words != null && !words.equals("")) {
                guestMessageService.addGuestMessage(guestMessage);
            }
        } else {
            MessageReply messageReply = new MessageReply();
            messageReply.setTime(new Date());
            messageReply.setEmail(guestMessage.getEmail());
            messageReply.setName(guestMessage.getName());
            Integer guestMessage_id = Integer.parseInt(words.split(" ")[1].replaceFirst("F", ""));
            messageReply.setGuestmessage_id(guestMessage_id.intValue());
            words = words.replaceFirst(">> F" + guestMessage_id, "");
            if (!words.equals("")) {
                messageReply.setWords(words);
                System.out.println(messageReply);
                messageReplyService.addMessageReply(messageReply);
            }
        }
        return "redirect:message";
    }
}
