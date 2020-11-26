package cn.tycoding.boot.common.log.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * @author tycoding
 * @since 2020/11/23
 */
@Slf4j
public class ErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        log.error("ErrorAttributes 异常...");
        return super.getErrorAttributes(webRequest, includeStackTrace);
    }
}
