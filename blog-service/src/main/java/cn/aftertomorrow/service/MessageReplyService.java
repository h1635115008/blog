package cn.aftertomorrow.service;

import java.util.List;

import cn.aftertomorrow.dao.domain.MessageReply;

public interface MessageReplyService {
    int addMessageReply(MessageReply messageReply);

    List<MessageReply> findByGuestmessage_id(Integer guestmessage_id);
}
