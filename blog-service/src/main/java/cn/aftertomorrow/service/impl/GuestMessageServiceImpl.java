package cn.aftertomorrow.service.impl;

import java.util.List;

import cn.aftertomorrow.dao.domain.GuestMessage;
import cn.aftertomorrow.dao.mapper.GuestMessageMapper;
import cn.aftertomorrow.service.GuestMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GuestMessageServiceImpl implements GuestMessageService {
	@Autowired
	private GuestMessageMapper guestMessageMapper;

	public int addGuestMessage(GuestMessage guestMessage) {
		return this.guestMessageMapper.addGuestMessage(guestMessage);
	}

	public List<GuestMessage> listAll() {
		return this.guestMessageMapper.listAll();
	}

	@Override
	public int deleteMessage(Integer[] ids) {
		return 0;
	}

}
