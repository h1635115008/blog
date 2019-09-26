package cn.aftertomorrow.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.aftertomorrow.dao.GuestMessageDao;
import cn.aftertomorrow.po.GuestMessage;

@Service
@Transactional
public class GuestMessageServiceImpl implements GuestMessageService {
	@Autowired
	private GuestMessageDao guestMessageDao;

	public int addGuestMessage(GuestMessage guestMessage) {
		// TODO Auto-generated method stub
		return this.guestMessageDao.addGuestMessage(guestMessage);
	}

	public List<GuestMessage> listAll() {
		// TODO Auto-generated method stub
		return this.guestMessageDao.listAll();
	}

}
