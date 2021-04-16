package cn.aftertomorrow.common.enumeration;

/**
 * 响应码
 *
 * @author huangming
 * @date 2021/04/13
 */
public enum ResultCodeEnums {
    /**
     * 成功响应
     */
    SUCCESS(0, "成功"),
    /**
     * 参数错误
     */
    ARGS_ERROR(-1, "参数错误"),
    /**
     * 服务端异常
     */
    SERVER_EXCEPTION(-2, "服务端异常"),
    /**
     * 业务异常
     */
    BIZ_ERROR(-3, "业务失败"),
    /**
     * 超时
     */
    SERVER_TIMEOUT(-4, "服务超时");

    private final Integer code;

    private final String desc;

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
