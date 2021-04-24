package cn.aftertomorrow.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * String工具类
 *
 * @author huangming
 * @date 2021/04/13
 */
public class StringUtils {

    /**
     * 判空函数
     *
     * @param args
     * @return
     */
    public static boolean notEmpty(String... args) {
        for (String arg : args) {
            if (arg == null || "".equals(arg)) {
                return false;
            }
        }
        return true;
    }


}
