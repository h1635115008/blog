package cn.aftertomorrow.common.enumeration;

/**
 * 响应码
 *
 * @author keriezhang
 * @date 2021/04/13
 */
public enum ResultCodeEnums {
    /**
     * 成功响应
     */
    SUCCESS(0, "响应成功");

    private Integer code;

    private String desc;

    ResultCodeEnums(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
