package cn.tycoding.boot.common.log.utils;

import cn.hutool.core.util.URLUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.http.HttpUtil;
import cn.tycoding.boot.common.auth.utils.AuthUtil;
import cn.tycoding.boot.modules.resource.entity.SysLog;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * 构建Log实体类信息
 *
 * @author tycoding
 * @since 2021/5/21
 */
public class SysLogUtil {

    /* 成功日志类型 */
    public static final int TYPE_OK = 1;
    /* 错误日志类型 */
    public static final int TYPE_FAIL = 2;

    /**
     * 构建日志Log类信息
     *
     * @param type      日志类型
     * @param operation 日志描述
     * @param method    操作方法
     * @param time      耗时
     * @return Log类
     */
    public static SysLog build(Integer type, String operation, String method, Long time) {
        HttpServletRequest request = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

        return new SysLog()
                .setType(type)
                .setUsername(AuthUtil.getUsername())
                .setOperation(operation)
                .setCreateTime(new Date())
                .setIp(ServletUtil.getClientIP(request))
                .setUrl(URLUtil.getPath(request.getRequestURI()))
                .setMethod(method)
                .setParams(HttpUtil.toParams(request.getParameterMap()))
                .setUserAgent(request.getHeader("user-agent"))
                .setTime(time);
    }
}
