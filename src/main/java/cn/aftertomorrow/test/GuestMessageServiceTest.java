package cn.aftertomorrow.test;

import java.util.Date;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aftertomorrow.po.GuestMessage;
import cn.aftertomorrow.service.GuestMessageService;

public class GuestMessageServiceTest {
	@Test
	public void listAllTest() {
		String xmlpath = "applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlpath);
		GuestMessageService guestMessageService = (GuestMessageService) applicationContext
				.getBean("guestMessageServiceImpl");
		/*
		 * GuestMessage guestMessage = new GuestMessage();
		 * guestMessage.setEmail("162@qq.com"); guestMessage.setName("LCJ");
		 * guestMessage.setWords("huangming"); guestMessage.setTime(new Date());
		 * guestMessageService.addGuestMessage(guestMessage);
		 */
		System.out.println(guestMessageService.listAll());
	}
}
