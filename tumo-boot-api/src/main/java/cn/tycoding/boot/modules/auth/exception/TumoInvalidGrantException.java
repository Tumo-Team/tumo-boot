package cn.tycoding.boot.modules.auth.exception;

import cn.tycoding.boot.common.core.api.TumoHttpStatus;
import cn.tycoding.boot.modules.auth.component.TumoOAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 用户名或密码错误异常
 *
 * @author tycoding
 * @see org.springframework.security.oauth2.common.exceptions.InvalidGrantException
 * @since 2020/10/16
 */
@JsonSerialize(using = TumoOAuth2ExceptionSerializer.class)
public class TumoInvalidGrantException extends TumoOAuth2Exception {

    public TumoInvalidGrantException(String msg, Throwable e) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return TumoHttpStatus.INVALID_GRANT.getMsg();
    }

    @Override
    public int getHttpErrorCode() {
        return TumoHttpStatus.INVALID_GRANT.getCode();
    }
}
