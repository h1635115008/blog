package cn.aftertomorrow.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aftertomorrow.service.ArticleService;
import cn.aftertomorrow.service.TagService;

public class TagServiceTest {
	@Test
	public void listAllTest() {
		String xmlpath = "applicationContext.xml";
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlpath);
		TagService articleService = (TagService) applicationContext.getBean("tagServiceImpl");
		System.out.println(articleService.listAll());
	}
}
