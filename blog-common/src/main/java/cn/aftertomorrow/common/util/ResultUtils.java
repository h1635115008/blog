package cn.aftertomorrow.common.util;

import cn.aftertomorrow.common.enumeration.ResultCodeEnums;
import cn.aftertomorrow.common.response.Result;

/**
 * 响应对象创建工具类
 *
 * @author huangming
 * @date 2021/04/13
 */
public class ResultUtils {
    /**
     * 创建成功响应对象
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> createSuccessResult(T data) {
        return Result.<T>builder()
                .code(ResultCodeEnums.SUCCESS.getCode())
                .msg(ResultCodeEnums.SUCCESS.getDesc())
                .data(data)
                .success(true)
                .build();
    }

    public static <T> Result<T> createFailResult(ResultCodeEnums resultCode, String msg) {
        return Result.<T>builder()
                .code(resultCode.getCode())
                .msg(msg)
                .success(false)
                .build();
    }
}
