//package cn.aftertomorrow.test;
//
//import org.jsoup.Jsoup;
//import org.jsoup.safety.Whitelist;
//import org.junit.Test;
//
//public class JsoupTest {
//    @Test
//    public void whitelist() {
//
//        String html = "sasasasa";//接收到的html代码
//        //防御XSS攻击,安全HTML验证
//        //可自定义jsoup的Whitelist
//        Whitelist whitelist = Whitelist.relaxed();
//
//        //其它白名单设置...
//
//        //获得安全HTML，消除xss隐患
//        String safeHtml = Jsoup.clean(html, whitelist);
//        System.out.println(safeHtml);
//    }
//}
