package cn.aftertomorrow.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author huangming
 * @date 2021/04/13
 */
public class DateUtils {
    /**
     * 获取年月日时分秒
     *
     * @param date
     * @return
     */
    public static String getYMDHMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取年月日时分
     *
     * @param date
     * @return
     */
    public static String getYMDHM(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        return sdf.format(date);
    }

    /**
     * 获取年月日
     *
     * @param date
     * @return
     */
    public static String getYMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 获取月日
     *
     * @param date
     * @return
     */
    public static String getMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(date);
    }
}
