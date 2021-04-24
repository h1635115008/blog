package cn.aftertomorrow.common.transfer;

import cn.aftertomorrow.common.util.DateUtils;

import java.util.Date;
import java.util.function.Function;

/**
 * @author huangming
 * @className ValueParser
 * @description
 * @date 2021/4/24
 */
@FunctionalInterface
public interface ValueParser<T, R> extends Function<T, R> {
    /**
     * 日期转换为月日
     */
    ValueParser<String, String> DATE_PARSER_TO_MD = DateUtils::getMD;
    /**
     * 日期转换为年月日
     */
    ValueParser<String, String> DATE_PARSER_TO_YMD = DateUtils::getYMD;
    /**
     * 日期转换为年月日时
     */
    ValueParser<String, String> DATE_PARSER_TO_YMDH = DateUtils::getYMDHM;
    /**
     * 日期转换为年月日分
     */
    ValueParser<String, String> DATE_PARSER_TO_YMDHM = DateUtils::getYMDHM;
    /**
     * 日期转换为年月日分秒
     */
    ValueParser<String, String> DATE_PARSER_TO_YMDHMS = DateUtils::getYMDHMS;
}
