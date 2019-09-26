package cn.aftertomorrow.util;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Util {
    public static String getYMDHMS(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        return sdf.format(date);
    }

    public static String getYMDHM(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm");
        return sdf.format(date);
    }

    public static String getYMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String getMD(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        return sdf.format(date);
    }

    public static void order(Map sthOrderByitems, String item, List sthOrderByitem, Object sth) {
        if (sthOrderByitems.get(item) == null) {
            sthOrderByitem.add(sth);
            sthOrderByitems.put(item, sthOrderByitem);
        } else {
            ((List) sthOrderByitems.get(item)).add(sth);
        }
    }

    public static boolean notEmpty(String... args) {
        for (String arg : args) {
            if (arg == null || arg.equals("")) {
                return false;
            }
        }
        return true;
    }

    public static String XSSFilter(String string) {
        Whitelist whitelist = Whitelist.relaxed();
        return Jsoup.clean(string, whitelist);
    }
}
