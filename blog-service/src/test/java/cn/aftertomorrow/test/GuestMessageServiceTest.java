//package cn.aftertomorrow.test;
//
//import cn.aftertomorrow.service.GuestMessageService;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class GuestMessageServiceTest {
//	@Test
//	public void listAllTest() {
//		String xmlpath = "applicationContext.xml";
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlpath);
//		GuestMessageService guestMessageService = (GuestMessageService) applicationContext
//				.getBean("guestMessageServiceImpl");
//		/*
//		 * GuestMessageDO guestMessage = new GuestMessageDO();
//		 * guestMessage.setEmail("162@qq.com"); guestMessage.setName("LCJ");
//		 * guestMessage.setWords("huangming"); guestMessage.setTime(new Date());
//		 * guestMessageService.addGuestMessage(guestMessage);
//		 */
//		System.out.println(guestMessageService.listAll());
//	}
//}
