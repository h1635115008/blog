package cn.aftertomorrow.dao;

import java.util.List;

import cn.aftertomorrow.po.GuestMessage;

public interface GuestMessageDao {
	public int addGuestMessage(GuestMessage guestMessage);

	public List<GuestMessage> listAll();
}
