//package cn.aftertomorrow.web.controller;
//
//import java.util.Date;
//import java.util.List;
//
//import cn.aftertomorrow.util.Util;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import cn.aftertomorrow.po.GuestMessage;
//import cn.aftertomorrow.po.MessageReply;
//import cn.aftertomorrow.service.GuestMessageService;
//import cn.aftertomorrow.service.MessageReplyService;
//
//@Controller
//public class MessageController {
//    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);
//    @Autowired
//    private GuestMessageService guestMessageService;
//    @Autowired
//    private MessageReplyService messageReplyService;
//
//    @RequestMapping("/message")
//    public String findBypage(Model model) throws Exception {
//        List<GuestMessage> guestMessages = guestMessageService.listAll();
//        logger.info(guestMessages.toString());
//        model.addAttribute("guestMessages", guestMessages);
//        return "message";
//    }
//
//    @RequestMapping("/addMessage")
//    public String addMessage(GuestMessage guestMessage) throws Exception {
//        logger.info("addMessage");
//        guestMessage.setName(Util.XSSFilter(guestMessage.getName()));
//        guestMessage.setEmail(Util.XSSFilter(guestMessage.getEmail()));
//        guestMessage.setWords(Util.XSSFilter(guestMessage.getWords()));
//        String words = guestMessage.getWords();
//        if (!words.startsWith(">> F")) {
//            guestMessage.setTime(new Date());
//            if (words != null && !words.equals("")) {
//                guestMessageService.addGuestMessage(guestMessage);
//            }
//        } else {
//            MessageReply messageReply = new MessageReply();
//            messageReply.setTime(new Date());
//            messageReply.setEmail(guestMessage.getEmail());
//            messageReply.setName(guestMessage.getName());
//            Integer guestMessage_id = Integer.parseInt(words.split(" ")[1].replaceFirst("F", ""));
//            messageReply.setGuestmessage_id(guestMessage_id.intValue());
//            words = words.replaceFirst(">> F" + guestMessage_id, "");
//            if (!words.equals("")) {
//                messageReply.setWords(words);
//                logger.info(messageReply.toString());
//                messageReplyService.addMessageReply(messageReply);
//            }
//        }
//        return "redirect:message";
//    }
//}
