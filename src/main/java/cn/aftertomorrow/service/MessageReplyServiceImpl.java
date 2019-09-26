package cn.aftertomorrow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.aftertomorrow.dao.MessageReplyDao;
import cn.aftertomorrow.po.MessageReply;

@Service
@Transactional
public class MessageReplyServiceImpl implements MessageReplyService {
	@Autowired
	private MessageReplyDao messageReplyDao;

	public int addMessageReply(MessageReply messageReply) {
		// TODO Auto-generated method stub
		return this.messageReplyDao.addMessageReply(messageReply);
	}

	public List<MessageReply> findByGuestmessage_id(Integer guestmessage_id) {
		// TODO Auto-generated method stub
		return this.messageReplyDao.findByGuestmessage_id(guestmessage_id);
	}

}
