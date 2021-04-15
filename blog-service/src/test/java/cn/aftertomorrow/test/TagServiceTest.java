//package cn.aftertomorrow.test;
//
//import cn.aftertomorrow.service.TagService;
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//public class TagServiceTest {
//	@Test
//	public void listAllTest() {
//		String xmlpath = "applicationContext.xml";
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlpath);
//		TagService articleService = (TagService) applicationContext.getBean("tagServiceImpl");
//		System.out.println(articleService.listAll());
//	}
//}
