package cn.aftertomorrow.service;

import java.util.List;

import cn.aftertomorrow.dao.domain.GuestMessage;

public interface GuestMessageService {
	int addGuestMessage(GuestMessage guestMessage);

	List<GuestMessage> listAll();

	int deleteMessage(Integer[] ids);
}
