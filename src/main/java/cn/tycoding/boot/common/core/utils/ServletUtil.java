package cn.tycoding.boot.common.core.utils;

import cn.hutool.json.JSONUtil;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import lombok.SneakyThrows;
import org.apache.poi.ss.formula.functions.T;

import javax.servlet.http.HttpServletResponse;

/**
 * @author tycoding
 * @since 2021/2/21
 */
public class ServletUtil {

    @SneakyThrows
    public static void write(HttpServletResponse response, R<T> data) {
        response.setStatus(data.getCode());
        response.setHeader("Content-type", "application/json;charset=" + CommonConstant.UTF_8);
        response.setCharacterEncoding(CommonConstant.UTF_8);
        response.getWriter().write(JSONUtil.toJsonStr(data));
    }
}
