package cn.tycoding.boot.modules.auth.exception;

import cn.tycoding.boot.common.core.api.HttpCode;
import cn.tycoding.boot.modules.auth.component.TumoOAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 拒绝访问异常
 *
 * @author tycoding
 * @see org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator
 * @since 2021/5/21
 */
@JsonSerialize(using = TumoOAuth2ExceptionSerializer.class)
public class TumoForbiddenException extends TumoOAuth2Exception {

    public TumoForbiddenException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return HttpCode.FORBIDDEN.getMsg();
    }

    @Override
    public int getHttpErrorCode() {
        return HttpCode.FORBIDDEN.getCode();
    }
}
