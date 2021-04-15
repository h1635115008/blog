package cn.aftertomorrow.common.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 * 安全相关工具类
 *
 * @author huangming
 * @date 2021/04/13
 */
public class SecurityUtils {
    /**
     * XSS攻击过滤
     *
     * @param string
     * @return
     */
    public static String XSSFilter(String string) {
        Whitelist whitelist = Whitelist.relaxed();
        return Jsoup.clean(string, whitelist);
    }
}
