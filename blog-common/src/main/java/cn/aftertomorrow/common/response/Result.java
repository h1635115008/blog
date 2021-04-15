package cn.aftertomorrow.common.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应封装对象
 *
 * @author huangming
 * @date 2021/04/13
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Result<T> implements Serializable {

    /**
     * 成功标志
     */
    private Boolean success;

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

}
