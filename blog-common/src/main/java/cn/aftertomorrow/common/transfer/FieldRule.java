package cn.aftertomorrow.common.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author huangming
 * @className FieldRule
 * @description
 * @date 2021/4/24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FieldRule {
    /**
     * 原有属性名称
     */
    private String fieldName;

    /**
     * 预替换的属性名称
     */
    private String newFiledName;

    /**
     *
     */
    private ValueParser valueParser;


    /**
     * 创建FieldRule对象
     *
     * @param fieldName
     * @param newFiledName
     * @return
     */
    public static FieldRule create(String fieldName, String newFiledName) {
        FieldRule fieldRule = new FieldRule();
        fieldRule.setFieldName(fieldName);
        fieldRule.setNewFiledName(newFiledName);
        return fieldRule;
    }

    /**
     * 创建FieldRule对象
     *
     * @param fieldName
     * @param newFiledName
     * @param valueParser
     * @return
     */
    public static FieldRule create(String fieldName, String newFiledName, ValueParser valueParser) {
        FieldRule fieldRule = new FieldRule();
        fieldRule.setFieldName(fieldName);
        fieldRule.setNewFiledName(newFiledName);
        fieldRule.setValueParser(valueParser);
        return fieldRule;
    }
}
