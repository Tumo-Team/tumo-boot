package cn.tycoding.boot.modules.auth.exception;

import cn.tycoding.boot.common.core.api.HttpCode;
import cn.tycoding.boot.modules.auth.component.TumoAuth2ExceptionSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 服务器异常
 *
 * @author tycoding
 * @see org.springframework.web.server.ServerErrorException
 * @since 2021/5/21
 */
@JsonSerialize(using = TumoAuth2ExceptionSerializer.class)
public class TumoServerErrorException extends TumoOAuth2Exception {

    public TumoServerErrorException(String msg, Throwable t) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return HttpCode.INTERNAL_SERVER_ERROR.getMsg();
    }

    @Override
    public int getHttpErrorCode() {
        return HttpCode.INTERNAL_SERVER_ERROR.getCode();
    }
}
