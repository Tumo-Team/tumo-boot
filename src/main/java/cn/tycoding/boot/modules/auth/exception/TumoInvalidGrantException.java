package cn.tycoding.boot.modules.auth.exception;

import cn.tycoding.boot.common.core.api.HttpCode;
import cn.tycoding.boot.modules.auth.component.TumoAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 用户名或密码错误异常
 *
 * @author tycoding
 * @see org.springframework.security.oauth2.common.exceptions.InvalidGrantException
 * @since 2021/5/21
 */
@JsonSerialize(using = TumoAuth2ExceptionSerializer.class)
public class TumoInvalidGrantException extends TumoOAuth2Exception {

    public TumoInvalidGrantException(String msg, Throwable e) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return HttpCode.INVALID_GRANT.getMsg();
    }

    @Override
    public int getHttpErrorCode() {
        return HttpCode.INVALID_GRANT.getCode();
    }
}
