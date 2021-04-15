package cn.aftertomorrow.common.exception;

/**
 * 插入异常
 *
 * @author huangming
 * @date 2019/09/26
 */
public class InsertException extends RuntimeException {
    public InsertException(String message) {
        super(message);
    }
}
