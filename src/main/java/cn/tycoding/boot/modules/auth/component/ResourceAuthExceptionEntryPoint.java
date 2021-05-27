package cn.tycoding.boot.modules.auth.component;

import cn.hutool.core.util.URLUtil;
import cn.tycoding.boot.common.auth.utils.SpringContextHolder;
import cn.tycoding.boot.common.core.api.HttpCode;
import cn.tycoding.boot.common.core.api.R;
import cn.tycoding.boot.common.core.constant.CommonConstant;
import cn.tycoding.boot.common.log.event.LogEvent;
import cn.tycoding.boot.common.log.utils.SysLogUtil;
import cn.tycoding.boot.modules.resource.entity.SysLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 自定义资源服务器未授权异常
 *
 * @author tycoding
 * @since 2021/5/21
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ResourceAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpCode.UN_AUTHORIZED.getCode());
        response.setCharacterEncoding(CommonConstant.UTF_8);
        response.setContentType(CommonConstant.CONTENT_TYPE);
        PrintWriter writer = response.getWriter();
        R<String> result = new R<>();
        result.setCode(HttpCode.UN_AUTHORIZED.getCode());
        result.setMsg(HttpCode.UN_AUTHORIZED.getMsg());
        log.error(HttpCode.UN_AUTHORIZED.getMsg() + ", URL: {}", URLUtil.getPath(request.getRequestURI()));
        writer.append(objectMapper.writeValueAsString(result));

        SysLog sysLog = SysLogUtil.build(2, HttpCode.UN_AUTHORIZED.getMsg(), null, null);
        SpringContextHolder.publishEvent(new LogEvent(sysLog));
    }
}
