package edu.develop.leave.utils;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;

/**
 * @author zhangpei
 * @version 1.0
 * @description 日志生成工具
 * @date 2019/4/20
 */
public class LogUtil {

    public static String logInfo(Logger log, String model, Object... parms) {
        String format = StrUtil.format(model, parms);
        log.info(format);
        return format;
    }

    public static String logErr(Logger log, String model, Object... parms) {
        String format = StrUtil.format(model, parms);
        log.error(format);
        return format;
    }

    public static String logWarn(Logger log, String model, Object... parms) {
        String format = StrUtil.format(model, parms);
        log.warn(format);
        return format;
    }
}
