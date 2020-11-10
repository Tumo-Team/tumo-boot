package cn.tycoding.boot.modules.auth.exception;

import cn.tycoding.boot.common.core.api.TumoHttpStatus;
import cn.tycoding.boot.modules.auth.component.TumoOAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 服务器异常
 *
 * @author tycoding
 * @see org.springframework.web.server.ServerErrorException
 * @since 2020/10/16
 */
@JsonSerialize(using = TumoOAuth2ExceptionSerializer.class)
public class TumoServerErrorException extends TumoOAuth2Exception {

    public TumoServerErrorException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return TumoHttpStatus.INTERNAL_SERVER_ERROR.getMsg();
    }

    @Override
    public int getHttpErrorCode() {
        return TumoHttpStatus.INTERNAL_SERVER_ERROR.getCode();
    }
}
