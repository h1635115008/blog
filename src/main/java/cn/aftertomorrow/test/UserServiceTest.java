package cn.aftertomorrow.test;

import cn.aftertomorrow.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UserServiceTest {

    @Test
    public void findUserByIdTest() {
        String xmlpath = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlpath);
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
        System.out.println(userService.findUserById("huangming"));
    }
}
