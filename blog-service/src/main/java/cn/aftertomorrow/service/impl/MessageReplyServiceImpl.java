package cn.aftertomorrow.service.impl;

import java.util.List;

import cn.aftertomorrow.dao.domain.MessageReply;
import cn.aftertomorrow.dao.mapper.MessageReplyMapper;
import cn.aftertomorrow.service.MessageReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MessageReplyServiceImpl implements MessageReplyService {
    @Autowired
    private MessageReplyMapper messageReplyMapper;

    public int addMessageReply(MessageReply messageReply) {
        return this.messageReplyMapper.addMessageReply(messageReply);
    }

    public List<MessageReply> findByGuestmessage_id(Integer guestmessage_id) {
        return this.messageReplyMapper.findByGuestmessage_id(guestmessage_id);
    }

}
