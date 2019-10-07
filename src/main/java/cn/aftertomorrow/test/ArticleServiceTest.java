package cn.aftertomorrow.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.aftertomorrow.po.Page;
import cn.aftertomorrow.service.ArticleService;

public class ArticleServiceTest {
    private int a;

    {
        a = 100;
    }

    @Test
    public void listAllTest() {
        String xmlpath = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlpath);
        ArticleService articleService = (ArticleService) applicationContext.getBean("articleServiceImpl");
        System.out.println(articleService.search("I/O"));
//        try {
//            Thread.sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println(articleService.serach(str2));
//        System.out.println(articleService.listAll().get(0));
//        System.out.println("--------------------------------------------------------------------------------------------------------------------");
//        System.out.println(articleService.getAticleOrderByTags());
    }

    public static void main(String[] args) {
		/*Object obj1 = new Object();
		Object obj2 = new Object();  
		System.out.println(obj1);
		System.out.println(Integer.toHexString(obj1.hashCode()));
		System.out.println(obj2);
		System.out.println(Integer.toHexString(obj2.hashCode()));
		System.out.println(Integer.toHexString(System.identityHashCode(obj1)));
		String str1 = "huangming";
		String str2 = new String("huangming");
		System.out.println(str1 == str2);
		System.out.println(str2.equals(str1));
		String hello = new String("huangming");
		System.out.println(hello.hashCode());
		System.out.println(Integer.toHexString(System.identityHashCode(hello)));*/
		/*HashSet<String> set = new HashSet<>();
		set.add("huangming");
		set.add("wcc");
		set.add("helloworld");
		System.out.println();
		Iterator<String> it = set.iterator();*/
		/*while(it.hasNext()){
			String str = (String)it.next();
			if(str.equals("huangming")){
				System.out.println("hello, " + str);
				list.remove("huangming");
			}
		}*/
		/*it.forEachRemaining(str -> {
			System.out.println(str);
		});*/
        HashMap<String, Object> hashMap = new HashMap<>();
        StringBuffer strBuffer = new StringBuffer("huangming");
        System.out.println(strBuffer);
        StringBuilder strBuilder = new StringBuilder("huangming");
        System.out.println(1 << 30);
        int[] a = {};
        a[0] = 100;
        System.out.println(a.length);
    }
}
