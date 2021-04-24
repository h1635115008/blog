package cn.aftertomorrow.common.util;

import cn.aftertomorrow.common.transfer.FieldRule;
import cn.aftertomorrow.common.transfer.ValueParser;
import javafx.print.Collation;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * JavaBean复制工具类
 *
 * @author huangming
 * @date 2021/04/13
 */
public class JavaBeanUtils {
    /**
     * 复制list
     *
     * @param sourceObjList 源list
     * @param targetClass   目标类
     * @param <T>           目标类泛型
     * @return 目标list
     */
    public static <T> List<T> copyPropertiesToList(List<?> sourceObjList, Class<T> targetClass) {
        return copyPropertiesToList(sourceObjList, targetClass, null);
    }

    /**
     * 复制list
     *
     * @param sourceObjList 源list
     * @param targetClass   目标类
     * @param <T>           目标类泛型
     * @return 目标list
     */
    public static <T> List<T> copyPropertiesToList(List<?> sourceObjList, Class<T> targetClass, List<FieldRule> fieldRules) {
        if (null == sourceObjList || null == targetClass) {
            return null;
        }
        List<T> targetObjList = new ArrayList<>();
        sourceObjList.forEach(e -> {
            try {
                T targetObj = targetClass.newInstance();
                BeanUtils.copyProperties(targetObj, e);
                if (CollectionUtils.isNotEmpty(fieldRules)) {
                    for (FieldRule fieldRule : fieldRules) {
                        ValueParser valueParser = fieldRule.getValueParser();
                        String fieldValue = BeanUtils.getProperty(e, fieldRule.getFieldName());
                        Object parsedField = valueParser == null ? fieldValue : valueParser.apply(fieldValue);
                        BeanUtils.setProperty(targetObj, fieldRule.getNewFiledName(), parsedField);
                    }
                }
                targetObjList.add(targetObj);
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (InvocationTargetException e1) {
                e1.printStackTrace();
            } catch (NoSuchMethodException noSuchMethodException) {
                noSuchMethodException.printStackTrace();
            }
        });
        return targetObjList;
    }

    /**
     * 复制对象
     *
     * @param sourceObj   源对象
     * @param targetClass 目标类
     * @param <T>         目标类泛型
     * @return 目标对象
     */
    public static <T> T copyPropertiesToObject(Object sourceObj, Class<T> targetClass) {
        return copyPropertiesToObject(sourceObj, targetClass, null);
    }

    /**
     * 复制对象
     *
     * @param sourceObj   源对象
     * @param targetClass 目标类
     * @param <T>         目标类泛型
     * @return 目标对象
     */
    public static <T> T copyPropertiesToObject(Object sourceObj, Class<T> targetClass, List<FieldRule> fieldRules) {
        if (null == sourceObj || null == targetClass) {
            return null;
        }
        T targetObj = null;
        try {
            targetObj = targetClass.newInstance();
            // 对默认属性进行拷贝
            BeanUtils.copyProperties(targetObj, sourceObj);
            if (CollectionUtils.isNotEmpty(fieldRules)) {
                for (FieldRule fieldRule : fieldRules) {
                    ValueParser valueParser = fieldRule.getValueParser();
                    String fieldValue = BeanUtils.getProperty(sourceObj, fieldRule.getFieldName());
                    Object parsedField = valueParser == null ? fieldValue : valueParser.apply(fieldValue);
                    BeanUtils.setProperty(targetObj, fieldRule.getNewFiledName(), parsedField);
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
            return targetObj;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return targetObj;
    }
}
