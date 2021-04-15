package cn.aftertomorrow.common.util;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * POJO复制工具类
 *
 * @author huangming
 * @date 2021/04/13
 */
public class POJOUtils {
    /**
     * 复制list
     *
     * @param sourceList
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> List<T> copyPropertiesToList(List sourceList, Class<T> targetClass) {
        if (null == sourceList || null == targetClass) {
            return null;
        }
        List<T> targetList = new ArrayList<>();
        sourceList.forEach(e -> {
            try {
                T target = targetClass.newInstance();
                BeanUtils.copyProperties(target, e);
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            }
        });
        return targetList;
    }

    /**
     * 复制对象
     *
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T copyPropertiesToObject(Object source, Class<T> target) {
        if (null == source || null == target) {
            return null;
        }
        T targetObj = null;
        try {
            targetObj = target.newInstance();
            BeanUtils.copyProperties(targetObj, source);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return targetObj;
    }
}
