package cn.tycoding.boot.common.core.utils;

import cn.hutool.json.JSONUtil;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import lombok.SneakyThrows;

import javax.servlet.http.HttpServletResponse;

/**
 * Servlet工具类
 *
 * @author tycoding
 * @since 2021/5/21
 */
public class ServletUtil {

    @SneakyThrows
    public static void write(HttpServletResponse response, R data) {
        response.setStatus(data.getCode());
        response.setHeader("Content-type", "application/json;charset=" + CommonConstant.UTF_8);
        response.setCharacterEncoding(CommonConstant.UTF_8);
        response.getWriter().write(JSONUtil.toJsonStr(data));
    }
}
