package cn.aftertomorrow.service;

import java.util.List;

import cn.aftertomorrow.po.GuestMessage;

public interface GuestMessageService {
	public int addGuestMessage(GuestMessage guestMessage);

	public List<GuestMessage> listAll();
}
