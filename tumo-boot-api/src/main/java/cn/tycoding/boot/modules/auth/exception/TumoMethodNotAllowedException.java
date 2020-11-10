package cn.tycoding.boot.modules.auth.exception;

import cn.tycoding.boot.common.core.api.TumoHttpStatus;
import cn.tycoding.boot.modules.auth.component.TumoOAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 请求方法不支持异常
 *
 * @author tycoding
 * @see org.springframework.web.server.MethodNotAllowedException
 * @since 2020/10/16
 */
@JsonSerialize(using = TumoOAuth2ExceptionSerializer.class)
public class TumoMethodNotAllowedException extends TumoOAuth2Exception {

    public TumoMethodNotAllowedException(String msg, Throwable e) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return TumoHttpStatus.METHOD_NOT_SUPPORTED.getMsg();
    }

    @Override
    public int getHttpErrorCode() {
        return TumoHttpStatus.METHOD_NOT_SUPPORTED.getCode();
    }
}
