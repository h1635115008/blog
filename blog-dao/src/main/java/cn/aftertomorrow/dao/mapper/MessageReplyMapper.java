package cn.aftertomorrow.dao.mapper;

import java.util.List;

import cn.aftertomorrow.dao.domain.MessageReply;


public interface MessageReplyMapper {
	int addMessageReply(MessageReply messageReply);

	List<MessageReply> findByGuestmessage_id(Integer guestmessage_id);
}
